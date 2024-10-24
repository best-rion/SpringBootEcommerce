package site.hossainrion.Ecommerce.repository;


import org.springframework.data.repository.CrudRepository;

import site.hossainrion.Ecommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> 
{
	Product findById(int id);
}