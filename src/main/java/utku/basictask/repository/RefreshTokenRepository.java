package utku.basictask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utku.basictask.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByUserId(int userId);
}
