import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    public void testSuma() {
        Calculadora calc = new Calculadora();
        int suma = calc.suma(1, 2);
        assertEquals(3, suma);
    }

    @Test
    public void testResta() {
        Calculadora calc = new Calculadora();
        int resta = calc.resta(2, 1);
        assertEquals(1, resta);
    }




}