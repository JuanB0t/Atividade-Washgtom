package org.example;

public class Usuario {
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