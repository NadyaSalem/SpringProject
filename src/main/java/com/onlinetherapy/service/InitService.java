package com.onlinetherapy.service;

import com.onlinetherapy.models.entity.Therapist;
import com.onlinetherapy.models.entity.User;
import com.onlinetherapy.models.entity.Role;
import com.onlinetherapy.models.enums.TherapistTypeEnum;
import com.onlinetherapy.models.enums.UserRoleEnum;
import com.onlinetherapy.repository.TherapistRepository;
import com.onlinetherapy.repository.UserRepository;
import com.onlinetherapy.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final TherapistRepository therapistRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminPass;

    @Autowired
    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       @Value("${app.default.admin.password}") String adminPass,
                       TherapistRepository therapistRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.therapistRepository = therapistRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminPass = adminPass;
    }

    public void init() {
        initRoles();
        initTherapists();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var clientRole = new Role().setUserRole (UserRoleEnum.ADMIN);
            var adminRole = new Role().setUserRole (UserRoleEnum.CLIENT);

            userRoleRepository.save(clientRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initTherapists() {
        if (therapistRepository.count() == 0) {
            Arrays.stream(TherapistTypeEnum.values())
                    .forEach(TherapistTypeEnum -> {
                        Therapist therapist = new Therapist();
                        therapist.setName(TherapistTypeEnum);
                        therapistRepository.save(therapist);
                    });
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initClient1();
            initClient2();
            initClient3();
            initClient4();
        }
    }

    private void initAdmin() {
        var adminUser = new User().
                setUsername("admin").
                setEmail("admin@example.com").
                setFirstName("Nadya").
                setLastName("Salem").
                setPassword(passwordEncoder.encode (adminPass)).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.ADMIN));

        userRepository.save(adminUser);
    }

    private void initClient1() {
        var clientUser = new User().
                setUsername("client").
                setEmail("client@example.com").
                setFirstName("Lili").
                setLastName("Lilova").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }
    private void initClient2() {
        var clientUser = new User().
                setUsername("petar").
                setEmail("petar@example.com").
                setFirstName("Petar").
                setLastName("Petrov").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }
    private void initClient3() {
        var clientUser = new User().
                setUsername("valentin").
                setEmail("valentin@example.com").
                setFirstName("Valentin").
                setLastName("Valentin").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }
    private void initClient4() {
        var clientUser = new User().
                setUsername("lina").
                setEmail("linai@example.com").
                setFirstName("Nedka").
                setLastName("Pavlova").
                setPassword(passwordEncoder.encode("client")).
                setUserRoles(userRoleRepository.findByUserRole(UserRoleEnum.CLIENT));

        userRepository.save(clientUser);
    }

}
