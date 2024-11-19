package site.hossainrion.Ecommerce.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import site.hossainrion.Ecommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> 
{
	public Product findById(int id);
	public List<Product> findAll();
}