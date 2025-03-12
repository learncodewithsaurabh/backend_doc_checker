package com.cg.backend_doc_checker.controller;

import com.cg.backend_doc_checker.model.FileEntity;
import com.cg.backend_doc_checker.service.FileService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@RestController
@RequestMapping("/api/file")
public class FileController {

    FileController(){
        System.err.println("In FileController!!!");
    }
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileEntity savedFile = fileService.saveFile(file);
            return ResponseEntity.ok(savedFile);
        } catch (IOException | TikaException e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFile(@PathVariable Long id) {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);
        return fileEntityOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);
        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(fileEntity.getData());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    public ResponseEntity<FileEntity> updateFile(@RequestBody FileEntity fileEntity) throws IOException, TikaException {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(fileEntity.getId());
        if (fileEntityOptional.isPresent()) {
            FileEntity existingFile = fileEntityOptional.get();
            existingFile.setExtractedText(fileEntity.getExtractedText());
            fileService.saveFile((MultipartFile) existingFile);
            return ResponseEntity.ok(existingFile);
        }
        return ResponseEntity.notFound().build();
    }
}