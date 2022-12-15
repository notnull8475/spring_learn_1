package ru.gb.SpringOne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.SpringOne.models.AppRole;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
     Optional<AppRole> findByName(String roleName);
}
