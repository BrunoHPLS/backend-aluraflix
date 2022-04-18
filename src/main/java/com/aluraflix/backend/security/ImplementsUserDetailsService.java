package com.aluraflix.backend.security;

import com.aluraflix.backend.entity.model.User;
import com.aluraflix.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = repository.findByUsername(username).get();
            System.out.println(user.getUsername()+" "+user.getPassword());
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),true,true,true,true,user.getAuthorities());
        }catch(NoSuchElementException ex){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
    }
}
