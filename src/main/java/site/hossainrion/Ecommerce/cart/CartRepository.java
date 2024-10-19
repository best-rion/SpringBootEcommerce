package site.hossainrion.Ecommerce.cart;


import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> 
{
	List<Cart> findByOwnerRef(int userId);
}