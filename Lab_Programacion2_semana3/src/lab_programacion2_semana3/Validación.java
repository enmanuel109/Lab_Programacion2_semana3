package lab_programacion2_semana3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Color;

public abstract class Validación {

    public class ref {

        public int x;
        public int y;

        public ref(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean MovimientoValido(JTextField[][] matriz, int fila, int colm, int num) {
    for (int i = 0; i < 9; i++) {
        String texto = matriz[fila][i].getText();
        if (!texto.isEmpty() && Integer.parseInt(texto) == num) {
            return false; 
        }
    }

    for (int i = 0; i < 9; i++) {
        String texto = matriz[i][colm].getText();
        if (!texto.isEmpty() && Integer.parseInt(texto) == num) {
            return false; 
        }
    }

    int startFila = fila - fila % 3;
    int startColm = colm - colm % 3;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            String texto = matriz[startFila + i][startColm + j].getText();
            if (!texto.isEmpty() && Integer.parseInt(texto) == num) {
                return false;
            }
        }
    }

    return true;
}


    public void highlightConflicts(List<ref> colision, JTextField[][] casilla) {
        for (ref p : colision) {
            casilla[p.x][p.y].setBackground(Color.RED);
        }
    }

}
