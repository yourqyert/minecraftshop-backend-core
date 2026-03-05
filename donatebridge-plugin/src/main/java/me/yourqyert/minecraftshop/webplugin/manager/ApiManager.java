package me.yourqyert.minecraftshop.webplugin.manager;

import me.yourqyert.minecraftshop.webplugin.DonateBridge;
import org.json.JSONArray;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiManager {
    private final String url;
    private final String key;
    private final DonateBridge plugin;
    private final HttpClient httpClient;

    public ApiManager(String url, String key, DonateBridge plugin) {
        this.url = url;
        this.key = key;
        this.plugin = plugin;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public JSONArray fetchTasks() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/tasks"))
                .header("X-Server-Key", key)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 403) {
            plugin.getLogger().severe("Authorization failed! Check secret-key in config.");
            return new JSONArray();
        }

        return new JSONArray(response.body());
    }

    public void confirmTask(long taskId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/complete/" + taskId))
                .header("X-Server-Key", key)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.discarding());
    }
}