package controller;

import model.Aluno;
import model.AlunoDAO;
import view.AlunoGUI;

import javax.swing.table.DefaultTableModel;

//Controla a tela de cadastro de alunos
public class AlunoController {

    private AlunoGUI view;
    private AlunoDAO dao = new AlunoDAO();

    public AlunoController(AlunoGUI view) {
        this.view = view; //composição da view no controller
    }

    public void limparCampos() {
        view.txtNome.setText("");
        view.txtMatricula.setText("");
        view.txtTelefone.setText("");
        view.cmbCurso.setSelectedIndex(0);
    }

    public void cadastrar() {
        try {

        //obtendo os dados da view
        String nome = view.txtNome.getText();
        String mat = view.txtMatricula.getText();
        String tel = view.txtTelefone.getText();
        String curso = view.cmbCurso.getSelectedItem().toString(); //assumindo que o item selecionado é uma String
        
        //metodo de validação de campos
        validarCampos(nome, mat, tel, curso);

    
        //criação do objeto e persistencia
        Aluno a = new Aluno(nome, mat, tel, curso);
        dao.adicionar(a); //esse metodo adiciona o aluno na lista interna do DAO
    // persiste imediatamente em arquivo para evitar perda de dados se o app for fechado
    dao.salvarEmArquivo();

        atualizarTabela();

        view.mostrarMensagem("DADOS CADASTRADOS COM SUCESSO!");
        limparCampos();
      
    }  catch (IllegalArgumentException e) { //captura erros de validação pelo método validarCampos
        view.mostrarMensagem("Erro de validação: " + e.getMessage());
        e.printStackTrace();
    } catch (NullPointerException e) {
        view.mostrarMensagem("Erro a lista de cursos não foi inicializada.");
        e.printStackTrace();
    } catch (Exception e) { //captura qualquer outra exceção
        view.mostrarMensagem("Erro ao cadastrar aluno." + e.getMessage());
        e.printStackTrace(); //imprime o rastreamento da pilha para depuração
    }
}
    

    private void validarCampos(String nome, String mat, String tel, String curso) {
        // Normaliza entradas (protege contra valores nulos e espaços em branco)
        if (nome == null) nome = "";
        if (mat == null) mat = "";
        if (tel == null) tel = "";
        if (curso == null) curso = "";

        nome = nome.trim();
        mat = mat.trim();
        tel = tel.trim();
        curso = curso.trim();

        // Verifica campo obrigatório
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        // Permite letras (qualquer alfabeto), espaços, hífen e apóstrofo
        if (!nome.matches("[\\p{L}\\s\\-']+")) {
            throw new IllegalArgumentException("Nome inválido. Use apenas letras, espaços, '-' ou '\''.");
        }

        if (mat.isEmpty()) {
            throw new IllegalArgumentException("Matrícula é obrigatória.");
        }
        if (!mat.matches("\\d+")) { //verifica se a matrícula contém apenas dígitos numéricos
            throw new IllegalArgumentException("Matrícula inválida. Use apenas números.");
        }

        // Aceita telefones formatados: remove tudo que não for dígito e valida o tamanho
        String telDigits = tel.replaceAll("\\D", "");
        if (telDigits.isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
        if (!telDigits.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Telefone inválido. Use apenas números com 10 ou 11 dígitos.");
        }

        if (curso.equals("Selecione") || curso.isEmpty()) { //verifica se o curso foi selecionado
            throw new IllegalArgumentException("Selecione um curso válido.");
        }
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) view.tabela.getModel();
        model.setRowCount(0);

        for (Aluno a : dao.listar()) {
            model.addRow(new Object[]{
                a.getNome(), a.getMatricula(), a.getTelefone(), a.getCurso()
            });
        }
    }

    public void salvarAoSair() {
        dao.salvarEmArquivo();
    }
}
