package Juegodelavida;

// declaramos la clase tablero la cual contendra los metodos necesarios para calcular las siguientes generaciones,
//e impresion de tablero
public class Tablero {
	private int nf;  //declaracion de variable "nf" numero de filas
    private int nc; //declaracion de variable "nc" numero de columnas
    private boolean[][] celulas; //declaracion de matriz para representar las celulas

    
    //constructor 
    public Tablero(int nf, int nc) {
        this.nf = nf;
        this.nc = nc;
        this.celulas = new boolean[nf][nc];
    }
   // metodo setCelulaViva para indicar la posicion de las celulas vivas durante la primer generacion.
    public void setCelulaViva(int fila, int columna) {
        celulas[fila][columna] = true;
    }

    //metodo calcularSiguienteGeneracion
    public void calcularSiguienteGeneracion() {
        boolean[][] nuevaGeneracion = new boolean[nf][nc];
      //bucle para recorrer tablero
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
            	//llamada al metodo contarVecinosVivos
                int vecinosVivos = contarVecinosVivos(i, j);
               //revision de aptitud de vivir de celulas(si tiene 2 vecinos o 3 sigue viviendo)
                if (celulas[i][j]) {
                    if (vecinosVivos == 2 || vecinosVivos == 3) {
                        nuevaGeneracion[i][j] = true;
                    }
                 // si esta muerta pero tiene 3 vecinos vive en la siguiente generacion   
                } else {
                    if (vecinosVivos == 3) {
                        nuevaGeneracion[i][j] = true;
                    }
                }
            }
        }

        celulas = nuevaGeneracion;
    }
     //metodo para contar vecinos vivos
    //al metodo se le pasan las coordenadas de la celula en la que se encuentra el cursor actualmente
    private int contarVecinosVivos(int fila, int columna) {
        int vecinosVivos = 0;
       // Arrays para definir desplazamientos en las 8 direcciones posibles (arriba, abajo, izquierda, derecha y diagonales)
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        //bucle para iterar atraves de los 8 desplazamientos y verificar los vecinos de la celula actual
        for (int i = 0; i < 8; i++) {
            int x = fila + dx[i];  //calcula fila del vecino
            int y = columna + dy[i]; //calcula columna del vecino

            //valida si las coordenadas estan dentro de los limites del tablero
            //valida si la celula esta viva
            if (x >= 0 && x < nf && y >= 0 && y < nc && celulas[x][y]) {
                vecinosVivos++;// el contador de vecinos aumenta si se cumplen las condiciones
            }
        }

        return vecinosVivos;
    }

    //metodo para imprimir el tablero
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();// se crea objeto StringBuilder para manipulacion de la cadena

        //bucles anidados para recorrer las filas(i)y las columnas(j)
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
            	//verificar si la celula esta viva o muerta y asignarle un caracter para representarlas
                if (celulas[i][j]) {
                    sb.append("O "); // Célula viva
                } else {
                    sb.append(". "); // Célula muerta
                }
            }
            //salto de linea para cambiar a la siguiente fila
            sb.append("\n");
        }

        //el objeto "sb" se regresa como cadena
        return sb.toString();
    }
}
