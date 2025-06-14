package com.curso.domains.dtos;

import com.curso.enums.MetodoEnvio;
import com.curso.domains.Pedido;
import com.curso.enums.EstadoPedido;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PedidoDTO {

    private Long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode ser vazio")
    private String Nome;

    @NotNull(message = "O campo Valor não pode ser nulo")
    @Digits(integer = 15, fraction = 3)
    private BigDecimal Valor;

    private EstadoPedido estadoAtual;

    private MetodoEnvio metodoEnvio;

    @Digits(integer = 15, fraction = 3)
    private BigDecimal frete;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido obj) {
        this.id = obj.getId();
        this.Nome = obj.getNome();
        this.Valor = obj.getValor();
        this.estadoAtual = EstadoPedido.valueOf(obj.getEstadoAtual().toString());
        this.metodoEnvio = obj.getMetodoEnvio();
        this.frete = obj.getFrete();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode ser vazio") String getNome() {
        return Nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "O campo nome não pode ser vazio") String nome) {
        Nome = nome;
    }

    public BigDecimal getValor() {
        return Valor;
    }

    public void setValor(BigDecimal valor) {
        Valor = valor;
    }

    public EstadoPedido getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(EstadoPedido estadoAtual) {
        this.estadoAtual = estadoAtual;
    }


    public MetodoEnvio getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public @Digits(integer = 15, fraction = 3) BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(@Digits(integer = 15, fraction = 3) BigDecimal frete) {
        this.frete = frete;
    }
}
