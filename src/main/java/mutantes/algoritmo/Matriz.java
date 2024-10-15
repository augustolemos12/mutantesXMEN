package mutantes.algoritmo;


import mutantes.exception.MatrizInvalida;

public class Matriz {
    private static final String[] SECUENCIAS_MUTANTES = {"AAAA", "CCCC", "GGGG", "TTTT"};       //Secuencias de ADN aceptadas
    public int N;                                                                               //Tamaño de la matriz que se recibe
    private static int filDirs[] = {-1, -1, -1, 0, 0, 1, 1, 1};                                 //Las 8 direciones posibles en las que nos podemos mover, siendo -1 atrás, 1 adelante y 0 nada.
    static int colDirs[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    private int count = 0;                                                                      //Contador de cuántas secuencias encontradas van


    public boolean verificarADN(String[] adn) {
        // Validación de array null
        if (adn == null) {
            System.out.println("La matriz es null.");
            throw new NullPointerException("El array de ADN no puede ser null.");
        }

        int N = adn.length;

        // Validación de array vacío
        if (N == 0) {
            System.out.println("La matriz está vacía.");
            return false;
        }

        // Validación de matriz NxN
        for (String fila : adn) {
            if (fila.length() != N) {
                System.out.println("La matriz NO es de NxN.");
                return false;
            }
        }

        // Validación de caracteres permitidos
        for (String fila : adn) {
            if (!fila.matches("[ACGT]+")) {
                System.out.println("La matriz posee caracteres indebidos.");
                return false;
            }
        }

        return true; // Si pasa todas las validaciones
    }

    //Verifica que no nos salgamos de los límites
    boolean esSeguro(int i, int j) {
        if (i >= 0 && i <= (N - 1) && j >= 0 && j <= N - 1) {
            return true;
        }
        return false;
    }

    //Recorre cada elemento de la matriz y lleva el conteo de cuántas secuencias encontradas en la misma van
    public boolean analizarADN(String[] adn) {

        // Verificar el ADN antes de analizarlo
        if (!verificarADN(adn)) {
            throw new MatrizInvalida("MATRIZ INVÁLIDA");
        }

        N = adn.length;

        for (String mutantADNSec : SECUENCIAS_MUTANTES) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    buscaYCuentaSecuencias(adn, i, j, mutantADNSec);

                    if (count >= 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void buscaYCuentaSecuencias(String[] adn, int fila, int col, String secuencia) {

        //Verificamos si el elemento/caracter en el que estamos es igual al primer caracter de la secuencia que buscamos
        if (adn[fila].charAt(col) != secuencia.charAt(0)) {
            return;
        }

        int longitud = secuencia.length();

        //Buscamos en las 8 direcciones posibles
        for (int dir = 0; dir < 8; ++dir) {
            int rowDir = fila + filDirs[dir];
            int colDir = col + colDirs[dir];
            int i;

            for (i = 1; i <= (longitud-1); i++) {

                if (!esSeguro(rowDir, colDir)) {
                    break;
                }

                if (!(adn[rowDir].charAt(colDir) == secuencia.charAt(i))) {
                    break;
                }

                rowDir = rowDir + filDirs[dir];
                colDir = colDir + colDirs[dir];
            }

            if (i == longitud) {
                count++;

                if (count >= 2) {
                    return;
                }
            }
        }
    }
}
