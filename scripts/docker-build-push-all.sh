#!/usr/bin/env bash
set -euo pipefail

TAG="${1:-latest}"
shift || true

DOCKER=(docker)
if ! docker info >/dev/null 2>&1; then
  if command -v sudo >/dev/null 2>&1 && sudo -n docker info >/dev/null 2>&1; then
    DOCKER=(sudo docker)
  else
    echo "Error: cannot access Docker daemon (try adding your user to the docker group, or run with sudo)." >&2
    exit 1
  fi
fi

if (( $# > 0 )); then
  SERVICES=("$@")
else
  SERVICES=(configServer eurekaserver compte daf transfert gatewayServer)
fi

for service_dir in "${SERVICES[@]}"; do
  service_name="${service_dir,,}"
  image="khoussngom/appmicro-${service_name}:${TAG}"

  echo "==> Build ${image}"
  "${DOCKER[@]}" build -t "${image}" "${service_dir}"

  echo "==> Push  ${image}"
  "${DOCKER[@]}" push "${image}"
done
