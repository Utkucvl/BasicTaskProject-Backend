package utku.basictask.services;

import org.springframework.stereotype.Service;
import utku.basictask.entity.User;
import utku.basictask.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User createOneUser(User user) {
        return userRepository.save(user);
    }

    public User getOneUserByUserName(String userName) {
        return (userRepository.findByUserName(userName));
    }


}
