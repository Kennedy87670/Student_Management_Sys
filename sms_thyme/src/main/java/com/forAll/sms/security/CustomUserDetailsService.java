package com.forAll.sms.security;


import com.forAll.sms.entity.Privilege;
import com.forAll.sms.entity.Role;
import com.forAll.sms.entity.User;
import com.forAll.sms.repository.RoleRepository;
import com.forAll.sms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
        }

        Set<Role> roles = (Set<Role>) user.getRoles();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(),
                true, true, true,
                getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(this::getPrivileges)
                .flatMap(Collection::stream)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<String> getPrivileges(Role role) {
        List<String> privileges = role.getPrivileges().stream()
                .map(Privilege::getName)
                .collect(Collectors.toList());
        privileges.add(role.getName());
        return privileges;



    }

}
