package at.kepplinger.brewery.test;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ValidationTests {

    @Test
    public void t01Valid() throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:8080/docker-brewery/rs/brewery");

        StringEntity requestEntity = new StringEntity(
                "{\"location\":\"Linz\",\"area\":200.00}",
                "application/json",
                "UTF-8");

        try {
            post.setEntity(requestEntity);
            HttpResponse response = client.execute(post);

            assertThat(response.getStatusLine().getStatusCode(), is(HttpStatus.SC_CREATED));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void t02InValid() throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://localhost:8080/docker-brewery/rs/brewery");

        StringEntity requestEntity = new StringEntity(
                "{\"location\":\"Linz\",\"area\":2000000}",
                "application/json",
                "UTF-8");

        try {
            post.setEntity(requestEntity);
            HttpResponse response = client.execute(post);

            assertThat(response.getStatusLine().getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

}
