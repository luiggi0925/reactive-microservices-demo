package pe.edu.ltmj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.ltmj.domain.Product;
import pe.edu.ltmj.repository.CatalogRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

	private final CatalogRepository catalogRepository;

	public CatalogController(CatalogRepository catalogRepository) {
		this.catalogRepository = catalogRepository;
	}

	@GetMapping({"", "/"})
	public Flux<Product> getAllProducts() {
		return catalogRepository.findAll();
	}

	@GetMapping({"/{id}"})
	public Mono<Product> getSingleProduct(@PathVariable("id") String id) {
		return catalogRepository.findById(Mono.just(id));
	}

	@PostMapping({"/"})
	public Mono<Product> store(@RequestBody Mono<Product> product) {
		return catalogRepository.insert(product).next();
	}
}
