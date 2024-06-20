package com.backend_happibee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend_happibee.repositories.ApicultorRepository;

@Service
public class ApicultorService implements UserDetailsService {
    private final ApicultorRepository apicultorRepository;

    @Autowired
    public ApicultorService(ApicultorRepository apicultorRepository) {
        this.apicultorRepository = apicultorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return apicultorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
