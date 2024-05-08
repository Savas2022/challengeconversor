package principal;

import models.Conversor;
import services.ConsumoAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Principal {
    private Scanner teclado= new Scanner(System.in);
    private ConsumoAPI consumoApi= new ConsumoAPI();
    Integer opc;

    public void Option(){
        String monedaBase ="";
        String monedaDestino ="";
        double cantidad=0.0;
        boolean ok= true;

        while (ok) {
            Menu();
            int option=getUserOption();

            switch (option) {
                case 1:
                    monedaBase = "USD";
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaBase = "ARS";
                    monedaDestino = "USD";
                    break;
                case 3:
                    monedaBase = "USD";
                    monedaDestino = "BRL";
                    break;
                case 4:
                    monedaBase = "BRL";
                    monedaDestino = "USD";
                    break;
                case 5:
                    monedaBase = "USD";
                    monedaDestino = "COP";
                    break;
                case 6:
                    monedaBase = "COP";
                    monedaDestino = "USD";
                    break;
                case 7:
                    System.out.println("Saliendo del conversor de monedas...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    ok=false;
            }

                if (option >=1 && option<=6) {
                    System.out.print("\n Por favor ingrese el Monto que desea cambiar: ");
                    cantidad = teclado.nextDouble();
                }
                Conversor conversor = consumoApi.obtenerDatos(monedaBase,monedaDestino,cantidad);
                System.out.println(conversor);
                System.out.println("\n");


        }
    }

    public void Menu(){

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
                ***************************************************************\n """);
            }

    public int getUserOption() {

            do{
                System.out.print("Ingrese una opcion: ");
                opc=teclado.nextInt();
                if (opc >= 8){
                    System.out.println("Opcion invalidad!!");
                }
            }while(opc < 1 || opc > 7);
          return opc;
    }

}
