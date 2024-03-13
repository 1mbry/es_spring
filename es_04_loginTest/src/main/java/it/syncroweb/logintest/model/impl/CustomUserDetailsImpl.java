package it.syncroweb.logintest.model.impl;

import it.syncroweb.logintest.model.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomUserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    //private boolean enabled;
    private String firstname;
    private String lastname;
    private String password;
    private String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetailsImpl(Long id, String email, String firstname, String lastname, String password, String username , Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        //this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    public static CustomUserDetailsImpl build(UserEntity user){
        List<GrantedAuthority> authorities1 = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new CustomUserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getUsername(),
                authorities1);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
