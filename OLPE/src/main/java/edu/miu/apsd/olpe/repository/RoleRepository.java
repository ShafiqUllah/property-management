package edu.miu.apsd.olpe.repository;

import edu.miu.apsd.olpe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String name);
}