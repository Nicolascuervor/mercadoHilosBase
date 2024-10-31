package org.cuervo.Domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "Cajas")
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCajas;

    @Column(nullable = false)
    private String numeroCaja;

    @Transient
    private AtomicInteger clientesAtendidos;

    @Column(nullable = false)
    private int clientesAtendidosCount;

    @Column(nullable = false)
    private double totalVentas;


    @OneToOne(mappedBy = "caja")
    Cliente cliente;


    public Caja() {
    }

    public Caja(String numeroCaja, int clientesAtendidosCount, double totalVentas, Cliente cliente) {
        this.numeroCaja = numeroCaja;
        this.clientesAtendidosCount = clientesAtendidosCount;
        this.totalVentas = totalVentas;
        this.cliente = cliente;
    }

    public long getIdCajas() {
        return idCajas;
    }

    public void setIdCajas(long idCajas) {
        this.idCajas = idCajas;
    }

    public String getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(String numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public AtomicInteger getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(AtomicInteger clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    public int getClientesAtendidosCount() {
        return clientesAtendidosCount;
    }

    public void setClientesAtendidosCount(int clientesAtendidosCount) {
        this.clientesAtendidosCount = clientesAtendidosCount;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Caja{" +
                "idCajas=" + idCajas +
                ", clientesAtendidos=" + clientesAtendidos.get() +
                ", totalVentas=" + totalVentas +
                '}';
    }
}
