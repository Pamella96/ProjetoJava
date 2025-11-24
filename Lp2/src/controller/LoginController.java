package controller;

import view.AlunoGUI;
import view.LoginGUI;

/**
 * Responsável pela lógica do login.
 */
public class LoginController {

    private LoginGUI view;

    public LoginController(LoginGUI view) {
        this.view = view;
    }

    public void autenticar(String login, String senha) {
        if (login.equals("aluno") && senha.equals("2025")) {
            new AlunoGUI().setVisible(true);
            view.dispose();
        } else {
            view.mostrarMensagem("Login ou senha incorretos.");
        }
    }
}
