package com.cg.backend_doc_checker;

import com.cg.backend_doc_checker.config.AppConstants;
import com.cg.backend_doc_checker.model.Role;
import com.cg.backend_doc_checker.model.User;
import com.cg.backend_doc_checker.repository.RoleRepository;
import com.cg.backend_doc_checker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@SpringBootApplication
@ComponentScan(basePackages = "com.cg.backend_doc_checker")
public class BackendDocCheckerApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendDocCheckerApplication.class, args);
        System.err.println("Welcome to Spring Boot BlogAppApisApplication!!!");
    }

    @Override
    public void run(String... args) throws Exception {
//       String sql = "INSERT INTO users (user_name, email,password,about) VALUES ("
//               + "'Saurabh', 'saurabh@gmail.com','saurabh','Software Developer')";
//       int rows = jdbcTemplate.update(sql);
//       if (rows > 0) {
//           System.out.println("A new row has been inserted.");
//       }

        // Check if roles exist
        Optional<Role> roleAdmin = roleRepo.findById(AppConstants.ADMIN_USER_ID);
        Role role1;
        if (!roleAdmin.isPresent()) {
            role1 = new Role();
            role1.setRoleId(AppConstants.ADMIN_USER_ID);
            role1.setRoleName(AppConstants.ADMIN_USER);
            roleRepo.save(role1);
        } else {
            role1 = roleAdmin.get();
        }

        Optional<Role> roleUser = roleRepo.findById(AppConstants.NORMAL_USER_ID);
        Role role2;
        if (!roleUser.isPresent()) {
            role2 = new Role();
            role2.setRoleId(AppConstants.NORMAL_USER_ID);
            role2.setRoleName(AppConstants.NORMAL_USER);
            roleRepo.save(role2);
        } else {
            role2 = roleUser.get();
        }

        List<Role> savedRoleList = this.roleRepo.findAll();
        savedRoleList.forEach(r -> {
            System.err.println("RoleId: " + r.getRoleId() + "  Role: " + r.getRoleName());
        });

        // Check if user exists by username or email
//        Optional<User> userByUsername = userRepository.findByUsername(AppConstants.DEFAULT_ADMIN_USERNAME);
        Optional<User> userByEmail = userRepository.findByEmail(AppConstants.DEFAULT_ADMIN_EMAIL);
        if (!userByEmail.isPresent()) {
            User user = new User();
//            user.setUsername(AppConstants.DEFAULT_ADMIN_USERNAME);
            user.setEmail(AppConstants.DEFAULT_ADMIN_EMAIL);
            user.setPassword(passwordEncoder.encode(AppConstants.DEFAULT_ADMIN_PASSWORD));

            // Associate roles with the user
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role1);
            userRoles.add(role2);
            user.setRoles(userRoles);
            userRepository.save(user);

//            List<User> savedUserList = this.userRepository.findAll();
//            savedUserList.forEach(u -> {
//                System.err.println("Default: username-" + u.getUsername()
//                        + " \nEmail-" + u.getEmail()
//                        + " \nPassword: " + AppConstants.DEFAULT_ADMIN_PASSWORD
//                        + " \nPassword: " + u.getPassword());
//            });
        }
        List<User> savedUserList = this.userRepository.findAllByUserId(AppConstants.ADMIN_USER_ID);
        savedUserList.forEach(u -> {
            System.err.println("Default: username-" + u.getUsername()
                    + " \nEmail-" + u.getEmail()
                    + " \nPassword: " + AppConstants.DEFAULT_ADMIN_PASSWORD
                    + " \nPassword: " + u.getPassword());
        });

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
