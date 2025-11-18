package model;
import java.io.Serializable;


package model;

import java.io.Serializable;

public class Aluno implements Serializable {
    // Definindo o serialVersionUID é uma boa prática para serialização
    private static final long serialVersionUID = 1L; 

    private String nome;
    private String matricula;
    private String telefone;
    private String curso;

   
    public Aluno(String nome, String matricula, String telefone, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.curso = curso;
    }

    // --- Métodos Getters ---

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCurso() {
        return curso;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }


    @Override
    public String toString() {
        return "Aluno{" +
               "nome='" + nome + '\'' +
               ", matricula='" + matricula + '\'' +
               ", telefone='" + telefone + '\'' +
               ", curso='" + curso + '\'' +
               '}';
    }
}