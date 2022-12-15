package ru.gb.SpringOne.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.SpringOne.models.AppUser;
import ru.gb.SpringOne.repositories.AppUsersRepository;

import java.util.Collection;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = usersRepository.findByUsername(username).orElseThrow();
        Collection<SimpleGrantedAuthority> authorities = appUser.getRoles().stream().map(it -> {
            return new SimpleGrantedAuthority(it.getName());
        }).toList();
        return User.builder()
                .username(username)
                .password(appUser.getPassword())
                .authorities(authorities)
                .build();
    }


    public void saveUser(AppUser appUser){

    }
}
