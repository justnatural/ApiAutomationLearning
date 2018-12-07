import entities.NotFound;
import entities.RateLimit;
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

        User user = ResponseUtils.unmarshallGeneric(response, User.class);

        Assert.assertEquals(user.getId(), 11834443);
    }

    @Test //(enabled = false)
    public void notFoundMessageIsCorrect() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/nonexistingendpoint");

        response = client.execute(get);

        NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);

        Assert.assertEquals(notFoundMessage.getMessage(), "Not Found");
    }

    @Test //(enabled = false)
    public void correctRateLimitsAreSet() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");

        response = client.execute(get);

        RateLimit rateLimit = ResponseUtils.unmarshallGeneric(response, RateLimit.class);

        Assert.assertEquals(rateLimit.getCoreLimit(),60);
        Assert.assertEquals(rateLimit.getSearchLimit(),"10");
    }

}
