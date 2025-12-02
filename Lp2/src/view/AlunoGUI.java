package view;

import controller.AlunoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de cadastro de alunos.
 */
public class AlunoGUI extends JFrame {

    public JTextField txtNome;
    public JTextField txtMatricula;
    public JTextField txtTelefone;
    public JComboBox<String> cmbCurso;  //lista suspensa/drop-down list
    public JTable tabela;

    private AlunoController controller;

    public AlunoGUI() {
        super("Cadastro de Alunos");
        controller = new AlunoController(this);

        setLayout(null);
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel l1 = new JLabel("Nome:");
        l1.setBounds(20, 20, 80, 25);
        add(l1);

        txtNome = new JTextField();
        txtNome.setBounds(120, 20, 200, 25);
        add(txtNome);

        JLabel l2 = new JLabel("Matrícula:");
        l2.setBounds(20, 60, 80, 25);
        add(l2);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(120, 60, 200, 25);
        add(txtMatricula);

        JLabel l3 = new JLabel("Telefone:");
        l3.setBounds(20, 100, 80, 25);
        add(l3);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(120, 100, 200, 25);
        add(txtTelefone);

        JLabel l4 = new JLabel("Curso:");
        l4.setBounds(20, 140, 80, 25);
        add(l4);

        cmbCurso = new JComboBox<>(new String[]{
                "Selecione", "Ciência da Computação",
                "Matemática", "Engenharia", "Física"
        });
        cmbCurso.setBounds(120, 140, 200, 25);
        add(cmbCurso);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(350, 40, 120, 30);
        btnLimpar.addActionListener(e -> controller.limparCampos());
        add(btnLimpar);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(350, 90, 120, 30);
        btnEnviar.addActionListener(e -> controller.cadastrar());
        add(btnEnviar);

        tabela = new JTable(new DefaultTableModel(
                new Object[][]{}, //vetor de vetores de objetos, cada vetor representa uma linha da tabela e os objetos são os valores das colunas
                new String[]{"Nome", "Matrícula", "Telefone", "Curso"}
        ));

        JScrollPane scroll = new JScrollPane(tabela); //adiciona barra de rolagem à tabela se necessário ou ultrapassar o tamanho da área visível
        scroll.setBounds(20, 200, 540, 140);
        add(scroll);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                controller.salvarAoSair();
            }
        });
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
