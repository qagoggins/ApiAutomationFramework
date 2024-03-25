package io.framework.demo;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DataProviderTest {
    @Test(dataProvider = "getData")
    public void dpTest(Map<String, String> map) {
        System.out.println(map.get("username"));
        System.out.println("password = " + map.get("password"));
        Assertions.assertThat(true).isEqualTo(true);
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[3][1];
        Map<String, String> map1 = new HashMap<>();
        map1.put("username", "linchevatel");
        map1.put("password", "crossbow");
        map1.put("email", "shitty@mail.com");

        Map<String, String> map2 = new HashMap<>();
        map1.put("username", "rodgers");
        map1.put("password", "candidat");
        map1.put("email", "usa@mail.com");

        Map<String, String> map3 = new HashMap<>();
        map1.put("username", "starko");
        map1.put("password", "tatetate");
        map1.put("email", "rahul@yahoo.com");

        data[0][0] = map1;
        data[1][0] = map2;
        data[2][0] = map3;
        return data;
    }
}
