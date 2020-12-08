import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {

        //RequestBul requestBul = new RequestBul("1234", "firstuser@mail.ru");//70087560
        NewRequestBul requestBul = new NewRequestBul("70087560");//70087560

        String address = "deremenko.simsim.ftp.sh";//
        int port = 4048;
        String page = "login";

//        String postUrl = "http://" + address + ":" + port + "/" + page;//http://localhost:8080/demo/getCoins
        String postUrl = "http://localhost:8080/demo/getCoins";//http://localhost:8080/demo/getCoins

        Gson         gson          = new Gson();
        HttpClient httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString = null;//gson.tojson() converts your pojo to json
        try {
            postingString = new StringEntity(gson.toJson(requestBul));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse  response = null;
        try {
            response = httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.toString());
    }

}
