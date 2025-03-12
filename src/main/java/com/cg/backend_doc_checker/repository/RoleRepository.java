package com.cg.backend_doc_checker.repository;

import com.cg.backend_doc_checker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}