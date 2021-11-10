package zup.com.br.gerenciador.contas.contadto;

import zup.com.br.gerenciador.contas.enums.Status;

public class ResumoContaDTO {

    private int id;
    private String Nome;
    private double valor;
    private Status status;

    public ResumoContaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
