package Juegodelavida;

// declaramos la clase tablero la cual contendra los metodos necesarios para calcular las siguientes generaciones,
//e impresion de tablero
public class Tablero {
	private int nf;  //declaracion de variable "nf" numero de filas
    private int nc; //declaracion de variable "nc" numero de columnas
    private boolean[][] celulas; //declaracion de matriz para representar las celulas
    private boolean[][] generacionAnterior;
    public int contador = 0;
    
    //constructor 
    public Tablero(int nf, int nc) {
        this.nf = nf;
        this.nc = nc;
        this.celulas = new boolean[nf][nc];
        this.generacionAnterior = new boolean[nf][nc];
        this.contador = contador;
    }
   // metodo setCelulaViva para indicar la posicion de las celulas vivas durante la primer generacion.
    public void setCelulaViva(int fila, int columna) {
        celulas[fila][columna] = true;
    }

    //metodo calcularSiguienteGeneracion
    public void calcularSiguienteGeneracion() {
        boolean[][] nuevaGeneracion = new boolean[nf][nc];
        

        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);
                if (celulas[i][j]) {
                    if (vecinosVivos == 2 || vecinosVivos == 3) {
                        nuevaGeneracion[i][j] = true;
                    }
                } else {
                    if (vecinosVivos == 3) {
                        nuevaGeneracion[i][j] = true;
                    }
                }
            }
        }

        // Comprobar si la nueva generación es igual a la generación anterior
        if (compararGeneraciones(celulas, generacionAnterior)) {
            
            contador ++;
        }

        celulas = nuevaGeneracion;

        // Almacenar la generación actual como generación anterior
        for (int i = 0; i < nf; i++) {
            System.arraycopy(celulas[i], 0, generacionAnterior[i], 0, nc);
        }
    }

    // Método para comparar dos generaciones
    private boolean compararGeneraciones(boolean[][] gen1, boolean[][] gen2) {
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                if (gen1[i][j] != gen2[i][j]) {
                    return false; // Hay al menos un cambio
                }
            }
        }
        return true; // Las generaciones son idénticas
    }
    

    // Agregar un método para verificar si no quedan células vivas
    public boolean noQuedanCelulasVivas() {
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                if (celulas[i][j]) {
                    return false; // Si encuentra al menos una célula viva, retorna false
                }
            }
        }
        return true; // Si no encuentra células vivas, retorna true
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
	public int getGeneracionesSinCambios() {
		return contador;
	}
}