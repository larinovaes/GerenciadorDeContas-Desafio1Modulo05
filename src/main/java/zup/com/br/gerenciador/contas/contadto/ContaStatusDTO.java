package zup.com.br.gerenciador.contas.contadto;

import zup.com.br.gerenciador.contas.enums.Status;

public class ContaStatusDTO {

    private Status status;

    public ContaStatusDTO() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
