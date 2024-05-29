package projet.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import projet.blog.security.services.AccountServiceImpl;

@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {SpringApplication.run(BlogApplication.class,args);}
    //@Bean
    public CommandLineRunner inMemoryUserDetailsManager(AccountServiceImpl acct) {
        return args-> {
            //acct.createRole("ADMIN");
           // acct.createUser("admin2","123","admin2@gmail.com","123");
            //acct.addRoleToUser("admin2","ADMIN");
            acct.createUser("user","123","user@gmail.com","123");
            acct.createRole("USER");
            acct.addRoleToUser("user","USER");

        };
    }


}
