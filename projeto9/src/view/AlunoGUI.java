package view;

import javax.swing.*;

public class AlunoGUI extends JFrame {
    public AlunoGUI() {
        super("Cadastro de Alunos");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Apenas um texto temporário para você ver que funcionou
        add(new JLabel("Tela de Cadastro (Em construção)", SwingConstants.CENTER));
    }
}
