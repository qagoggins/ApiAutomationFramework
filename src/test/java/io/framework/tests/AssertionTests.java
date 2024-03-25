package io.framework.tests;

import io.framework.Annotations.FrameworkAnnotation;
import io.framework.Reports.ExtentLogger;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static io.framework.RequestBuilder.ApiBuilder.*;
import static io.framework.Utils.Converter.*;
import static io.framework.Assertions.Assertions.*;

public class AssertionTests {
    private static Logger logger = LogManager.getLogger(AssertionTests.class);
    @Test(dataProvider = "getDataForFieldTypeAssert")
    @FrameworkAnnotation(author = {"Baiel"}, category = {"Sanity"})
    public void fieldTypeAssertionTest(String key, Object value) throws IOException {
        Response response = buildRequestForGetCalls()
                .pathParam("id", key)
                .get("/employees/{id}");
        ExtentLogger.logResponse(response.prettyPrint());
        logger.info("Get request has been performed...");
        assertResponseFieldType(jsonResponseToMap(response), "lname", value.getClass());
    }
    @Test(dataProvider = "getDataForHasKeysAssert")
    @FrameworkAnnotation(author = {"Muhtar"}, category = {"Regression"})
    public void responseHasKeysAssertionTest(String id, HashSet<String> expectedKeys) throws IOException {
        Response response = buildRequestForGetCalls()
                .pathParam("id", id)
                .get("/employees/{id}");
        ExtentLogger.logResponse(response.prettyPrint());
        logger.info("Get request has sent...");
        assertResponseHasKeys(jsonResponseToMap(response), expectedKeys);
    }

    @DataProvider
    public Object[][] getDataForFieldTypeAssert() {
        Map<String, Object> map = new HashMap<>();
        map.put("70", "abbott");
        map.put("45", 2);
        map.put("73", false);
        Object[][] data = new Object[map.size()][2];
        int index = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            data[index][0] = entry.getKey();
            data[index][1] = entry.getValue();
            index++;
        }
        return data;
    }

    @DataProvider
    public Object[][] getDataForHasKeysAssert() {
        HashSet<String> setOne = new HashSet<>();
        setOne.add("fname");
        setOne.add("lname");

        HashSet<String> setTwo = new HashSet<>();
        setTwo.add("house");
        setTwo.add("car");
        setTwo.add("fname");

        HashSet<String> setThree = new HashSet<>();
        setThree.add("id");
        setThree.add("fname");
        setThree.add("lname");
        return new Object[][] {
                {"78", setOne},
                {"75", setTwo},
                {"57", setThree}
        };
    }

}
