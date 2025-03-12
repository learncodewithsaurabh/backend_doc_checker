//package com.cg.backend_doc_checker.service.impl;
//
//import com.cg.backend_doc_checker.model.FileEntity;
//import com.cg.backend_doc_checker.repository.FileRepository;
//import com.cg.backend_doc_checker.service.FileService;
//import org.apache.tika.Tika;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Service
//public class FileServiceImpl implements FileService {
//    @Autowired
//    private FileRepository fileRepository;
//
//    private Tika tika = new Tika();
//
//    public FileEntity saveFile(MultipartFile file) throws IOException {
//        FileEntity fileEntity = new FileEntity();
//        fileEntity.setFileName(file.getOriginalFilename());
//        fileEntity.setFileType(file.getContentType());
//        fileEntity.setData(file.getBytes());
//
//        // Extract text from file
//        String extractedText = tika.parseToString(file.getInputStream());
//        fileEntity.setExtractedText(extractedText);
//
//        return fileRepository.save(fileEntity);
//    }
//
//    public Optional<FileEntity> getFile(Long id) {
//        return fileRepository.findById(id);
//    }
//}
