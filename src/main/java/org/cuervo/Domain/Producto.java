package org.cuervo.Domain;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
    @Table(name = "Producto")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private double precio;


    @ManyToOne
    @JoinColumn(name = "cliente_uid")
    private Cliente cliente;


    public Producto(int id, String tipo, double precio, Cliente cliente) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.cliente = cliente;
    }

    public Producto(double precio, String tipo) {
        this.precio = precio;
        this.tipo = tipo;
    }

    public Producto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Informacion de producto: " +
                "ID = |" + id + "|\t" +
                "Tipo = |" + tipo + "|\t" +
                "Precio = |" + precio + "|\t";

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Double.compare(precio, producto.precio) == 0 && Objects.equals(tipo, producto.tipo) && Objects.equals(cliente, producto.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, precio, cliente);
    }
}
