package com.api.verifier.suits;

import com.api.verifier.domain.Group;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.api.verifier.config.CommonConfiguration.*;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

public class GroupsApiTest {

    //test variables
    private static final String DEFAULT_GROUP_NAME = "Group 1";
    private static final String GROUP_NAME_SYS_VARIABLE = "expected.group.name";
    private static String expectedGroupName;

    @BeforeClass
    public void setUp() {
        String port = System.getProperty(PORT_SYS_VARIABLE, DEFAULT_PORT);
        RestAssured.port = Integer.valueOf(port);

        String baseHost = System.getProperty(HOST_SYS_VARIABLE, DEFAULT_HOST);
        RestAssured.baseURI = baseHost;

        expectedGroupName = System.getProperty(GROUP_NAME_SYS_VARIABLE, DEFAULT_GROUP_NAME);
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }

    @Test
    public void getGroupsSendsCode200andValidFirstGroup() {
        JsonPath jsonPath = given().contentType(ContentType.JSON).when().get("/groups").then().assertThat().statusCode(200).extract().jsonPath();
        List<Group> jsonContent = jsonStringToObjectList(jsonPath.prettyPrint());
        Assert.assertEquals(jsonContent.get(0).getName(), expectedGroupName);
    }


    public List<Group> jsonStringToObjectList(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<List<Group>>() {
        }.getType());
    }
}
