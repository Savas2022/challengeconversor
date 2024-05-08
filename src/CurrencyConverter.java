import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import principal.Principal;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/b74b0ac73ac105f89bb7c1a5/latest/USD";

    public static void main(String[] args) {
        Principal prin= new Principal();
        prin.Option();
    }
}