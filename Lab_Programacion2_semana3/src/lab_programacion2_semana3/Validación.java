package lab_programacion2_semana3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Color;

public class Validación {
    
    public class ref {
        public int x;
        public int y;

        public ref(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public boolean MoviemientoValido(int [][] matriz, int fila, int colm, int num){
        
            for (int i = 0; i < 9; i++) {
                if (matriz[fila][i] == num) 
                    return false;
            }
            
            for (int i = 0; i < 9; i++) {
                if (matriz[i][colm] == num) 
                    return false;
            }
            
            int startFila = fila - fila % 3;
            int startColm = colm - colm % 3;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (matriz[startFila + i][startColm + j] == num) 
                            return false;
                    }
            }     

            return true;
    }  
    public List<ref> Colisiones(int[][] matriz, int fila, int colm, int num) {
        List<ref> colision = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (matriz[fila][i] == num && i != colm) colision.add(new ref(fila, i));
        }

        for (int i = 0; i < 9; i++) {
            if (matriz[i][colm] == num && i != fila) colision.add(new ref(i, colm));
        }

        int startFila = fila - fila % 3;
        int startColm = colm - colm % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int f = startFila + i;
                int c = startColm + j;
                if (matriz[f][c] == num && (f != fila || c != colm)) colision.add(new ref(f, c));
            }
        }

        return colision;
    }
    
    public void highlightConflicts(List<ref> colision, JTextField[][] casilla) {
    for (ref p : colision) {
        casilla[p.x][p.y].setBackground(Color.RED);
        }
    }
    
}


