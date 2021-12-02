package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnlineUserServiceImpl implements UserDetailsService {




    private final UserRepository userRepository;

    public OnlineUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));

     return mapToUserDetails(user);
    }

    private UserDetails mapToUserDetails(User user) {
        List<GrantedAuthority> authorities =
                user.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());
        return new OnlineUser(
                user.getUsername(),
                user.getPassword(),
                authorities);

    }
}
