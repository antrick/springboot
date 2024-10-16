package com.example.curso.servicios;

import com.example.curso.Rutas;
import com.example.curso.models.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    public void storeOrder(List<Producto> productos){
        System.out.println("Guardando en la base de datos...");

        productos.forEach(producto -> logger.debug("el nombre del producto es: {}",producto.nombre));
    }
}
