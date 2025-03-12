package com.cg.backend_doc_checker.controller;
import com.cg.backend_doc_checker.model.Document;
import com.cg.backend_doc_checker.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadDocument(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(documentService.uploadDocument(userId, file));
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<Document> getDocument(@PathVariable Long documentId) {
        return ResponseEntity.ok(documentService.getDocument(documentId));
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<String> updateDocument(@PathVariable Long documentId, @RequestBody String newText) {
        return ResponseEntity.ok(documentService.updateDocument(documentId, newText));
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        return ResponseEntity.ok(documentService.deleteDocument(documentId));
    }
}
