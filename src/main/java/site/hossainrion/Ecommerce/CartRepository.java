package site.hossainrion.Ecommerce;


import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> 
{
	List<Cart> findByOwner(int id);
}