package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Conversor;
import models.ConversorApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private final String API_KEY="b74b0ac73ac105f89bb7c1a5";

    public Conversor obtenerDatos(String codigoBase, String codigoDestino, double cantidad){
        String URL_BASE="https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/"+codigoBase+"/"+codigoDestino+"/"+cantidad;
        Gson gson= new GsonBuilder()
                .setPrettyPrinting()
                .create();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();
        ConversorApi conversorApi = gson.fromJson(json, ConversorApi.class);
        Conversor conversor= new Conversor(conversorApi);
        return conversor;
    }
}
