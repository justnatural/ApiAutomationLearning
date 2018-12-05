import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static main.java.entities.User.ID;
import static main.java.entities.User.LOGIN;

public class BodyTestWithSimpleMap extends BaseClass{

    CloseableHttpClient client ;
    CloseableHttpResponse response ;

    @BeforeMethod
    public void setUp() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client = HttpClientBuilder.create().build();
        client.close();
        response.close();
    }

    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        //response.getEntity().get

        String jsonBody = EntityUtils.toString(response.getEntity());
        System.out.println(jsonBody);

        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginValue = (String) getValueFor(jsonObject, LOGIN);
        Assert.assertEquals(loginValue, "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        //response.getEntity().get

        String jsonBody = EntityUtils.toString(response.getEntity());
        System.out.println(jsonBody);

        JSONObject jsonObject = new JSONObject(jsonBody);

        Integer loginValue = (Integer) getValueFor(jsonObject, ID);
        Assert.assertEquals(loginValue, Integer.valueOf(11834443));
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);

    }

}
