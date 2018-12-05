import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;

public class BaseClass {

    CloseableHttpClient client ;
    CloseableHttpResponse response;

    protected static final String BASE_ENDPOINT = "https://api.github.com";


}
