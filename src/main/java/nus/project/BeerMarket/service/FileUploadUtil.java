package nus.project.BeerMarket.service;

import java.io.*;
import java.nio.file.*;
 
import org.springframework.web.multipart.MultipartFile;

// This util class is responsible for creating the directory if it does not exist
// and save the upload file (image) into the file system
public class FileUploadUtil {
    
    public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        
                Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {        
            throw new IOException("Could not save image file: " + fileName, ex);
        }      
    }
}

