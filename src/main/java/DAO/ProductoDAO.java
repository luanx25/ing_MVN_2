package DAO;

import domain.Producto;
import domain.ProductoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/productos?useSSl=false";
    private String user = "root";
    private String password = "1234";

    public ProductoDAO() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean agregarProducto(ProductoDTO productoDTO) {
        Producto nuevoProducto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .precio(productoDTO.getPrecio())
                .stock(productoDTO.getStock())
                .build();
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO producto(nombre, precio, stock) VALUES (?,?,?)")) {
            ps.setString(1, nuevoProducto.getNombre());
            ps.setBigDecimal(2, nuevoProducto.getPrecio());
            ps.setInt(3, nuevoProducto.getStock());
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: {}" + e.getMessage());
            return false;
        }

    }

    public ProductoDTO mostrarProducto(Long id) {
        try (PreparedStatement ps = con.prepareStatement("SELECT * from producto WHERE id =?")) {
            ProductoDTO productoEncontrado;
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productoEncontrado = ProductoDTO.builder()
                            .id(rs.getLong("id"))
                            .nombre(rs.getString("nombre"))
                            .precio(rs.getBigDecimal("precio"))
                            .stock(rs.getInt("stock"))
                            .build();
                } else {
                    return null;
                }

            }

            return productoEncontrado;

        } catch (SQLException e) {
            System.err.println("Error al mostrar producto con id : " + id + e.getMessage());
            throw new RuntimeException("Error en la consulta del producto", e);
        }
    }

    public List<ProductoDTO> mostrarProductos() {
        try (PreparedStatement ps = con.prepareStatement("SELECT * from producto")) {
            List<ProductoDTO> productos = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productos.add(ProductoDTO.builder()
                            .id(rs.getLong("id"))
                            .nombre(rs.getString("nombre"))
                            .precio(rs.getBigDecimal("precio"))
                            .stock(rs.getInt("stock"))
                            .build());
                }
            }

            return productos;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Error en la consulta de productos", e);
        }
    }


    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoModificado) {

        ProductoDTO productoEncontrado = mostrarProducto(id);
        productoEncontrado.setNombre(productoModificado.getNombre());
        productoEncontrado.setPrecio(productoModificado.getPrecio());
        productoEncontrado.setStock(productoModificado.getStock());
        try (PreparedStatement ps = con.prepareStatement("UPDATE producto SET nombre=?, precio=?, stock=? WHERE id=?")) {
            ps.setString(1, productoModificado.getNombre());
            ps.setBigDecimal(2, productoModificado.getPrecio());
            ps.setInt(3, productoModificado.getStock());
            ps.setLong(4, id);
            if (ps.executeUpdate() == 1) {
                return mostrarProducto(id);
            } else return null;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Error en la Actualizacion del producto", e);

        }

    }


    public boolean eliminarProducto(Long id) {
        if (mostrarProducto(id)==null)return false;
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM producto WHERE id=?")) {
            ps.setLong(1, id);
            int i = ps.executeUpdate();
            return i == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Error en la eliminacion del producto", e);
        }

    }


}












