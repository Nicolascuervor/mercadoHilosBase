package org.cuervo.Aplication;



import org.cuervo.Aplication.Services.ProductoService;
import org.cuervo.Aplication.Services.ClienteService;
import org.cuervo.Aplication.Services.registroService;
import org.cuervo.Domain.Cliente;
import org.cuervo.Domain.Producto;
import org.cuervo.Domain.Registro;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Visual implements Runnable {
    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final registroService regService;


    public Visual(ClienteService clienteService, ProductoService productoService, registroService regService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.regService = regService;
    }


    public void iniciar(){
        while(true){
            int contadorRegistro = clienteService.listarPacientes().size();
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Bienvenido. Ingrese una opcion valida: \n" +
                    "1. Registrar clientes \n" +
                    "2. Empezar simulacion en consola\n" +
                    "3. Salir \n" +
                    "Clientes en espera: " + contadorRegistro));

            switch(opcion){
                case 1 -> registarClientes();
                case 2 -> run();
                case 3 -> System.exit(0);
            }
        }
    }

    private void registarClientes() {
        String[] opcionesCliente = {"Registrar de forma aleatoria","Registrar de forma manual"};
        int opcion = JOptionPane.showOptionDialog(null, "Desea registrar los clientes de forma aleatoria o hacerlo de forma manual?", "Registro de productos", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesCliente, opcionesCliente[0]);
        switch(opcion){
            case 0:
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de clientes que desee registrar: "));
                if(cantidad > 0){
                    generarClientes(cantidad);
                }
                else JOptionPane.showMessageDialog(null,"No puede ingresar una cantidad menor a cero");
        }
    }

    @Override
    public synchronized void run() {
        enum NumCaja {
            CAJA1, CAJA2, CAJA3
        }

        System.out.println("Iniciando atención de clientes...");

        List<Cliente> clientesEnEspera = clienteService.listarPacientes();


        if (clientesEnEspera.isEmpty()) {
            System.out.println("No hay clientes en espera para atender.");
            return;
        }

        Random rand = new Random();
        for (Cliente cliente : clientesEnEspera) {

            NumCaja cajaAsignada = NumCaja.values()[rand.nextInt(NumCaja.values().length)];

            System.out.println("Atendiendo al cliente " + cliente.getNombre() + " en " + cajaAsignada);


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Error en la simulación de atención: " + e.getMessage());
                Thread.currentThread().interrupt();
            }


            Registro registro = new Registro(cliente.getNombre(),new Date());
            regService.crearRegistro(registro);
        }

        System.out.println("Atención finalizada para todos los clientes en espera.");

    }



    public void generarClientes(int cantidad){

        enum NombresAleatorios{
            CARLOS,JOSE,PEDRO,MARIA,ANA,MIGUEL,SAMUEL
        }

        enum TipoProducto {
            FRUTAS(12.000),
            VEGETALES(20.00),
            CARNE(50.00),
            LECHE(100.0),
            HUEVOS(15.00),
            PAPA(50.00),
            SNACKS(20.0);

            private double precio;

            TipoProducto(double precio) {
                this.precio = precio;
            }
        }

        for (int i = 0; i < cantidad; i++){
            Random rand = new Random();
            int numeroCliente = rand.nextInt(8) -1;

            int numeroProducto1 = rand.nextInt(8) -1;
            int numeroProducto2 = rand.nextInt(8) -1;
            int numeroProducto3 = rand.nextInt(8) -1;

            for(NombresAleatorios nombre : NombresAleatorios.values()){
                Cliente cliente = new Cliente();
                if(numeroCliente == nombre.ordinal()){
                    cliente.setNombre(nombre.name());
                    clienteService.crear(cliente);
                }

            }

            List<Producto> cesta = new ArrayList<>();

            for (TipoProducto prodcuto : TipoProducto.values()){
                if(numeroProducto1 == prodcuto.ordinal()){
                    Producto producto = new Producto(prodcuto.precio,prodcuto.name());
                    cesta.add(producto);
                    productoService.crearProducto(producto);
                }
            }


        }




    }



}






