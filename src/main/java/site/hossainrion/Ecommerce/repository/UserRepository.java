package site.hossainrion.Ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import site.hossainrion.Ecommerce.model.User;

public interface UserRepository extends CrudRepository<User, Integer>
{
	User findByUsername(String username);
	User findById(int id);
}
