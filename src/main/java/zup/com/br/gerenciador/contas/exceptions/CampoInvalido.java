package zup.com.br.gerenciador.contas.exceptions;

public class CamposInvalidos {
    private String mensagem;
    private String campo;

    public CamposInvalidos(String mensagem, String campo) {
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
