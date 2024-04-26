import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/b74b0ac73ac105f89bb7c1a5/latest/USD";

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int option = getUserOption();

            switch (option) {
                case 1:
                    convertDollarToARS();
                    break;
                case 2:
                    convertARSToDollar();
                    break;
                case 3:
                    convertDollarToBRL();
                    break;
                case 4:
                    convertBRLToDollar();
                    break;
                case 5:
                    convertDollarToCOP();
                    break;
                case 6:
                    convertCOPToDollar();
                    break;
                case 7:
                    System.out.println("Saliendo del conversor de monedas...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("""
                ***************************************************************
                ----------------------Conversor De Moneda----------------------
                
                1) Dolar => Peso Argentino
                2) Peso Argentino => Dolar
                3) Dolar => Real Brasileño
                4) Real Brasileño => Dolar
                5) Dolar => Peso Colombiano
                6) Peso Colombiano => Dolar
                7) Salir
                    Seleccione una opcion
                *************************************************************** \n""");
    }

    private static int getUserOption() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Ingrese la opción: ");
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error al leer la entrada del usuario: " + e.getMessage());
            return -1;
        }
    }

    private static void convertDollarToARS() {
        double amount = getUserAmount("dólares");
        double rate = getExchangeRate("ARS");
        double result = amount * rate;
        System.out.printf("%.2f dólares = %.2f pesos argentinos%n", amount, result);
    }

    private static void convertARSToDollar() {
        double amount = getUserAmount("pesos argentinos");
        double rate = getExchangeRate("ARS");
        double result = amount / rate;
        System.out.printf("%.2f pesos argentinos = %.2f dólares%n", amount, result);
    }

    private static void convertDollarToBRL() {
        double amount = getUserAmount("dólares");
        double rate = getExchangeRate("BRL");
        double result = amount * rate;
        System.out.printf("%.2f dólares = %.2f reales brasileños%n", amount, result);
    }

    private static void convertBRLToDollar() {
        double amount = getUserAmount("reales brasileños");
        double rate = getExchangeRate("BRL");
        double result = amount / rate;
        System.out.printf("%.2f reales brasileños = %.2f dólares%n", amount, result);
    }

    private static void convertDollarToCOP() {
        double amount = getUserAmount("dólares");
        double rate = getExchangeRate("COP");
        double result = amount * rate;
        System.out.printf("%.2f dólares = %.2f pesos colombianos%n", amount, result);
    }

    private static void convertCOPToDollar() {
        double amount = getUserAmount("pesos colombianos");
        double rate = getExchangeRate("COP");
        double result = amount / rate;
        System.out.printf("%.2f pesos colombianos = %.2f dólares%n", amount, result);
    }

    private static double getUserAmount(String currency) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.printf("Ingrese la cantidad en %s: ", currency);
            return Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error al leer la entrada del usuario: " + e.getMessage());
            return 0.0;
        }
    }

    private static double getExchangeRate(String targetCurrency) {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
                JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
                return rates.get(targetCurrency).getAsDouble();
            } else {
                System.out.println("Error al obtener el tipo de cambio: " + responseCode);
                return 0.0;
            }
        } catch (Exception e) {
            System.out.println("Error al comunicar con la API: " + e.getMessage());
            return 0.0;
        }
    }
}
