package cr.ac.una.ventas.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cr.ac.una.ventas")
public class CatalogoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatalogoApplication.class, args);
    }
}
