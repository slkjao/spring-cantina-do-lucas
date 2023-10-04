package com.cantina.cantina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "tb_garcom")
public class Garcom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @OneToMany(mappedBy="garcom", cascade = CascadeType.ALL)
    private List<Pedido> listaPedidos = new ArrayList<>();

    public Garcom(){
    }

    public Garcom(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @JsonIgnore
    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garcom garcom = (Garcom) o;
        return id.equals(garcom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
