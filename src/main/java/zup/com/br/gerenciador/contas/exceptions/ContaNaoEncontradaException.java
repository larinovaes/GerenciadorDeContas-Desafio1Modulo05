package zup.com.br.gerenciador.contas.exceptions;

public class ContaNaoEncontradaException extends RuntimeException{

    private Integer idConta;


    public ContaNaoEncontradaException(Integer idConta) {
        this.idConta = idConta;
    }

    public Integer getIdConta() {
        return idConta;
    }
}
