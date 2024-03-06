package it.syncroweb.logintest.service;

import it.syncroweb.logintest.model.UserEntity;
import it.syncroweb.logintest.model.impl.CustomUserDetailsImpl;
import it.syncroweb.logintest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        System.out.println(user.getTokens());
        return CustomUserDetailsImpl.build(user);
    }

}
