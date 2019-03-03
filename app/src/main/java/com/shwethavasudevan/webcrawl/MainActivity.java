package com.shwethavasudevan.webcrawl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.shwethavasudevan.webcrawl.WebhoseIOClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("XXX-XXXX-XXXX-XXXX-XXXX");
        Map<String, String> queries = new HashMap<String, String>();
        queries.put("q", "ipod");

        JsonElement result = null;
        try {
            result = webhoseClient.query("filterWebData", queries);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.getAsJsonObject().get("totalResults"));


        JsonArray postArray = result.getAsJsonObject().getAsJsonArray("posts");

        for(JsonElement o  : postArray) {
            Toast.makeText(getApplicationContext(),o.getAsJsonObject().get("title") + " ", Toast.LENGTH_LONG).show();  // Print title
            System.out.println(o.getAsJsonObject().get("author")); // Print author
            System.out.println(o.getAsJsonObject().get("language"));   // Print language
        }
    }


}
