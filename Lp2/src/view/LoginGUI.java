package view;

import controller.LoginController;

import javax.swing.*;

/**
 * Tela de login do sistema.
 */
public class LoginGUI extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private LoginController controller;

    public LoginGUI() {
        super("Login");
        controller = new LoginController(this);

        setLayout(null);
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Login:");
        l1.setBounds(20, 20, 80, 25);
        add(l1);

        txtLogin = new JTextField();
        txtLogin.setBounds(100, 20, 150, 25);
        add(txtLogin);

        JLabel l2 = new JLabel("Senha:");
        l2.setBounds(20, 60, 80, 25);
        add(l2);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 60, 150, 25);
        add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(40, 110, 90, 30);
        btnEntrar.addActionListener(e ->
            controller.autenticar(txtLogin.getText(), new String(txtSenha.getPassword()))
        );
        add(btnEntrar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(150, 110, 90, 30);
        btnLimpar.addActionListener(e -> {
            txtLogin.setText("");
            txtSenha.setText("");
        });
        add(btnLimpar);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
