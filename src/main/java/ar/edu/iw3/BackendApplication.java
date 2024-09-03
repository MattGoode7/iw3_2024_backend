package ar.edu.iw3;

import ar.edu.iw3.model.business.FoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.iw3.model.Product;
import ar.edu.iw3.model.business.BusinessException;
import ar.edu.iw3.model.business.IProductBusiness;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication 
@Slf4j
public class BackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
	}

	@Autowired
	private IProductBusiness productBusiness;

	@Override
	public void run(String... args) throws Exception {
		try {
			// Crear dos productos con el mismo nombre pero con diferentes IDs
			Product product1 = new Product();
			product1.setId(3L);
			product1.setProduct("Leche");
			product1.setPrice(50);
			productBusiness.add(product1);

			Product product2 = new Product();
			product2.setId(4L);
			product2.setProduct("Leche");
			product2.setPrice(190);
			productBusiness.add(product2);

			// Intentar actualizar uno de los productos para que coincida con el nombre del otro producto
			product2.setProduct("Leche");
			productBusiness.update(product2);
		} catch (FoundException e) {
            log.error("Excepción encontrada: {}", e.getMessage());
		} catch (Exception e) {
            log.error("Ocurrió un error inesperado: {}", e.getMessage(), e);
		}
	}
}