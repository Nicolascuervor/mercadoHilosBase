package org.cuervo.Aplication.Services;

import org.cuervo.Domain.Registro;
import org.cuervo.Interfaces.RegistroRepository;


import java.util.List;

public class registroService {
    private final RegistroRepository registrosRepository;

    public registroService(RegistroRepository registrosRepository) {
        this.registrosRepository = registrosRepository;
    }

    public void crearRegistro(Registro registro) {
        registrosRepository.saveRegistro(registro);
    }

    public List<Registro> listarRegistros() {
        return registrosRepository.findAll();
    }

    public void eliminarRegistro(int id) {
        registrosRepository.deleteRegistro(id);
    }

    public Registro buscarRegistro(int id) {
        return registrosRepository.findRegistroById(id);
    }

    public void actualizarRegistro(Registro registro) {
        registrosRepository.updateRegistro(registro);
    }
}
