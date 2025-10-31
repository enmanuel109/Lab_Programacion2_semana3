/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Cantarero
 */
public final class SudokuGui extends JFrame {

    public JTextField[][] matriz, matrizRespuestas;
    public Random aleatorio = new Random();
    public int dificultad = 0;

    public SudokuGui() {
        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //*opciones de dificultad
        JPanel panelOpcion = new JPanel();
        panelOpcion.setLayout(null);
        panelOpcion.setBounds(0, 0, 630, 630);
        panelOpcion.setBackground(Color.WHITE);
        add(panelOpcion);

        JButton BtnFacil = new JButton("Facil");
        BtnFacil.setBounds(90, 130, 300, 50);
        panelOpcion.add(BtnFacil);

        JButton BtnMedio = new JButton("Medio");
        BtnMedio.setBounds(90, 230, 300, 50);
        panelOpcion.add(BtnMedio);

        JButton BtnDificil = new JButton("Dificil");
        BtnDificil.setBounds(90, 330, 300, 50);
        panelOpcion.add(BtnDificil);
        //*Panel Juego
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 20, 630, 630);
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setVisible(false);

        matriz = new JTextField[9][9];
        int size = 70;
        matrizRespuestas = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int ramdon = aleatorio.nextInt(9) + 1;
                matrizRespuestas[i][j] = new JTextField(String.valueOf(ramdon), SwingConstants.CENTER);
                matrizRespuestas[i][j].setBounds(j * size, i * size, size, size);
                matrizRespuestas[i][j].setBackground(new Color(180, 200, 255));
                matrizRespuestas[i][j].setOpaque(false);
                matrizRespuestas[i][j].setHorizontalAlignment(JTextField.CENTER);
                matrizRespuestas[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
                matrizRespuestas[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
                matrizRespuestas[i][j].setBackground(Color.WHITE);
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;

                matrizRespuestas[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                panel.add(matrizRespuestas[i][j]).setVisible(false);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                matriz[i][j] = new JTextField("", SwingConstants.CENTER);
                matriz[i][j].setBounds(j * size, i * size, size, size);
                matriz[i][j].setBackground(new Color(180, 200, 255));
                matriz[i][j].setOpaque(false);
                matriz[i][j].setHorizontalAlignment(JTextField.CENTER);
                matriz[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
                matriz[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
                matriz[i][j].setBackground(Color.WHITE);
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;

                matriz[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                panel.add(matriz[i][j]);
            }
        }

        BtnFacil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultad = 2;
                setSize(700, 700);
                panel.setVisible(true);
                setLocationRelativeTo(null);

                panelOpcion.setVisible(false);
                Dificultadinicio();
            }

        });

        BtnMedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultad = 3;
                setSize(700, 700);
                setLocationRelativeTo(null);

                panel.setVisible(true);
                panelOpcion.setVisible(false);
                Dificultadinicio();
            }
        });

        BtnDificil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultad = 1;
                setSize(700, 700);
                setLocationRelativeTo(null);

                panel.setVisible(true);
                panelOpcion.setVisible(false);
                Dificultadinicio();
            }
        });
        setVisible(true);
    }

    public void Dificultadinicio() {
        int visiblesInicio = 0;

        if (dificultad == 1) {
            visiblesInicio = 40;
        } else if (dificultad == 2) {
            visiblesInicio = 45;
        } else if (dificultad == 3) {
            visiblesInicio = 31;
        }

        for (int i = 0; i < visiblesInicio; i++) {
            int ramdonfila = aleatorio.nextInt(9);
            int ramdonCol = aleatorio.nextInt(9);

            if (matriz[ramdonfila][ramdonCol].getText().isEmpty()) {
                matriz[ramdonfila][ramdonCol].setText(
                        matrizRespuestas[ramdonfila][ramdonCol].getText()
                );
                matriz[ramdonfila][ramdonCol].setVisible(true);
            } else {
                i--;
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGui::new);
    }
}
