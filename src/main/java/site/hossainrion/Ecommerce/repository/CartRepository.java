package site.hossainrion.Ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import site.hossainrion.Ecommerce.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> 
{	
<<<<<<< HEAD
	@Query(value = "SELECT * from cart where owner_ref = :userId AND sold = 0", nativeQuery = true)
=======
	@Query(value = "SELECT * from cart where owner_ref = :userId AND sold = false", nativeQuery = true)
>>>>>>> 401519b (Inititalized)
	List<Cart> findByOwnerRef(int userId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart WHERE product_ref=:productId AND owner_ref=:ownerId", nativeQuery = true)
	void deleteByProductRefAndOwnerRef(@Param("productId") int productId, @Param("ownerId") int ownerId);
}