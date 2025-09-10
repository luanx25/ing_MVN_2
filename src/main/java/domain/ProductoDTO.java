package domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@Builder
@ToString
public class ProductoDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;


}
