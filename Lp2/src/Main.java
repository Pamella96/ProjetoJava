import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class Main {
    public static void main(String[] args) {

        // Criação da Janela
        JFrame janela = new JFrame("Exemplo Swing no VS Code");

        janela.setSize(400, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout simples
        JPanel painel = new JPanel();
        painel.setLayout(new FlowLayout());

        // Componentes
        JLabel label = new JLabel("Olá, isso é Swing no VS Code!");
        JButton botao = new JButton("Clique aqui");

        painel.add(label);
        painel.add(botao);

        // Adiciona o painel na janela
        janela.add(painel);

        // Exibir
        janela.setVisible(true);
    }
}
