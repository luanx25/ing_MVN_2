package Servicio;

import DAO.ProductoDAO;
import domain.Producto;
import domain.ProductoDTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductoServicio {

    ProductoDAO productoDAO;

    public ProductoServicio() {
        productoDAO = new ProductoDAO();
    }

    public void agregarProducto(ProductoDTO productoDTO) {
        boolean b = productoDAO.agregarProducto(productoDTO);
        if (b) System.out.println("Producto creado con exito");
        else System.out.println("Error al agregar producto");

    }

    public void mostrarProducto(Long id) {
        ProductoDTO productoBuscado = productoDAO.mostrarProducto(id);
        if (productoBuscado != null)
            System.out.println(productoBuscado.toString());

    }

    public void mostrarProductoPorNombre(String nombre) {
        ProductoDTO productoBuscado = productoDAO.mostrarProductoPorNombre(nombre);
        if (productoBuscado != null)
            System.out.println(productoBuscado.toString());


    }

    public void mostrarProductos() {
        List<ProductoDTO> productos = productoDAO.mostrarProductos();
        productos.stream().forEach(System.out::println);

    }

    public void ActualizarProducto(Long id, String datosProducto) {
        ProductoDTO productoSeleccionado = productoDAO.mostrarProducto(id);
        String nombre = productoSeleccionado.getNombre();
        BigDecimal precio = productoSeleccionado.getPrecio();
        Integer stock = productoSeleccionado.getStock();
        if (datosProducto.contains("nombre") && datosProducto.contains("precio") && datosProducto.contains("stock")) {
            String[] strings = datosProducto.split(",");
            nombre = strings[1];
            precio = new BigDecimal(strings[3]);
            stock = Integer.parseInt(strings[5]);
        } else if (datosProducto.contains("nombre") && datosProducto.contains("precio")) {
            String[] strings = datosProducto.split(",");
            nombre = strings[1];
            precio = new BigDecimal(strings[3]);
        } else if (datosProducto.contains("nombre") && datosProducto.contains("stock")) {
            String[] strings = datosProducto.split(",");
            nombre = strings[1];
            stock = Integer.parseInt(strings[3]);
        } else if (datosProducto.contains("precio") && datosProducto.contains("stock")) {
            String[] strings = datosProducto.split(",");
            precio = new BigDecimal(strings[1]);
            stock = Integer.parseInt(strings[3]);
        } else if (datosProducto.contains("nombre")) {
            String[] strings = datosProducto.split(",");
            nombre = strings[1];
        } else if (datosProducto.contains("precio")) {
            String[] strings = datosProducto.split(",");
            precio = new BigDecimal(strings[1]);
        } else if (datosProducto.contains("stock")) {
            String[] strings = datosProducto.split(",");
            stock = Integer.parseInt(strings[1]);
        }
        ProductoDTO actualizado = productoDAO.actualizarProducto(id, ProductoDTO.builder()
                .nombre(nombre)
                .precio(precio)
                .stock(stock)
                .build());

        if (actualizado != null) {
            System.out.println("Producto modificado con exito");
            System.out.println(actualizado.toString());

        } else System.out.println("Error al actualizar producto");

    }

    public void eliminarProducto(Long id) {

        boolean eliminarProducto = productoDAO.eliminarProducto(id);
        if (eliminarProducto) System.out.println("producto eliminado con exito");
        else System.out.println("Error al eliminar producto");

    }


}
