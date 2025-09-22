package Servicio;

import DAO.ProductoDAO;
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

    public ProductoDTO mostrarProducto(Long id) {
        if (id <= 0) throw new IllegalArgumentException("El id no puede ser negativo o igual a 0");
        ProductoDTO productoBuscado = productoDAO.mostrarProducto(id);
        if (productoBuscado != null) {
            System.out.println(productoBuscado.toString());
            return productoBuscado;
        } else {
            System.out.println("no se encontro el producto");
            return null;

        }
    }

    public List<ProductoDTO> mostrarProductoPorNombre(String nombre) {
        if (nombre == null) throw new IllegalArgumentException("El nombre no puede ser nulo");
        if (nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (!nombre.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+$"))
            throw new IllegalArgumentException("El nombre contiene caracteres no válidos");
        List<ProductoDTO> productoBuscado = productoDAO.mostrarProductoPorNombre(nombre);
        if (productoBuscado != null) {
            productoBuscado.stream().forEach(System.out::println);
            return productoBuscado;
        } else {
            System.out.println("no se encontro el producto");
            return null;
        }


    }

    public void mostrarProductos() {
        List<ProductoDTO> productos = productoDAO.mostrarProductos();
        productos.stream().forEach(System.out::println);

    }

    public boolean ActualizarProducto(Long id, String datosProducto) {
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
//
//        if (nombre.matches("\"^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑüÜ\\\\s\\\\.\\\\,\\\\(\\\\)\\\\&\\\\#\\\\@\\\\%\\\\!\\\\?]+$")) {
//            System.out.println("caracte invalido");
//            return false;
//        }
        if (!nombre.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+$"))
            throw new IllegalArgumentException("El nombre contiene caracteres no válidos");


        if (precio.doubleValue() <= 0) {
            System.out.println("precio invalido");
            return false;
        }
        if (stock <= 4) {
            System.out.println("stock invalido");
            return false;
        }

        ProductoDTO actualizado = productoDAO.actualizarProducto(id, ProductoDTO.builder()
                .nombre(nombre)
                .precio(precio)
                .stock(stock)
                .build());

        if (actualizado != null) {
            System.out.println("Producto modificado con exito");
            System.out.println(actualizado);
            return true;

        } else {
            System.out.println("Error al actualizar producto");
            return false;
        }

    }

    public void eliminarProducto(Long id) {

        boolean eliminarProducto = productoDAO.eliminarProducto(id);
        if (eliminarProducto) System.out.println("producto eliminado con exito");
        else System.out.println("Error al eliminar producto");

    }


}
