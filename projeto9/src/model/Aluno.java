package projeto9.src.model;

public class Aluno {
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

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getTelefone() { return telefone; }
    public String getCurso() { return curso; }
    
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
