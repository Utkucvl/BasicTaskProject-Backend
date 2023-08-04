package utku.basictask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utku.basictask.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
