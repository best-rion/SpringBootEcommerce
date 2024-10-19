package site.hossainrion.Ecommerce.reg;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>
{
	User findByUsername(String username);
}
