package com.example.NstCollections.Role.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.NstCollections.Role.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT role FROM Role role WHERE role.name = ?1")
    Optional<Role> findRoleByName(String name);

}