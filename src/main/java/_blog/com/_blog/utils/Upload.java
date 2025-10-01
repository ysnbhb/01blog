package _blog.com._blog.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import _blog.com._blog.Exception.UserExeption;

public class Upload {

    private static final String UPLOAD_DIR = "uploads/images";

    @JsonIgnore
    public static boolean isRealPhoto(MultipartFile file) throws UserExeption {
        if (file == null || file.isEmpty()) {
            throw new UserExeption(400, "Image file is required");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new UserExeption(400, "File is not an image");
        }

        try {
            byte[] bytes = file.getBytes();
            if (!isValidImage(bytes)) {
                throw new UserExeption(400, "Invalid image content");
            }
            return true;
        } catch (IOException e) {
            throw new UserExeption(400, "Failed to read image data");
        }
    }

    @JsonIgnore
    public static boolean isValidImage(byte[] data) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
            return img != null;
        } catch (IOException e) {
            return false;
        }
    }

    public static String saveImage(MultipartFile file) throws UserExeption {
        isRealPhoto(file);

        String projectDir = System.getProperty("user.dir");
        File dir = new File(projectDir, UPLOAD_DIR);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String extension = getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + (extension.isEmpty() ? ".jpg" : extension);

        File destination = new File(dir, fileName);
        try {
            file.transferTo(destination);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserExeption(500, "Failed to save image: " + e.getMessage());
        }

        return fileName;
    }

    private static String getExtension(String originalName) {
        if (originalName == null)
            return "";
        int dotIndex = originalName.lastIndexOf(".");
        return (dotIndex >= 0) ? originalName.substring(dotIndex) : "";
    }
}
