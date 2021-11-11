package zup.com.br.gerenciador.contas.exceptions;

public class MensagemGenerica {

    private String mensagem;

    public MensagemGenerica(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
