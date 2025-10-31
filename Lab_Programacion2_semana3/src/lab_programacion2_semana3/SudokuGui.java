/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Cantarero
 */
public final class SudokuGui extends JFrame {

    public JTextField[][] matriz, matrizRespuestas;
    public int[][] tablero;
    public int[][] solucion;
    public Random aleatorio = new Random();
    public int dificultad = 0;

    public SudokuGui() {
        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // ===== PANEL MENÚ DE DIFICULTAD =====
        JPanel panelOpcion = new JPanel();
        panelOpcion.setLayout(null);
        panelOpcion.setBounds(0, 0, 630, 630);
        panelOpcion.setBackground(Color.WHITE);
        add(panelOpcion);

        JLabel titulo = new JLabel("SUDOKU", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setBounds(100, 50, 300, 60);
        panelOpcion.add(titulo);

        JButton BtnFacil = new JButton("Fácil");
        BtnFacil.setBounds(100, 150, 300, 50);
        panelOpcion.add(BtnFacil);

        JButton BtnMedio = new JButton("Medio");
        BtnMedio.setBounds(100, 250, 300, 50);
        panelOpcion.add(BtnMedio);

        JButton BtnDificil = new JButton("Difícil");
        BtnDificil.setBounds(100, 350, 300, 50);
        panelOpcion.add(BtnDificil);

        // ===== PANEL DE JUEGO =====
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 20, 630, 630);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(Color.BLACK, 3));
        add(panel);
        panel.setVisible(false);

        matriz = new JTextField[9][9];
        matrizRespuestas = new JTextField[9][9];
        tablero = new int[9][9];
        solucion = new int[9][9];
        int size = 60;

        generarSudoku(solucion); // genera tablero completo correcto

        // ===== MATRIZ RESPUESTA (opcionalmente visible para depurar) =====
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrizRespuestas[i][j] = new JTextField(String.valueOf(solucion[i][j]));
                matrizRespuestas[i][j].setBounds(j * size, i * size, size, size);
                matrizRespuestas[i][j].setOpaque(false);
                matrizRespuestas[i][j].setHorizontalAlignment(JTextField.CENTER);
                matrizRespuestas[i][j].setFont(new Font("Arial", Font.BOLD, 22));
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;
                matrizRespuestas[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                matrizRespuestas[i][j].setVisible(false); // ocultas
                panel.add(matrizRespuestas[i][j]);
            }
        }

        // ===== MATRIZ DE JUEGO =====
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matriz[i][j] = new JTextField("");
                matriz[i][j].setBounds(j * size, i * size, size, size);
                matriz[i][j].setOpaque(true);
                matriz[i][j].setBackground(Color.WHITE);
                matriz[i][j].setHorizontalAlignment(JTextField.CENTER);
                matriz[i][j].setFont(new Font("Arial", Font.BOLD, 22));
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;
                matriz[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));

                final int fila = i;
                final int col = j;

                matriz[i][j].addActionListener(e -> {
                    String texto = matriz[fila][col].getText().trim();
                    if (!texto.isEmpty()) {
                        try {
                            int num = Integer.parseInt(texto);
                            if (num != solucion[fila][col]) {
                                matriz[fila][col].setBackground(Color.PINK);
                            } else {
                                matriz[fila][col].setBackground(Color.WHITE);
                            }
                        } catch (NumberFormatException ex) {
                            matriz[fila][col].setText("");
                        }
                    }
                });

                panel.add(matriz[i][j]);
            }
        }

        // ===== EVENTOS DE BOTONES =====
        BtnFacil.addActionListener(e -> {
            dificultad = 1;
            panel.setVisible(true);
            panelOpcion.setVisible(false);
            Dificultadinicio();
            setSize(700, 700);
            setLocationRelativeTo(null);
        });

        BtnMedio.addActionListener(e -> {
            dificultad = 2;
            panel.setVisible(true);
            panelOpcion.setVisible(false);
            Dificultadinicio();
            setSize(700, 700);
            setLocationRelativeTo(null);
        });

        BtnDificil.addActionListener(e -> {
            dificultad = 3;
            panel.setVisible(true);
            panelOpcion.setVisible(false);
            Dificultadinicio();
            setSize(700, 700);
            setLocationRelativeTo(null);
        });

        setVisible(true);
    }

    // ===== DIFICULTAD =====
    public void Dificultadinicio() {
        int visiblesInicio = 0;
        if (dificultad == 1) {
            visiblesInicio = 45; // fácil
        } else if (dificultad == 2) {
            visiblesInicio = 35; // medio
        } else if (dificultad == 3) {
            visiblesInicio = 25; // difícil
        }

        for (int i = 0; i < visiblesInicio; i++) {
            int fila = aleatorio.nextInt(9);
            int col = aleatorio.nextInt(9);
            if (matriz[fila][col].getText().isEmpty()) {
                matriz[fila][col].setText(String.valueOf(solucion[fila][col]));
                matriz[fila][col].setBackground(new Color(220, 220, 220));
                matriz[fila][col].setEditable(false);
            } else {
                i--; // si ya está llena, intenta otra celda
            }
        }
    }

    // ===== GENERADOR DE SUDOKU =====
    private boolean generarSudoku(int[][] tablero) {
        return rellenarSudoku(tablero, 0, 0);
    }

    private boolean rellenarSudoku(int[][] tablero, int fila, int col) {
        if (fila == 9) return true;
        if (col == 9) return rellenarSudoku(tablero, fila + 1, 0);

        int[] numeros = new int[9];
        for (int i = 0; i < 9; i++) numeros[i] = i + 1;
        // Mezclar los números aleatoriamente
        for (int i = 0; i < 9; i++) {
            int r = i + aleatorio.nextInt(9 - i);
            int tmp = numeros[i];
            numeros[i] = numeros[r];
            numeros[r] = tmp;
        }

        for (int num : numeros) {
            if (ValidadorSudoku.colocacionValida(tablero, fila, col, num)) {
                tablero[fila][col] = num;
                if (rellenarSudoku(tablero, fila, col + 1)) {
                    return true;
                }
                tablero[fila][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGui::new);
    }
}

// ===== CLASE AUXILIAR =====
class ValidadorSudoku {
    public static boolean colocacionValida(int[][] tablero, int fila, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i] == num || tablero[i][col] == num) {
                return false;
            }
        }
        int subFila = (fila / 3) * 3;
        int subCol = (col / 3) * 3;
        for (int i = subFila; i < subFila + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                if (tablero[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}