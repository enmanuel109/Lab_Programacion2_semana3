/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab_programacion2_semana3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
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
public class SudokuGui extends JFrame {

    private JTextField[][] matriz;

    public SudokuGui() {
        setTitle("Sudoku");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // layout absoluto

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20, 20, 630, 630);
        panel.setBackground(Color.WHITE);
        add(panel);

        matriz = new JTextField[9][9];
        int size = 70;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matriz[i][j] = new JTextField("", SwingConstants.CENTER);
                matriz[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
                matriz[i][j].setHorizontalAlignment(JTextField.CENTER);
                matriz[i][j].setMargin(new java.awt.Insets(0, 0, 0, 0));
                matriz[i][j].setOpaque(true);
                matriz[i][j].setBackground(new Color(180, 200, 255));

                // Borde grueso solo cada 3 celdas (top, left)
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = 1; // igual para todas las filas
                int right = 1;  // igual para todas las columnas

                matriz[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                panel.add(matriz[i][j]);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGui::new);
    }
}
