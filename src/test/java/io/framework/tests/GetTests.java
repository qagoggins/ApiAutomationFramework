package io.framework.tests;


import io.framework.Annotations.FrameworkAnnotation;
import io.framework.Reports.ExtentLogger;
import io.framework.RequestBuilder.ApiBuilder;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class GetTests extends BaseTest {

    @Test
    @FrameworkAnnotation(author = {"Baiel"}, category = {"Smoke", "Get call"})
    public void getEmployeesDetails() {
        Response response = ApiBuilder.buildRequestForGetCalls()
                .get("/employees");

        ExtentLogger.logResponse(response.prettyPrint());

        assertThat(response.getStatusCode())
                .isEqualTo(200);
        assertThat(response.jsonPath().getList("$").size())
                .isPositive()
                .as("Validating the size of the employee array").isLessThan(30);
    }
    @Test(dataProvider = "getEmployeeData")
    @FrameworkAnnotation(author = {"Baiel", "Robin"}, category = {"Smoke", "Get call"})
    public void getEmployeeDetails(String id, String lastName) {
        Response response = ApiBuilder.buildRequestForGetCalls()
                .pathParam("id", id)
                .get("/employees/{id}");
        ExtentLogger.logResponse(response.prettyPrint());
        assertThat(response.getStatusCode())
                .isEqualTo(200);
        assertThat(response.jsonPath().getString("lname")) // or "fname"
                .as("Comparing the last name node in the response").isEqualTo(lastName)
                .hasSizeBetween(3, 20);
    }

    @DataProvider
    public Object[][] getEmployeeData() {
        return new Object[][] {
                {"75", "langworth"},
        };
    }
}
