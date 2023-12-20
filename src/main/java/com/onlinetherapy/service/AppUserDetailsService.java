package com.onlinetherapy.service;


import com.onlinetherapy.models.entity.Role;
import com.onlinetherapy.models.entity.User;
import com.onlinetherapy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// NOTE: This is not annotated as @Service, because we will return it as a bean.
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.
                findByUsername(username).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private UserDetails map(User userEntity) {
        return
                //използваме дефолтната имплементация на Spring
                org.springframework.security.core.userdetails.User.builder().
                        username(userEntity.getUsername()).
                        password(userEntity.getPassword()).
                        authorities(userEntity.
                                getUserRoles().
                                stream().
                                map(this::map).
                                toList()).
                        build();
    }

    //обяснява на Spring какво може да прави юзъра с тази си роля
    private GrantedAuthority map(Role userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getUserRole().name());
    }
}
