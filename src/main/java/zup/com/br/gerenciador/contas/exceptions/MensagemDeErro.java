package zup.com.br.gerenciador.contas.exceptions;

public class MensagemDeErro {

    private String mensagem;
    private String campo;

    public MensagemDeErro() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
