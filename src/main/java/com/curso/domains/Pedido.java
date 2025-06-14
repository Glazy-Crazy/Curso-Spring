package com.curso.domains;

import com.curso.enums.EstadoPedido;
import com.curso.enums.MetodoEnvio;
import com.curso.domains.dtos.PedidoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valor;

    private EstadoPedido estadoAtual;

    @Enumerated(EnumType.STRING)
    private MetodoEnvio metodoEnvio;

    @Digits(integer = 15, fraction = 3)
    private BigDecimal frete;

    public Pedido() {
    }

    public Pedido(Long id, String nome, BigDecimal  valorTotal, MetodoEnvio metodoEnvio, BigDecimal frete) {
        this.id = id;
        this.nome = nome;
        this.valor = valorTotal;
        this.estadoAtual = EstadoPedido.AGUARDANDOPAGAR;
        this.metodoEnvio = metodoEnvio;
        this.frete = frete;
    }

    public Pedido(PedidoDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.valor = dto.getValor();
        this.estadoAtual = EstadoPedido.AGUARDANDOPAGAR;
        this.metodoEnvio = dto.getMetodoEnvio();
        this.frete = dto.getFrete();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal  getValor() {
        return valor;
    }

    public void setValor(BigDecimal  valor) {
        this.valor = valor;
    }

    public MetodoEnvio getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public void pagar() {
        this.estadoAtual = this.estadoAtual.pagar();
    }

    public void cancelar() {
        this.estadoAtual = this.estadoAtual.cancelar();
    }

    public void enviar() {
        this.estadoAtual = this.estadoAtual.enviar();
    }


    public double calcularFrete() {
        return metodoEnvio.calcularFrete(valor);
    }

    public BigDecimal getFrete() {
        return BigDecimal.valueOf(calcularFrete());
    }

    public void setEstadoAtual(EstadoPedido novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public EstadoPedido getEstadoAtual() {
        return estadoAtual;
    }

}
