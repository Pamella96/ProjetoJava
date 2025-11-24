package controller;

import model.Aluno;
import model.AlunoDAO;
import view.AlunoGUI;

import javax.swing.table.DefaultTableModel;

/**
 * Controla o cadastro dos alunos.
 */
public class AlunoController {

    private AlunoGUI view;
    private AlunoDAO dao = new AlunoDAO();

    public AlunoController(AlunoGUI view) {
        this.view = view;
    }

    public void limparCampos() {
        view.txtNome.setText("");
        view.txtMatricula.setText("");
        view.txtTelefone.setText("");
        view.cmbCurso.setSelectedIndex(0);
    }

    public void cadastrar() {
        String nome = view.txtNome.getText();
        String mat = view.txtMatricula.getText();
        String tel = view.txtTelefone.getText();
        String curso = view.cmbCurso.getSelectedItem().toString();

        if (nome.isEmpty() || mat.isEmpty() || tel.isEmpty() || curso.equals("Selecione")) {
            view.mostrarMensagem("Preencha todos os campos.");
            return;
        }

        Aluno a = new Aluno(nome, mat, tel, curso);
        dao.adicionar(a);

        atualizarTabela();
        view.mostrarMensagem("DADOS CADASTRADOS COM SUCESSO!");
        limparCampos();
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
