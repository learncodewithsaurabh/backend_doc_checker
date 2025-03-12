package com.cg.backend_doc_checker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @Lob
    private String extractedText;
}