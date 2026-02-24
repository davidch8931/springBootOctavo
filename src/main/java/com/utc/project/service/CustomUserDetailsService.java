package com.utc.project.service;

import com.utc.project.entity.AuthUser;
import com.utc.project.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        AuthUser user = authUserRepository.findByCorreo(correo);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new User(user.getCorreo(), user.getPassword(), user.isCuentaVerificada(), true, true, true, new ArrayList<>());
    }
}