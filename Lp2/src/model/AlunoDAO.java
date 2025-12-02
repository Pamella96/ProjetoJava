package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Classe responsável por armazenar e salvar alunos.
public class AlunoDAO {

    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void adicionar(Aluno a) {
        alunos.add(a);
    }

    public ArrayList<Aluno> listar() {
        return alunos;
    }

    public void salvarEmArquivo() {
        try (FileWriter fw = new FileWriter("alunos.txt")) {
            for (Aluno a : alunos) {
                fw.write(a.getNome() + ";" + a.getMatricula() + ";" +
                         a.getTelefone() + ";" + a.getCurso() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }
}
