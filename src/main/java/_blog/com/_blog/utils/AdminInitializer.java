package _blog.com._blog.utils;

import java.util.Scanner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import _blog.com._blog.Entity.User;
import _blog.com._blog.repositories.UserRepository;
import jakarta.validation.Valid;

@Component
public class AdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!args.containsOption("admin")) {
            return;
        }

        new Thread(this::createAdmin).start();
    }

    @Valid
    private void createAdmin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter admin email:");
        String email = scanner.nextLine().trim();

        if (userRepository.findByEmail(email).isEmpty()) {
            System.out.print("Enter admin username:");
            String username = scanner.nextLine().trim();
            System.out.print("Enter admin firstname:");
            String firstname = scanner.nextLine().trim();
            System.out.print("Enter admin lastname:");
            String lastname = scanner.nextLine().trim();
            System.out.print("Enter admin password:");
            String password = scanner.nextLine().trim();
            String url_photo = "default-avatar.jpg";
            User admin = new User();

            admin.setEmail(email);
            admin.setDateOfBirth("2005-06-24");
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setUrlPhoto(url_photo);
            admin.setRole("ADMIN");
            admin.setLastName(lastname);
            admin.setName(firstname);
            userRepository.save(admin);

            System.out.println("✅ Admin created successfully!");
        } else {
            System.out.println("⚠️ Admin with this email already exists.");
        }

        scanner.close();
        System.exit(0);
    }
}
