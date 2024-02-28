package Services;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import java.text.SimpleDateFormat;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.stream.FileImageOutputStream;

public class GifMaker {
    private String directoryPath;

    public GifMaker(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void createGif() {
        try {
            validateDirectory();

            File[] imageFiles = getJpgAndPngFiles();
            if (imageFiles.length < 2) {
                throw new Exception("\u001B[41m\u001B[37mInsufficient images in the directory. At least 2 JPG/PNG images required.\u001B[0m");
            }

            var gifFileName = generateGifFileName();
            createGifFile(directoryPath,gifFileName, imageFiles);
        } catch (Exception e) {
            System.err.println("\u001B[41m\u001B[37mError: " + e.getMessage() + "\u001B[0m");
        }
    }

    private void validateDirectory() throws Exception {
        var directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new Exception("Directory not found.");
        }
    }

    private File[] getJpgAndPngFiles() {
        var directory = new File(directoryPath);
        return directory.listFiles(
                file -> file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")));
    }

    private String generateGifFileName() {
        var dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        var timestamp = dateFormat.format(new Date());
        return "GIF_" + timestamp + ".gif";
    }
   
    private void createGifFile(String filePath, String fileName, File[] imageFiles) {
        try {
            File outputFile = new File(filePath, fileName);
    
            BufferedImage firstImage = ImageIO.read(imageFiles[0]);
    
            ImageWriter writer = ImageIO.getImageWritersByFormatName("gif").next();
    
            ImageOutputStream output = new FileImageOutputStream(outputFile);
            writer.setOutput(output);
            writer.prepareWriteSequence(null);
    
            for (File imageFile : imageFiles) {
                BufferedImage image = ImageIO.read(imageFile);
                writer.writeToSequence(new javax.imageio.IIOImage(image, null, null), null);
            }
    
            writer.endWriteSequence();
            output.close();
    
            System.out.println("GIF created successfully: " + fileName);
        } catch (IOException e) {
            System.err.println("\u001B[41m\u001B[37mError creating GIF: " + e.getMessage() + "\u001B[0m");
        }
    }
}
