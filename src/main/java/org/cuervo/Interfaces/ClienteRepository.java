package org.cuervo.Interfaces;

import org.cuervo.Domain.Cliente;

import java.util.List;

public interface ClienteRepository {
    void save(Cliente cliente);
    void update(Cliente cliente);

    void delete(int id);

    Cliente findById(long id);
    List<Cliente> findAll();




}
