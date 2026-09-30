package ValidacaoTest;

import org.example.Usuario;
import org.example.ValidacaoSenhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidacaoSenhaServiceTest {

    private ValidacaoSenhaService service;
    private Usuario usuarioValido;

    @BeforeEach
    void setup() {
        service = new ValidacaoSenhaService();
        usuarioValido = new Usuario("admin", "Java@12345");
    }

    // --- VALIDAÇÃO DE SENHA ---

    @Test
    void deveAceitarSenhaValida() {
        assertTrue(service.validarSenha("Java@12345"));
    }

    @Test
    void deveRejeitarSenhaNulaOuVazia() {
        assertFalse(service.validarSenha(null));
        assertFalse(service.validarSenha("   "));
    }

    @Test
    void deveRejeitarSenhaCom9Caracteres() {
        assertFalse(service.validarSenha("Jav@12345"));
    }

    @Test
    void deveAceitarSenhaCom10Caracteres() {
        assertTrue(service.validarSenha("Java@12345"));
    }

    @Test
    void deveAceitarSenhaCom11Caracteres() {
        assertTrue(service.validarSenha("Java@123456"));
    }

    @Test
    void deveAceitarSenhaCom12Caracteres() {
        assertTrue(service.validarSenha("Java@1234567"));
    }

    @Test
    void deveRejeitarSenhaCom13Caracteres() {
        assertFalse(service.validarSenha("Java@12345678"));
    }

    @Test
    void deveRejeitarSenhaSemNumero() {
        assertFalse(service.validarSenha("Java@Testes"));
    }

    @Test
    void deveRejeitarSenhaSemLetra() {
        assertFalse(service.validarSenha("123456@7890"));
    }

    @Test
    void deveRejeitarSenhaSemCaractereEspecial() {
        assertFalse(service.validarSenha("Java123456"));
    }

    // --- AUTENTICAÇÃO E BLOQUEIO ---

    @Test
    void deveAutenticarUsuarioValido() {
        boolean resultado = service.autenticar(usuarioValido, "admin", "Java@12345");
        assertTrue(resultado);
    }

    @Test
    void deveRejeitarCamposNulosOuVaziosNaAutenticacao() {
        assertFalse(service.autenticar(usuarioValido, null, "Java@12345"));
        assertFalse(service.autenticar(usuarioValido, "admin", ""));
    }

    @Test
    void deveRejeitarUsuarioInexistenteOuLoginIncorreto() {
        assertFalse(service.autenticar(usuarioValido, "adminErrado", "Java@12345"));
        assertFalse(service.autenticar(null, "admin", "Java@12345"));
    }

    @Test
    void deveBloquearContaAposTresTentativasInvalidas() {
        assertFalse(service.autenticar(usuarioValido, "admin", "senha1"));
        assertFalse(usuarioValido.isBloqueado());

        assertFalse(service.autenticar(usuarioValido, "admin", "senha2"));
        assertFalse(usuarioValido.isBloqueado());

        assertFalse(service.autenticar(usuarioValido, "admin", "senha3"));
        assertTrue(usuarioValido.isBloqueado());

        assertFalse(service.autenticar(usuarioValido, "admin", "Java@12345"));
    }
}