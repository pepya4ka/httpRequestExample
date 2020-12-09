import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.gson.Gson;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {

        RequestBul requestBul = new RequestBul("1234", "firstuser@mail.ru");

        String address = "deremenko.simsim.ftp.sh";//
        int port = 4048;
        String page = "login";

        String postUrl = "http://" + address + ":" + port + "/" + page;

        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = null;//gson.tojson() converts your pojo to json
        System.out.println(gson.toJson(requestBul));
        try {
            postingString = new StringEntity(gson.toJson(requestBul));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = null;
        try {
            response = httpClient.execute(post);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String responseString = null;
        try {
            responseString = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(responseString);
    }

}
