package com.cg.backend_doc_checker.repository;

import com.cg.backend_doc_checker.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
