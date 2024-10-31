package org.cuervo.Domain;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Registro")

public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private Date fechaCompra;


    @OneToOne(mappedBy = "registro")
    Cliente cliente;


    public Registro(String nombreCliente, Date fechaCompra) {
        this.nombreCliente = nombreCliente;
        this.fechaCompra = fechaCompra;
    }

    public Registro() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

}
