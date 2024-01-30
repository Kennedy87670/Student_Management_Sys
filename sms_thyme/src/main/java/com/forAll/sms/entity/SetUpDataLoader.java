package com.forAll.sms.entity;

import com.forAll.sms.repository.PrivilegeRepository;
import com.forAll.sms.repository.RoleRepository;
import com.forAll.sms.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    private final UserRepository userRepository;
    private final  RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege editPrivilege = createPrivilegeIfNotFound("EDIT_PRIVILEGE");
        Privilege createPrivilege = createPrivilegeIfNotFound("CREATE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, editPrivilege, deletePrivilege);
        List<Privilege> staffPrivileges = Arrays.asList(readPrivilege, writePrivilege, editPrivilege);
        List<Privilege> parentPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        List<Privilege> studentPrivileges = Arrays.asList(readPrivilege);

        createRoleIfNotFound("ADMIN", adminPrivileges);
        createRoleIfNotFound("STAFF", staffPrivileges);
        createRoleIfNotFound("PARENT", parentPrivileges);
        createRoleIfNotFound("STUDENT", studentPrivileges);

        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole != null) {
            User user = new User();
            user.setFirstname("user");
            user.setLastname("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@user.com");
            user.setRoles((Set<Role>) Arrays.asList(adminRole));
            user.setEnabled(true);
            userRepository.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
