package com.backend_happibee.infrastructure.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_happibee.model.entities.Apicultor;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth/public/")
public class AuthenticationAPI {

    private final AuthenticationManager authenticationManager;

    public AuthenticationAPI(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity<Apicultor> login(@RequestBody @Valid final AuthenticationRequest request) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            final Apicultor user = (Apicultor) authentication.getPrincipal();

            // Retorne apenas os detalhes do usuário após a autenticação bem-sucedida
            return ResponseEntity.ok(user);
        } catch (final BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}