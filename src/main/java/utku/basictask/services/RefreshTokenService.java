package utku.basictask.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utku.basictask.entity.RefreshToken;
import utku.basictask.entity.User;
import utku.basictask.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
    public String create (User user ){
        if(getByUser(user.getId()) == null || isRefreshExpired(getByUser(user.getId()))){
            RefreshToken token = new RefreshToken();
            token.setUser(user);
            token.setToken(UUID.randomUUID().toString());
            token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
            refreshTokenRepository.save(token);
            return token.getToken();
        }else {
            RefreshToken token = getByUser(user.getId());
            return token.getToken();
        }

    }
    public boolean isRefreshExpired(RefreshToken token ){
        return token.getExpiryDate().before(new Date());

    }

    public RefreshToken getByUser(int userId) {
        return  refreshTokenRepository.findByUserId(userId);
    }
}
