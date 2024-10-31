package org.cuervo.Aplication.Services;

import org.cuervo.Domain.Producto;
import org.cuervo.Interfaces.ProductosRepository;

import java.util.List;

public class ProductoService {
    private final ProductosRepository productosRepository;

    public ProductoService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    public void crearProducto(Producto producto) {
        productosRepository.saveProducto(producto);
    }

    public List<Producto> listarProductos() {
        return productosRepository.findAll();
    }

    public void eliminarProducto(int id) {
        productosRepository.deleteProducto(id);
    }

    public Producto buscarProducto(int id) {
        return productosRepository.findProductoById(id);
    }

    public void actualizarProducto(Producto producto) {
        productosRepository.updateProducto(producto);
    }
}
