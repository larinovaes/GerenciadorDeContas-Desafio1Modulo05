package zup.com.br.gerenciador.contas.contadto;

import com.fasterxml.jackson.annotation.JsonFormat;
import zup.com.br.gerenciador.contas.enums.Status;
import zup.com.br.gerenciador.contas.enums.Tipo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaDTO {
    private int id;
    @NotBlank
    @Size(min = 2, max = 20, message = "Esse campo permite no minimo 2 caractere e no maximo 20")
    private String nome;
    @NotNull
    private Double valor;
    @NotNull
    private LocalDate dataDeVencimento;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDePagamento;
    private Status status;
    @NotNull
    private Tipo tipo;

    public ContaDTO() {
     super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public LocalDateTime getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDateTime dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

