package site.hossainrion.Ecommerce;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Product, Integer> {}