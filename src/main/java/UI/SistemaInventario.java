package UI;

import Servicio.ProductoServicio;
import domain.ProductoDTO;

import java.math.BigDecimal;
import java.util.Scanner;

public class SistemaInventario {
    static ProductoServicio ps = new ProductoServicio();

    public static void main(String[] args) {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);


        System.out.println("Bienvenido al sistema de inventario");
        System.out.println("Iniciando el programa");
        for (int i = 0; i <10 ; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println();



        while (opcion != 6) {
            System.out.println("Introducir operacion a realizar");
            System.out.println("1. Crear Producto");
            System.out.println("2. Mostrar Producto");
            System.out.println("3. Mostrar todos los productos");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. salir");
            opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.println("ingresar el nombre del producto");
                String nombre = sc.next();
                System.out.println("ingresar el precio del producto");
                BigDecimal precio = sc.nextBigDecimal();
                System.out.println("ingresar el Stock del producto");
                Integer Stock = sc.nextInt();
                System.out.println("Guardando producto en la base de datos...");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println();
                agregarProducto(nombre, precio, Stock);
            }
            if (opcion == 2) {
                System.out.println("ingresar el ID del producto");
                Long id = sc.nextLong();
                System.out.println("Cargando producto desde la base de datos...");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println();
                mostrarProducto(id);

            }
            if (opcion == 3) {
                System.out.println("cargando los productos desde la base de datos...");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println();
                mostrarProductos();
            }

            if (opcion == 4) {
                System.out.println("ingresar el ID del producto");
                Long id = sc.nextLong();
                sc.nextLine();
                System.out.println("ingresar el campo a actualizar y su nuevo valor separado por coma ej(nombre,soda,precio,12,stock,20) ");
                String datos = sc.nextLine();
                System.out.println("Actualizando producto en la base de datos...");
                for (int i = 0; i <3 ; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println();

                actualizarProducto(id, datos);
            }

            if (opcion == 5) {
                System.out.println("ingresar el ID del producto a Eliminar");
                Long id = sc.nextLong();
                System.out.println("Eliminando el producto en la base de datos...");
                for (int i = 0; i <3 ; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println();
                eliminarProducto(id);
            }

            if (opcion == 6) {
                System.out.println("⏳ Cerrando el sistema...");
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println("\n✅ Sistema cerrado exitosamente");
                System.out.println("👋 ¡Hasta pronto!");

                // Cerrar recursos
                sc.close();

                // Salir del programa
                System.exit(0);


            }


        }


    }

    public static void agregarProducto(String nombre, BigDecimal precio, Integer stock) {
        double v = precio.doubleValue();
        if (nombre == null || nombre.equals("") || v <= 0 || stock <= 4) {
            System.out.println("Datos invalidos");
        } else {
            ps.agregarProducto(ProductoDTO.builder()
                    .nombre(nombre)
                    .precio(precio)
                    .stock(stock)
                    .build());
        }
    }

    public static void mostrarProducto(Long id) {
        if (id != null) {
            ps.mostrarProducto(id);
        }
    }

    public static void mostrarProductos() {
        ps.mostrarProductos();
    }


    public static void eliminarProducto(Long id) {
        if (id != null) {
            ps.eliminarProducto(id);
        }
    }

    public static void actualizarProducto(Long id, String datosProducto) {
        if (id != null && datosProducto != null) {
            ps.ActualizarProducto(id, datosProducto);
        }

    }


}

