package com.cantina.cantina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Instant horaPedido;
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "garcom_id")
    private Garcom garcom;
    @ElementCollection
    private Map<Prato, Integer> listaDePratos = new LinkedHashMap<>();

    public Pedido() {
    }

    public Pedido(Long id, String descricao, Instant horaPedido, Garcom garcom, Mesa mesa) {
        this.id = id;
        this.descricao = descricao;
        this.horaPedido = horaPedido;
        this.garcom = garcom;
        this.mesa = mesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getHora() {
        return horaPedido;
    }

    public void setHora(Instant hora) {
        this.horaPedido = hora;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    @JsonIgnore
    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Map<Prato, Integer> getListaDePratos() {
        return listaDePratos;
    }

    public BigDecimal valorPedido() {
        BigDecimal total = listaDePratos.entrySet().stream().
                map(prato -> prato.getKey().getPreco().multiply(BigDecimal.valueOf(prato.getValue()))).
                reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder pedido = new StringBuilder();
        pedido.append("Pedido #" + id + ":\n");
        pedido.append("Horario do pedido: " + horaPedido + " - ");
        pedido.append("Garcom: " + garcom.getNome() + "\n");
        pedido.append("Lista de Itens: \n");
        listaDePratos.forEach((key, value) -> pedido.append("Qnt: " + key + " - Item: " + value.toString() + "\n"));
        return pedido.toString();
    }
}
