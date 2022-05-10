package com.emplify.ema.repositories;

import com.emplify.ema.models.auth.ERole;
import com.emplify.ema.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
