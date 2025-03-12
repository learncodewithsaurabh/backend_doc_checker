package com.cg.backend_doc_checker.utils;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class OCRUtil {

    private final Tesseract tesseract;

    public OCRUtil() {
        this.tesseract = new Tesseract();
        this.tesseract.setDatapath("/path/to/tessdata"); // Set Tesseract data path
//        this.tesseract.setDatapath("C:/Tesseract/tessdata");  // Update the path accordingly
    }

    public String extractTextFromImage(File file) {
        try {
            return tesseract.doOCR(file);
        } catch (Exception e) {
            throw new RuntimeException("Error extracting text from image", e);
        }
    }
}