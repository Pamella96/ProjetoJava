package view;

import controller.LoginController;

import javax.swing.*;

//Tela de login do sistema
public class LoginGUI extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private LoginController controller;
    private JButton btnEntrar;
    private JButton btnLimpar;

    public LoginGUI() {
        //configurações da janela
        super("Login");
        controller = new LoginController(this);

        setLayout(null);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //criando os componentes
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(20, 20, 80, 25);
        add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(100, 20, 150, 25);
        add(txtLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 60, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 60, 150, 25);
        add(txtSenha);

        //criando os botões
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(40, 110, 90, 30);
        btnEntrar.addActionListener(e ->
            controller.autenticar(txtLogin.getText(), new String(txtSenha.getPassword()))
        );
        add(btnEntrar);

        btnLimpar = new JButton("Limpar");
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
