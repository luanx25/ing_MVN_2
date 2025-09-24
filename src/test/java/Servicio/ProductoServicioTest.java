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
                .nombre("cafe americano")
                .precio(new BigDecimal("25.90"))
                .stock(30)
                .build();
//       assertDoesNotThrow(()->dao.agregarProducto(p));
        assertTrue(dao.agregarProducto(p));
    }

    @Test
    public void mostrarProductoPorId() {
        long id = 1L;
        ProductoServicio srv = new ProductoServicio();
        assertNotNull(srv.mostrarProducto(id));
//        ProductoDAO dao = new ProductoDAO();
//        assertNotNull(dao.mostrarProducto(id));

    }
    @Test
    public void mostrarProductoPorNombre() {
        String nombre = "c";
        ProductoServicio srv = new ProductoServicio();
        assertNotNull(srv.mostrarProductoPorNombre(nombre));
//        ProductoDAO dao = new ProductoDAO();
//        assertNotNull(dao.mostrarProductoPorNombre(nombre));

    }

    @Test
    public void mostrarProductos() {
        ProductoDAO dao = new ProductoDAO();
        assertNotNull(dao.mostrarProductos());

    }
    @Test
    public void actualizarProducto() {
//        ProductoDAO dao = new ProductoDAO();
        ProductoServicio ps = new ProductoServicio();
        assertTrue(ps.ActualizarProducto (1L,"nombre,soda,precio,1,stock,45"));


    }




    @Test
    public void eliminarProducto() {
//        ProductoDAO dao = new ProductoDAO();
        ProductoServicio ps = new ProductoServicio();
        assertTrue(ps.eliminarProducto(0L));
    }





}