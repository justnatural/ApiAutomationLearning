import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Get404 extends BaseClass{


    CloseableHttpClient client ;
    CloseableHttpResponse response;

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
    public void nonExistingUrlReturns404() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");

        response = client.execute(get);

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, 404);
    }

}
