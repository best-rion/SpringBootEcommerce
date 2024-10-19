package site.hossainrion.Ecommerce.home;


import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> 
{
	Product findById(int id);
}