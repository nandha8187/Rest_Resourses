package com.ems.json_schema_validation;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class RestAssuredJsonSchemaValidation {

    @Test(enabled = false)
	public void validationusingJsonSchemaInClassPath() {

		File inputJson = new File("src/test/resources/input.json");


		RestAssured
		.given()
		.baseUri("http://localhost:3000")
		.header("Content-Type","application/json")
		.body(inputJson)
		.when()
		.post("/employees")
        .then()
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		

	}
    
    @Test
    public void validationUsingMatchesJsonSchema() {
		
    	File inputJson = new File ("src/test/resources/input.json");
    	File inputSchema = new File("src/test/resources/schema.json");    	
    	RestAssured
		.given()
		.baseUri("http://localhost:3000")
		.header("Content-Type","application/json")
		.body(inputJson)
		.when()
		.post("/employees")
        .then()
        .body(JsonSchemaValidator.matchesJsonSchema("{\r\n"
        		+ "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\r\n"
        		+ "  \"type\": \"object\",\r\n"
        		+ "  \"properties\": {\r\n"
        		+ "    \"firstname\": {\r\n"
        		+ "      \"type\": \"string\"\r\n"
        		+ "    },\r\n"
        		+ "    \"lastname\": {\r\n"
        		+ "      \"type\": \"string\"\r\n"
        		+ "    },\r\n"
        		+ "    \"email\": {\r\n"
        		+ "      \"type\": \"string\"\r\n"
        		+ "    },\r\n"
        		+ "    \"skills\": {\r\n"
        		+ "      \"type\": \"array\",\r\n"
        		+ "      \"items\": [\r\n"
        		+ "        {\r\n"
        		+ "          \"type\": \"string\"\r\n"
        		+ "        },\r\n"
        		+ "        {\r\n"
        		+ "          \"type\": \"string\"\r\n"
        		+ "        }\r\n"
        		+ "      ]\r\n"
        		+ "    }\r\n"
        		+ "  },\r\n"
        		+ "  \"required\": [\r\n"
        		+ "    \"firstname\",\r\n"
        		+ "    \"lastname\",\r\n"
        		+ "    \"email\",\r\n"
        		+ "    \"skills\"\r\n"
        		+ "  ]\r\n"
        		+ "}"));
		
    	
    	
	} 




}
