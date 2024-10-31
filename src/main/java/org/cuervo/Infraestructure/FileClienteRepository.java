package org.cuervo.Infraestructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.cuervo.Domain.Cliente;
import org.cuervo.Interfaces.ClienteRepository;

import java.util.List;

public class FileClienteRepository implements ClienteRepository {

    private final EntityManagerFactory emf;

    public FileClienteRepository() {
        emf = Persistence.createEntityManagerFactory("ClientesUp");
    }

    public void save(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }


    @Override
    public List<Cliente> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Cliente p", Cliente.class)
                    .getResultList();
        }
    }



    public void cerrar() {
        emf.close();
    }
}
