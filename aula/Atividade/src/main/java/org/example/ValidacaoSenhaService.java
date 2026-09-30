package org.example;

public class ValidacaoSenhaService {

    public boolean validarSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            return false;
        }
        if (senha.length() < 10 || senha.length() > 12) {
            return false;
        }

        boolean possuiNumero = senha.matches(".*\\d.*");
        boolean possuiLetra = senha.matches(".*[a-zA-Z].*");
        boolean possuiEspecial = senha.matches(".*[!@#$%&*()].*");

        return possuiNumero && possuiLetra && possuiEspecial;
    }

    public boolean autenticar(Usuario usuarioBD, String loginInformado, String senhaInformada) {
        if (loginInformado == null || loginInformado.isBlank() ||
                senhaInformada == null || senhaInformada.isBlank()) {
            return false;
        }

        if (usuarioBD == null || !usuarioBD.getLogin().equals(loginInformado)) {
            return false;
        }

        if (usuarioBD.isBloqueado()) {
            return false;
        }

        if (!usuarioBD.getSenha().equals(senhaInformada)) {
            usuarioBD.registrarTentativaFalha();
            return false;
        }

        usuarioBD.resetarTentativas();
        return true;
    }
}