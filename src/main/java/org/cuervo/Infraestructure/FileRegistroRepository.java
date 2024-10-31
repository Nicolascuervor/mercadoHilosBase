package org.cuervo.Infraestructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.cuervo.Domain.Registro;
import org.cuervo.Interfaces.RegistroRepository;

import java.util.List;

public class FileRegistroRepository implements RegistroRepository {
    private final EntityManagerFactory emf;

    public FileRegistroRepository() {
        emf = Persistence.createEntityManagerFactory("RegistrosUp");
    }

    @Override
    public void saveRegistro(Registro registro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(registro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateRegistro(Registro registro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(registro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteRegistro(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Registro registro = em.find(Registro.class, id);
            if (registro != null) {
                em.remove(registro);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Registro findRegistroById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Registro.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Registro> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Registro p", Registro.class)
                    .getResultList();
        }
    }
}
