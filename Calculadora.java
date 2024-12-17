package aula11;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora extends JFrame {
    private JTextField textFind1, textFind2, resultFind;
    private JButton addButton, subtractButton, multiplyButton, divideButton;

    public Calculadora() {
        // Configurações da janela
        setTitle("Calculadora");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        // Inicialização dos campos de texto
        textFind1 = new JTextField();
        textFind2 = new JTextField();
        resultFind = new JTextField();
        resultFind.setEditable(false);

        // Inicialização dos botões
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        // Adiciona ActionListener aos botões
        addButton.addActionListener(new ButtonClickListener());
        subtractButton.addActionListener(new ButtonClickListener());
        multiplyButton.addActionListener(new ButtonClickListener());
        divideButton.addActionListener(new ButtonClickListener());

        // Adiciona componentes à janela
        add(new JLabel("Número 1:"));
        add(textFind1);
        add(new JLabel("Número 2:"));
        add(textFind2);
        add(new JLabel("Resultado:"));
        add(resultFind);

        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);

        // Torna a janela visível
        setVisible(true);
    }

    // Classe interna para tratar eventos de clique nos botões
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtém os números dos campos de texto
                double num1 = Double.parseDouble(textFind1.getText());
                double num2 = Double.parseDouble(textFind2.getText());
                double result = 0;

                // Realiza a operação correspondente ao botão clicado
                if (e.getSource() == addButton) {
                    result = num1 + num2;
                } else if (e.getSource() == subtractButton) {
                    result = num1 - num2;
                } else if (e.getSource() == multiplyButton) {
                    result = num1 * num2;
                } else if (e.getSource() == divideButton) {
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(null, "Erro: Divisão por zero!");
                        return;
                    }
                    result = num1 / num2;
                }

                // Exibe o resultado no campo de texto de resultado
                resultFind.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                // Exibe mensagem de erro se os valores de entrada não forem números válidos
                JOptionPane.showMessageDialog(null, "Erro: Insira um número válido!");
            }
        }
    }

    public static void main(String[] args) {
        // Cria e exibe a calculadora na thread de despacho de eventos do Swing
        SwingUtilities.invokeLater(() -> new Calculadora());
    }
}
