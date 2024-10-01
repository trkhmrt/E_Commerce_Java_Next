package com.e_commerce.E_commerce.repositories;

import com.e_commerce.E_commerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllById(Iterable<Long> ids);

}
