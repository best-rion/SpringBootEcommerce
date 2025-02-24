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
	public List<Cart> findAll();

	@Query(value = "SELECT * from cart where owner_id = :userId AND sold = 0", nativeQuery = true)
	public List<Cart> findByOwnerId(int userId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart WHERE product_id = :productId AND owner_id = :ownerId", nativeQuery = true)
	public void deleteByProductAndOwner(@Param("productId") int productId, @Param("ownerId") int ownerId);
}