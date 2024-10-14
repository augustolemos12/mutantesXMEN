package mutantes.algoritmo;


public class Matriz {
    //Secuencias de ADN aceptadas
    private static final String[] SECUENCIAS_MUTANTES = {"AAAA", "CCCC", "GGGG", "TTTT"};
    //Tamaño de la matriz que se recibe
    public int N;
    //Las 8 direciones posibles en las que nos podemos mover, siendo -1 atrás, 1 adelante y 0 nada.
    private static int filDirs[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int colDirs[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    //Contador de cuántas secuencias encontradas van
    private int count = 0;

    //Verifica que no nos salgamos de los límites
    boolean esSeguro(int i, int j) {
        if (i >= 0 && i <= (N - 1) && j >= 0 && j <= N - 1) {
            return true;
        }
        return false;
    }

    //Recorre cada elemento de la matriz y lleva el conteo de cuántas secuencias encontradas en la misma van
    public boolean analizarADN(String[] adn) {
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
