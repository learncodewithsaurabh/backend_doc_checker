package com.cg.backend_doc_checker.service;

import com.cg.backend_doc_checker.model.Document;
import com.cg.backend_doc_checker.repository.DocumentRepository;
import com.cg.backend_doc_checker.repository.UserRepository;
import com.cg.backend_doc_checker.utils.OCRUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DocumentService {

    private final OCRUtil ocrUtil;
    private final DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;

    public DocumentService(OCRUtil ocrUtil, DocumentRepository documentRepository) {
        this.ocrUtil = ocrUtil;
        this.documentRepository = documentRepository;
    }

    public String uploadDocument(Integer userId, MultipartFile file) {
        try {
            // Save the uploaded file to a local directory
            Path path = Paths.get("uploads/" + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            file.transferTo(path);

            // Extract text from the image using OCR
            String extractedText = ocrUtil.extractTextFromImage(new File(path.toUri()));

            // Save the extracted text and file info into the database
            Document document = new Document();
            document.setFileame(file.getOriginalFilename());
            document.setExtractedText(extractedText);
            // Assume user is fetched from database by userId (simplified for demo)
            document.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
            documentRepository.save(document);

            return "File uploaded and text extracted successfully!";
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }

    public Document getDocument(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public String updateDocument(Long documentId, String newText) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setExtractedText(newText);
        documentRepository.save(document);
        return "Document updated successfully!";
    }

    public String deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
        return "Document deleted successfully!";
    }
}
