package zup.com.br.gerenciador.contas.exceptions;

public class CamposInvalidos extends RuntimeException{
    private String mensagem;

    public CamposInvalidos(String message) {
        super(message);
    }
}
