package _blog.com._blog.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;
import _blog.com._blog.Exception.ProgramExeption;

public class Upload {

    private static final String UPLOAD_DIR_Photo = "uploads/images";
    private static final String UPLOAD_DIR_video = "uploads/video";

    public static boolean isRealPhoto(MultipartFile file) throws ProgramExeption {
        if (file == null || file.isEmpty()) {
            throw new ProgramExeption(400, "Image file is required");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new ProgramExeption(400, "File is not an image");
        }

        try {
            byte[] bytes = file.getBytes();
            if (!isValidImage(bytes)) {
                throw new ProgramExeption(400, "Invalid image content");
            }
            return true;
        } catch (IOException e) {
            throw new ProgramExeption(400, "Failed to read image data");
        }
    }

    public static boolean isValidImage(byte[] data) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
            return img != null;
        } catch (IOException e) {
            return false;
        }
    }

    public static String saveImage(MultipartFile file) throws ProgramExeption {
        isRealPhoto(file);

        String projectDir = System.getProperty("user.dir");
        File dir = new File(projectDir, UPLOAD_DIR_Photo);

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
            throw new ProgramExeption(500, "Failed to save image");
        }

        return fileName;
    }

    public static void delete(String file, String type) {
        if (file == null || file.isEmpty()) {
            System.out.println("No file name provided, skipping delete.");
            return;
        }

        String projectDir = System.getProperty("user.dir");
        String baseDir;

        if ("image".equalsIgnoreCase(type)) {
            baseDir = UPLOAD_DIR_Photo;
        } else if ("video".equalsIgnoreCase(type)) {
            baseDir = UPLOAD_DIR_video;
        } else {
            System.err.println("Unknown file type: " + type);
            return;
        }

        Path filePath = Paths.get(projectDir, baseDir, file);

        try {
            Files.deleteIfExists(filePath);
            System.out.println("Deleted file: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + filePath);
            e.printStackTrace();
        }
    }

    private static String getExtension(String originalName) {
        if (originalName == null)
            return "";
        int dotIndex = originalName.lastIndexOf(".");
        return (dotIndex >= 0) ? originalName.substring(dotIndex) : "";
    }

    public static boolean isLikelyVideo(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("video/")) {
                return false;
            }

            byte[] header = file.getBytes();
            String headerStr = new String(header, 0, Math.min(header.length, 64));
            return headerStr.contains("ftyp") || headerStr.contains("moov");
        } catch (Exception e) {
            return false;
        }
    }

    public static String saveVideo(MultipartFile file) throws ProgramExeption {
        if (!isLikelyVideo(file)) {
            throw new ProgramExeption(400, "File is not an video");
        }
        String projectDir = System.getProperty("user.dir");
        File dir = new File(projectDir, UPLOAD_DIR_video);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String extension = getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + (extension.isEmpty() ? ".mp4" : extension);
        File destination = new File(dir, fileName);
        try {
            file.transferTo(destination);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProgramExeption(500, "Failed to save video");
        }
        return fileName;
    }

}
