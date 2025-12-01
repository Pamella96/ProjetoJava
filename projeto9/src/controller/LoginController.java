package controller;
import view.LoginGUI;
import view.AlunoGUI;

public class LoginController {
    private LoginGUI view;

    public LoginController(LoginGUI view) {
        this.view = view;
}

    // Lógica de controle de login pode ser implementada aqui
    public void autenticar(String login, String senha){
        if(login.equals("aluno") && senha.equals("2025")){
            new AlunoGUI().setVisible(true);
            view.dispose();
        }else{
            view.mostrarMensagem("Login ou senha incorretos.");
        }
    }
}