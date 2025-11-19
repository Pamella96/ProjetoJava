package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginGUI {
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoLimpar;

    public LoginGUI(){
        configurarTela();
        inicializarComponentes();
    }

    private void configurarTela(){
       setTitle("Sistema de Gerenciamento de Alunos - Login");
       setSize(300, 200);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setResizable(false);
    }

    // Inicialização adicional dos componentes, se necessário
    
    private void inicializarComponentes(){
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titulo = new JLabel("Login do sistema", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titulo, gbc);

        // Label e Campo de Login
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Login:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        campoLogin = new JTextField(15);
        painel.add(campoLogin, gbc);

        // Label e Campo de Senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        campoSenha = new JPasswordField(15);
        painel.add(campoSenha, gbc);

        //Painel de botoes
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel painelBotoes = new JPanel(new FlowLayout());

        botaoEntrar = new JButton("Entrar");
        botaoLimpar = new JButton("Limpar");

        painelBotoes.add(botaoEntrar);
        painelBotoes.add(botaoLimpar);
        painel.add(painelBotoes, gbc);
        add(painel);

    }

    // metodos para acesso aos componentes
    public String getLogin() {
        return campoLogin.getText();
    }
    
    public String getSenha() {
        return new String(campoSenha.getPassword());
    }

    // métodos para adicionar ActionListeners aos botões
    public void

}
