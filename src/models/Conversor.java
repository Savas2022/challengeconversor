package models;

public class Conversor {
    String monedaBase;
    String monedaDestino;
    double tipoDeCambio;
    double resultado;

    public Conversor(ConversorApi conversorApi) {
        this.monedaBase = conversorApi.base_code();
        this.monedaDestino = conversorApi.target_code();
        this.tipoDeCambio = conversorApi.conversion_rate();
        this.resultado = conversorApi.conversion_result();
    }

    @Override
    public String toString() {
        return  "La conversion de "+ '\'' + monedaBase + '\'' +
                " a '" + monedaDestino + '\'' +
                " es: " + resultado;
    }
}
