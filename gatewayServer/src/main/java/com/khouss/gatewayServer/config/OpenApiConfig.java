package com.khouss.gatewayServer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Microservices AppMicro")
                        .version("1.0")
                        .description("Documentation générale des APIs des microservices via Gateway"))
                .paths(createPaths())
                .components(new io.swagger.v3.oas.models.Components()
                        .addSchemas("CreateCompte", createCreateCompteSchema())
                        .addSchemas("GetCompte", createGetCompteSchema())
                        .addSchemas("CitoyenDto", createCitoyenDtoSchema())
                        .addSchemas("TransfertRequest", createTransfertRequestSchema()));
    }

    private Paths createPaths() {
        Paths paths = new Paths();

        // Comptes
        paths.addPathItem("/comptes", new PathItem()
                .get(new Operation()
                        .summary("Récupérer tous les comptes")
                        .description("Retourne la liste de tous les comptes")
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Liste des comptes")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new ArraySchema()
                                                                .items(new Schema<>().$ref("#/components/schemas/GetCompte"))))))))
                .post(new Operation()
                        .summary("Créer un nouveau compte")
                        .description("Crée un nouveau compte bancaire")
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().$ref("#/components/schemas/CreateCompte")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Compte créé")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/GetCompte"))))))));

        paths.addPathItem("/comptes/exists/{numeroTelephone}", new PathItem()
                .get(new Operation()
                        .summary("Vérifier l'existence d'un compte")
                        .description("Vérifie si un compte existe pour le numéro de téléphone donné")
                        .parameters(java.util.List.of(new Parameter()
                                .name("numeroTelephone")
                                .in("path")
                                .required(true)
                                .schema(new StringSchema())
                                .description("Numéro de téléphone")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Résultat de la vérification")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new BooleanSchema())))))));

        // Citoyens
        paths.addPathItem("/citoyens/getAll", new PathItem()
                .get(new Operation()
                        .summary("Récupérer tous les citoyens")
                        .description("Retourne la liste de tous les citoyens")
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Liste des citoyens")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new ArraySchema()
                                                                .items(new Schema<>().$ref("#/components/schemas/CitoyenDto")))))))));

        paths.addPathItem("/citoyens/{numCni}", new PathItem()
                .get(new Operation()
                        .summary("Récupérer un citoyen par numéro CNI")
                        .description("Retourne les informations d'un citoyen")
                        .parameters(java.util.List.of(new Parameter()
                                .name("numCni")
                                .in("path")
                                .required(true)
                                .schema(new IntegerSchema().format("int64"))
                                .description("Numéro de CNI")))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Informations du citoyen")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/CitoyenDto")))))
                                .addApiResponse("404", new ApiResponse()
                                        .description("Citoyen non trouvé")))));

        // Transferts
        paths.addPathItem("/transferts", new PathItem()
                .post(new Operation()
                        .summary("Effectuer un transfert")
                        .description("Effectue un transfert d'argent entre deux comptes")
                        .requestBody(new RequestBody()
                                .content(new Content()
                                        .addMediaType("application/json", new MediaType()
                                                .schema(new Schema<>().$ref("#/components/schemas/TransfertRequest")))))
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse()
                                        .description("Transfert réussi")
                                        .content(new Content()
                                                .addMediaType("application/json", new MediaType()
                                                        .schema(new StringSchema())))))));

        return paths;
    }

    private Schema<?> createCreateCompteSchema() {
        return new ObjectSchema()
                .addProperty("numeroTelephone", new StringSchema().description("Numéro de téléphone"))
                .addProperty("solde", new NumberSchema().format("double").description("Solde initial"))
                .addProperty("numCni", new IntegerSchema().format("int64").description("Numéro de CNI"));
    }

    private Schema<?> createGetCompteSchema() {
        return new ObjectSchema()
                .addProperty("numeroTelephone", new StringSchema().description("Numéro de téléphone"))
                .addProperty("solde", new NumberSchema().format("double").description("Solde actuel"))
                .addProperty("numCni", new IntegerSchema().format("int64").description("Numéro de CNI"));
    }

    private Schema<?> createCitoyenDtoSchema() {
        return new ObjectSchema()
                .addProperty("numCni", new IntegerSchema().format("int64").description("Numéro de CNI"))
                .addProperty("prenom", new StringSchema().description("Prénom"))
                .addProperty("nom", new StringSchema().description("Nom"))
                .addProperty("adresse", new StringSchema().description("Adresse"))
                .addProperty("ddn", new DateSchema().description("Date de naissance"));
    }

    private Schema<?> createTransfertRequestSchema() {
        return new ObjectSchema()
                .addProperty("source", new StringSchema().description("Numéro de téléphone source"))
                .addProperty("destination", new StringSchema().description("Numéro de téléphone destination"))
                .addProperty("montant", new NumberSchema().format("decimal").description("Montant à transférer"));
    }
}