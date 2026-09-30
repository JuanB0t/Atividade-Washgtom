package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }

    public static class Usuario {
        private String login;
        private String senha;
        private int tentativasInvalidas = 0;
        private boolean bloqueado = false;

        public Usuario(String login, String senha) {
            this.login = login;
            this.senha = senha;
        }

        public String getLogin() { return login; }
        public String getSenha() { return senha; }
        public boolean isBloqueado() { return bloqueado; }

        // Controla o bloqueio após 3 tentativas (RF07)
        public void registrarTentativaFalha() {
            this.tentativasInvalidas++;
            if (this.tentativasInvalidas >= 3) {
                this.bloqueado = true;
            }
        }

        public void resetarTentativas() {
            this.tentativasInvalidas = 0;
        }
    }
}