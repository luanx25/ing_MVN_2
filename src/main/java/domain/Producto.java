package domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@Builder
@ToString
public class Producto {
    Long id;
    String nombre;
    BigDecimal precio;
    Integer stock;

}
