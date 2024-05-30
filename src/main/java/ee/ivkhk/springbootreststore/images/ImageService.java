package ee.ivkhk.springbootreststore.images;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${uploadPath}")
    private String uploadPath;

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(MultipartFile file) {
        try {
            String uuid = UUID.randomUUID().toString();
            String originalFilename = uuid + "_" + file.getOriginalFilename();
            String smallFilename = uuid + "_small_" + file.getOriginalFilename();

            // Save original file
            File originalFile = new File(uploadPath, originalFilename);
            file.transferTo(originalFile);

            // Resize and save the small version
            BufferedImage originalImage = ImageIO.read(originalFile);
            BufferedImage smallImage = Scalr.resize(originalImage, Scalr.Mode.FIT_TO_WIDTH, 100);
            File smallFile = new File(uploadPath, smallFilename);
            ImageIO.write(smallImage, "jpg", smallFile);

            // Create and save Image entity
            Image image = new Image();
            image.setPathToOriginal(originalFile.getAbsolutePath());
            image.setPathToSmall(smallFile.getAbsolutePath());
            image.setFilename(file.getOriginalFilename());
            imageRepository.save(image);
            return image;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
