import main.java.ResponseUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseHeaders extends BaseClass{

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
    public void responseHeaders() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT );

        response = client.execute(get);

        Header contentType = response.getEntity().getContentType();
        assertEquals(contentType.getValue(), "application/json; charset=utf-8");

        ContentType ct = ContentType.getOrDefault(response.getEntity());
        assertEquals(ct.getMimeType(), "application/json");
        //assertEquals(ct.getCharset(), "UTF-8");


    }
    
    @Test
    public void serverIsGithub() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT );

        response = client.execute(get);

        String headerValue = ResponseUtils.getHeader(response, "Server");

        Assert.assertEquals(headerValue, "GitHub.com");


    }

    @Test
    public void xRateLimitIsSixty() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT );

        response = client.execute(get);

        String limitVal = ResponseUtils.getHeaderjava8Way(response, "X-RateLimit-Limit");
        assertEquals(limitVal, "60");

    }

    @Test
    public void eTagIsPresent() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT );

        response = client.execute(get);

        boolean tagIsPreent = ResponseUtils.headerIsPresent(response, "ETag");
        assertTrue(tagIsPreent);

    }

}
