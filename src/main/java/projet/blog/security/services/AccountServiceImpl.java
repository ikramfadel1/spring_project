package projet.blog.security.services;

import jakarta.transaction.Transactional;
import projet.blog.security.entities.Role;
import projet.blog.security.entities.User;
import projet.blog.security.repositories.RoleRepository;
import projet.blog.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public User createUser(String username, String password, String email, String confirmPassword) {
        /*
        User user1 = new User();
        user1.setUserId(UUID.randomUUID().toString());
        user1.setUsername(username);
        user1.setPassword(bCryptPasswordEncoder.encode(password));
        user1.setEmail(email);
        return userRepository.save(user1);
         */
        User user1 = userRepository.findByUsername(username);
        if (user1 != null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmPassword)) throw new RuntimeException("Passwords do not match");
        user1 = User.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .build();
        return userRepository.save(user1);
    }

    @Override
    public Role createRole(String newRole) {
        Role role1 = roleRepository.findByRole(newRole);
        if(role1 != null) throw new RuntimeException("Role already exists");
        role1 = Role.builder()
                .role(newRole)
                .build();
        return roleRepository.save(role1);
    }

    @Override
    public void addRoleToUser(String username, String newRole) {
        User user1 = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByRole(newRole);
        user1.getRoles().add(role1);
    }

    @Override
    public void removeRoleFromUser(String username, String newRole) {
        User user1 = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByRole(newRole);
        user1.getRoles().remove(role1);
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
