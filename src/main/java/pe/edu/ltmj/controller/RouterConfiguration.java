package pe.edu.ltmj.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.edu.ltmj.domain.Product;
import pe.edu.ltmj.repository.CatalogRepository;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfiguration {
	
//	@Bean
//	public RouterFunction<ServerResponse> catalogRoute(CatalogRepository catalogRepository) {
//		return RouterFunctions.route(
//				GET("/catalog/{id}").and(accept(MediaType.APPLICATION_JSON)),
//				req -> {
//					Mono<Product> productMono = catalogRepository.findById(req.pathVariable("id"));
//					return productMono.then(
//							//person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(person)
//							);
//				});
//	}
}
