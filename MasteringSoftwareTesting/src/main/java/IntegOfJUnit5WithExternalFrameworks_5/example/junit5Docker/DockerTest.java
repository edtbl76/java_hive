package IntegOfJUnit5WithExternalFrameworks_5.example.junit5Docker;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.dockerjava.transport.DockerHttpClient.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DockerTest {

    private static final DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

    public static void main(String[] args) throws IOException {
        DockerHttpClient client = new ApacheDockerHttpClient.Builder()
                .dockerHost(standard.getDockerHost())
                .sslConfig(standard.getSSLConfig())
                .build();

        Request request = Request.builder()
                .method(Request.Method.GET)
                .path("/_ping")
                .build();

        try(Response response = client.execute(request)) {
            assertThat(response.getStatusCode(), equalTo(200));
            assertThat(IOUtils.toString(response.getBody(), StandardCharsets.UTF_8), equalTo("OK"));
        }

        DockerClient dockerClient = DockerClientImpl.getInstance(standard, client);
        System.out.println(dockerClient.pingCmd().exec());
    }
}
