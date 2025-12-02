package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Classe responsável por armazenar e salvar alunos.
public class AlunoDAO {

    private ArrayList<Aluno> alunos = new ArrayList<>();

    // Caminho do arquivo de persistência (relativo ao diretório de trabalho atual)
    private final String arquivo = "alunos.txt";

    public AlunoDAO() {
        // Ao instanciar, tenta carregar alunos existentes do arquivo
        File f = new File(arquivo);
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 4) {
                        Aluno a = new Aluno(parts[0], parts[1], parts[2], parts[3]);
                        alunos.add(a);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void adicionar(Aluno a) {
        alunos.add(a);
    }

    public ArrayList<Aluno> listar() {
        return alunos;
    }

    public void salvarEmArquivo() {
        File f = new File(arquivo);
        try (FileWriter fw = new FileWriter(f)) {
            for (Aluno a : alunos) {
                fw.write(a.getNome() + ";" + a.getMatricula() + ";" +
                         a.getTelefone() + ";" + a.getCurso() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
