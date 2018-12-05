import main.java.ResponseUtils;
import main.java.entities.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class BodyTestWithJackson extends BaseClass{

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

        User user = ResponseUtils.unmarshall(response, User.class);

        Assert.assertEquals(user.getLogin(), "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        User user = ResponseUtils.unmarshall(response, User.class);

        Assert.assertEquals(user.getId(), 11834443);
    }



}
