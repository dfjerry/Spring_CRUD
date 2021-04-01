package springusermanager.demo.repository;

import org.springframework.data.repository.CrudRepository;
import springusermanager.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
