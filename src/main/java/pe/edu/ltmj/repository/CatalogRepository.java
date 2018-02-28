package pe.edu.ltmj.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pe.edu.ltmj.domain.Product;

public interface CatalogRepository extends ReactiveMongoRepository<Product, String> {

}
