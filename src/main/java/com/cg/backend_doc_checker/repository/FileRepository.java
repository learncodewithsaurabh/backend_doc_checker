package com.cg.backend_doc_checker.repository;

import com.cg.backend_doc_checker.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}