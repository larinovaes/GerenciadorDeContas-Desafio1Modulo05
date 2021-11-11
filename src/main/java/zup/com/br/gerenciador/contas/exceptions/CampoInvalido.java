package zup.com.br.gerenciador.contas.exceptions;

public class CampoInvalido extends RuntimeException {

    private String mensagem;
    private String campo;

    public CampoInvalido(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
