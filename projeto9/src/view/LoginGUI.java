package view;


import javax.swing.*;

import controller.LoginController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    // Componentes da tela
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnLimpar;
    private LoginController controller;

    public LoginGUI() {
        // Configurações da Janela
        super("Tela de Login"); //titulo da janela do construtor do JFrame
        controller = new LoginController();
        
        
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(null); // Layout manual coordenadas x,y

        // Criando os Rótulos/Labels
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(50, 30, 100, 20); // x, y, largura, altura
        add(lblLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 80, 100, 20);
        add(lblSenha);

        // Criando os Campos de Texto
        txtLogin = new JTextField();
        txtLogin.setBounds(50, 50, 230, 30);
        add(txtLogin);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(50, 100, 230, 30);
        add(txtSenha);

        // criando os Botões
        btnLimpar = new JButton("LIMPAR");
        btnLimpar.setBounds(50, 150, 100, 30);
        add(btnLimpar);

        btnEntrar = new JButton("ENTRAR");
        btnEntrar.setBounds(180, 150, 100, 30);
        btnEntrar.addActionListener(e -> controller.autenticar(txtLogin.getText(), new String(txtSenha.getPassword())));
        add(btnEntrar);



        
    }
    

}