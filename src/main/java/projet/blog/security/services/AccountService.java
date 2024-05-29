package projet.blog.security.services;

import projet.blog.security.entities.Role;
import projet.blog.security.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    User createUser(String username, String password, String email, String confirmPassword);
    Role createRole(String newRole);
    void addRoleToUser(String username, String newRole);
    void removeRoleFromUser(String username, String newRole);
    User loadUserByUsername(String username);
}
