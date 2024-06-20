package com.backend_happibee.infrastructure.bootstrapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend_happibee.repositories.ApicultorRepository;
import com.backend_happibee.model.entities.Apicultor;

@Component
@Profile("bootstrapping")
public class UserBootstrapping implements CommandLineRunner {

    @Autowired
    private ApicultorRepository uRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        
        // Criar apicultor1
        if(uRepo.findByUsername("apicultor1@mail.com").isEmpty()){
            Apicultor ap1 = new Apicultor("apicultor1@mail.com",encoder.encode("apicultor1"),"Apicultor1", "123456789","Morada apicultor1");
            uRepo.save(ap1);
        }
        
        // Criar apicultor2
         if(uRepo.findByUsername("apicultor2@mail.com").isEmpty()){
            Apicultor ap2 = new Apicultor("apicultor2@mail.com",encoder.encode("apicultor2"), "Apicultor2", "987654321","Morada apicultor2");
            uRepo.save(ap2);
        }
    }
    
}
