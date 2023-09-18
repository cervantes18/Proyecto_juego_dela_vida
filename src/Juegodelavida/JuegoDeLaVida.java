package Juegodelavida;

import java.util.Scanner;
//Clase main del juego de la vida
public class JuegoDeLaVida {
	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);//creamos un objeto scanner para obtener los datos del teclado

		 //ingresamos el numero de filas
	        System.out.println("Ingrese el número de filas del tablero (2-20):");
	        int nf = scanner.nextInt();
	        //ingresamos el numero de columnas
	        System.out.println("Ingrese el número de columnas del tablero (2-20):");
	        int nc = scanner.nextInt();
	        //ingrese el numero de generaciones
	        System.out.println("Ingrese el número de generaciones del juego (1-50):");
	        int gen = scanner.nextInt();

	        //creamos el objeto tablero y se le pasan el numero de filas (nf)y el numero de columnas (nc)
	        Tablero tablero = new Tablero(nf, nc);

	        //se ingresa el numero de organismos vivos durante la primer generacion
	        System.out.println("Ingrese el número de organismos iniciales (n) (0-50% del tablero):");
	        int n = (nf * nc * scanner.nextInt()) / 100;
	        
	        //se crea un bucle para ingresar las coordenadas de las celulas vivas
	        for (int i = 0; i < n; i++) {
	            System.out.println("Ingrese la posición fila y columna del organismo " + (i + 1) + ":");
	            int fila = scanner.nextInt();
	            int columna = scanner.nextInt();
	            tablero.setCelulaViva(fila, columna);//se setea la celula viva en el tablero
	        }

	        //bucle para simular las generaciones
	        for (int generacion = 1; generacion <= gen; generacion++) {
	            System.out.println("Generación " + generacion + ":");
	            System.out.println(tablero.toString());
	            tablero.calcularSiguienteGeneracion();//llamada al metodo siguienteGeneracion
	            System.out.println("Presione Enter para continuar a la siguiente generación...");
	            scanner.nextLine();
	            scanner.nextLine(); // Esperar a que el usuario presione Enter
	        }

	        scanner.close();

	}

}
