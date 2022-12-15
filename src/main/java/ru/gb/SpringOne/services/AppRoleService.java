package ru.gb.SpringOne.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.gb.SpringOne.models.AppRole;
import ru.gb.SpringOne.repositories.AppRoleRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppRoleService{
    private final AppRoleRepository roleRepository;

    AppRole getByName(String roleName) throws RoleNotFoundException {
        return roleRepository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException());
    }
}
