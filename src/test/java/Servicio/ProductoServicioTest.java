package Servicio;

import DAO.ProductoDAO;
import domain.Producto;
import domain.ProductoDTO;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoServicioTest {

    @Test
    public void agregarProducto() {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO p = ProductoDTO.builder()
                .nombre("cafe")
                .precio(new BigDecimal("19.50"))
                .stock(10)
                .build();
//       assertDoesNotThrow(()->dao.agregarProducto(p));
        assertTrue(dao.agregarProducto(p));
    }

    @Test
    public void consultarProducto() {
        long id = 12;
        ProductoDAO dao = new ProductoDAO();
        assertNotNull(dao.mostrarProducto(id));

    }

    @Test
    public void mostrarProductos() {
        ProductoDAO dao = new ProductoDAO();
        assertNotNull(dao.mostrarProductos());

    }
    @Test
    public void actualizarProducto() {
        ProductoDAO dao = new ProductoDAO();
        assertNotNull(dao.actualizarProducto(13l,ProductoDTO.builder().nombre("cafe").precio(new BigDecimal("19.00")).stock(12).build()));


    }






    @Test
    public void eliminarProducto() {

        ProductoDAO dao = new ProductoDAO();
        assertTrue(dao.eliminarProducto(12L));
    }





}