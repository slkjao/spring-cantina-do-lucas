package com.cantina.cantina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "tb_mesa")
public class Mesa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant horaEntrada;
    private Instant horaSaida;

    @OneToMany(mappedBy="mesa", cascade = CascadeType.ALL)
    private List<Pedido> listaDePedidos = new ArrayList<>();

    public Mesa() {
    }

    public Mesa(Long id, Instant horaEntrada, Instant hora_saida) {
        this.id = id;
        this.horaEntrada = horaEntrada;
        this.horaSaida = hora_saida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Instant horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Instant getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Instant hora_saida) {
        this.horaSaida = hora_saida;
    }

    public List<Pedido> getListaDePedidos() {
        return listaDePedidos;
    }

    public void mudarQnt(int indexPedido, Prato prato, int novaQnt){
        var local = this.getListaDePedidos().get(indexPedido).getListaDePratos();
        local.remove(prato);
        if(novaQnt == 0){
            return;
        }
        local.put(prato, novaQnt);
    }

    public void mostrarPedidos() {
        for (Pedido pedido : listaDePedidos) {
            System.out.println("Pedido #" + pedido.getId());
            var map = pedido.getListaDePratos();
            for (var innerMap: map.entrySet()){
                System.out.println("Item: "+ innerMap.getKey().getNome() + " - Qnt: "+innerMap.getValue());
            }
        }
    }
    public void setPedido(Pedido... pedido) {
        if (this.getHoraEntrada() == null) {
            this.setHoraEntrada(pedido[0].getHora());
        }
        for (Pedido pedidoInput : pedido) {
            this.getListaDePedidos().add(pedidoInput);
        }
    }

    public BigDecimal valorMesa() {
        var total = listaDePedidos.stream().map(Pedido::valorPedido).reduce(BigDecimal.ZERO, BigDecimal::add);
        var gorjeta = total.divide(BigDecimal.TEN);
        total = total.add(gorjeta);
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return id.equals(mesa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", horaEntrada=" + horaEntrada +
                ", horaSaida=" + horaSaida +
                "\nlistaDePedidos=\n" + listaDePedidos.toString() +
                '}';
    }
}
