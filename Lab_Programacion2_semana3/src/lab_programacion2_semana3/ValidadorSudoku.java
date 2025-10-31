/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ValidadorSudoku {
    
    public static boolean colocacionValida(int[][] tablero, int fila, int columna, int numero) {
        
        // Verificar en la fila
        for (int i = 0; i < 9; i++){
            if (i != columna && tablero[fila][i] == numero) {
                return false;
            }
        }

        // Verificar en la columna
        for (int i = 0; i < 9; i++) {
            if (i != fila && tablero[i][columna] == numero) {
                return false;
            }
        }

        // Verificar en la subcuadrícula 3x3
        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;

        for (int i = inicioFila; i < inicioFila + 3; i++) {
            for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                if ((i != fila || j != columna) && tablero[i][j] == numero) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public static boolean tableroSinErrores(int[][] tablero) {
        
        // Verificar todas las filas
        for (int fila = 0; fila < 9; fila++) {
            boolean[] numerosVistos = new boolean[10];
            for (int columna = 0; columna < 9; columna++) {
                int valor = tablero[fila][columna];
                if (valor == 0) {
                    continue; // ignorar casillas vacías
                }
                if (numerosVistos[valor]) {
                    return false; // número repetido
                }
                numerosVistos[valor] = true;
            }
        }

        // Verificar todas las columnas
        for (int columna = 0; columna < 9; columna++) {
            boolean[] numerosVistos = new boolean[10];
            for (int fila = 0; fila < 9; fila++) {
                int valor = tablero[fila][columna];
                if (valor == 0) {
                    continue;
                }
                if (numerosVistos[valor]) {
                    return false;
                }
                
                numerosVistos[valor] = true;
            }
        }

        // Verificar todas las subcuadrículas 3x3
        for (int bloqueFila = 0; bloqueFila < 3; bloqueFila++) {
            for (int bloqueColumna = 0; bloqueColumna < 3; bloqueColumna++) {
                boolean[] numerosVistos = new boolean[10];
                for (int i = bloqueFila * 3; i < bloqueFila * 3 + 3; i++) {
                    for (int j = bloqueColumna * 3; j < bloqueColumna * 3 + 3; j++) {
                        int valor = tablero[i][j];
                        if (valor == 0) {
                            continue;
                        }
                        if (numerosVistos[valor]) {
                            return false;
                        }
                        
                        numerosVistos[valor] = true;
                    }
                }
            }
        }

        return true;
    }
    
    public static List<String> obtenerErrores(int[][] tablero) {
        List<String> conflictos = new ArrayList<>();

        // Revisar filas
        for (int fila = 0; fila < 9; fila++) {
            int[] posicionPrimeraColumna = new int[10];
            for (int columna = 0; columna < 9; columna++) {
                int valor = tablero[fila][columna];
                if (valor == 0) {
                    continue;
                }
                if (posicionPrimeraColumna[valor] == 0) {
                    posicionPrimeraColumna[valor] = columna + 1;
                }else {
                    conflictos.add(String.format("Número %d repetido en fila %d (columnas %d y %d)",
                            valor, fila + 1, posicionPrimeraColumna[valor], columna + 1));
                }
            }
        }

        // Revisar columnas
        for (int columna = 0; columna < 9; columna++) {
            int[] posicionPrimeraFila = new int[10];
            for (int fila = 0; fila < 9; fila++) {
                int valor = tablero[fila][columna];
                if (valor == 0) {
                    continue;
                }
                if (posicionPrimeraFila[valor] == 0) {
                    posicionPrimeraFila[valor] = fila + 1;
                }else {
                    conflictos.add(String.format("Número %d repetido en columna %d (filas %d y %d)",
                            valor, columna + 1, posicionPrimeraFila[valor], fila + 1));
                }
            }
        }

        // Revisar subcuadrículas
        for (int bloqueFila = 0; bloqueFila < 3; bloqueFila++) {
            for (int bloqueColumna = 0; bloqueColumna < 3; bloqueColumna++) {
                int[] filaPrimeraOcurrencia = new int[10];
                int[] columnaPrimeraOcurrencia = new int[10];
                for (int i = bloqueFila * 3; i < bloqueFila * 3 + 3; i++) {
                    for (int j = bloqueColumna * 3; j < bloqueColumna * 3 + 3; j++) {
                        int valor = tablero[i][j];
                        if (valor == 0) {
                            continue;
                        }
                        if (filaPrimeraOcurrencia[valor] == 0) {
                            filaPrimeraOcurrencia[valor] = i + 1;
                            columnaPrimeraOcurrencia[valor] = j + 1;
                        } else {
                            conflictos.add(String.format(
                                    "Número %d repetido en subcuadro [%d,%d]: posiciones (%d,%d) y (%d,%d)",
                                    valor, bloqueFila + 1, bloqueColumna + 1,
                                    filaPrimeraOcurrencia[valor], columnaPrimeraOcurrencia[valor],
                                    i + 1, j + 1));
                        }
                    }
                }
            }
        }

        return conflictos;
    }
}
