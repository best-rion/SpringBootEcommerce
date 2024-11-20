package site.hossainrion.Ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import site.hossainrion.Ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Page<Product> findAll(Pageable p);
	
	public Product findById(int id);
}