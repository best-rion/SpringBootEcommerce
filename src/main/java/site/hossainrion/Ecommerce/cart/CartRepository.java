package site.hossainrion.Ecommerce.cart;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface CartRepository extends CrudRepository<Cart, Integer> 
{
	List<Cart> findByOwnerRef(int userId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart WHERE product_ref=:productId AND owner_ref=:ownerId", nativeQuery = true)
	void deleteByProductRefAndOwnerRef(@Param("productId") int productId, @Param("ownerId") int ownerId);
}