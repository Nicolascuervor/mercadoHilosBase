package org.cuervo.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Entity
    @Table(name = "Cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;


    @OneToMany(mappedBy = "cliente")
    private List<Producto> cesta;


    @OneToOne
    @JoinColumn(name = "caja_uid")
    private Caja caja;


    @OneToOne
    @JoinColumn(name = "registro_uid")
    private Registro registro;



    public Cliente(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cliente(String nombre, List<Producto> cesta) {
        this.nombre = nombre;
        this.cesta = cesta;
    }

    public Cliente() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "Cliente: " +
                "ID = |" + id + "| \t" +
                "Nombre = |" + nombre + "| \t";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Objects.equals(nombre, cliente.nombre) && Objects.equals(cesta, cliente.cesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cesta);
    }
}
