package org.cuervo.Aplication;

import org.cuervo.Aplication.Services.ProductoService;
import org.cuervo.Aplication.Services.ClienteService;
import org.cuervo.Aplication.Services.registroService;
import org.cuervo.Infraestructure.FileProductosRepository;
import org.cuervo.Infraestructure.FileClienteRepository;
import org.cuervo.Infraestructure.FileRegistroRepository;
import org.cuervo.Interfaces.ProductosRepository;
import org.cuervo.Interfaces.ClienteRepository;
import org.cuervo.Interfaces.RegistroRepository;

public class Main {
    public static void main(String[] args) {

        ClienteRepository repository = new FileClienteRepository();
        ClienteService service = new ClienteService(repository);

        RegistroRepository registros = new FileRegistroRepository();
        registroService service1 = new registroService(registros);

        ProductosRepository productosRepository = new FileProductosRepository();
        ProductoService service3 = new ProductoService(productosRepository);

        Visual visual = new Visual(service, service3,service1);

        visual.iniciar();

    }
}
