package site.hossainrion.Ecommerce;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>
{
	User findByUsername(String username);
}
