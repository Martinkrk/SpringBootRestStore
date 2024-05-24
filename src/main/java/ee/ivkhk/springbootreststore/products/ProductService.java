package ee.ivkhk.springbootreststore.products;

import ee.ivkhk.springbootreststore.images.Image;
import ee.ivkhk.springbootreststore.images.ImageRepository;
import ee.ivkhk.springbootreststore.images.ImageService;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;

    public ProductService(ProductRepository productRepository, ImageRepository imageRepository, ImageService imageService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    @Value("${uploadDir}")
    private String uploadPath;
    @Value("${temporal}")
    private String temporal;

    public void add(Product product) {
        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File is null or empty. Please select a file to upload.");
            }

            // Create directories if they do not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Add image to product and save product
            product.getImages().add(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        product.setCreated_at(LocalDate.now());
        productRepository.save(product);
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    public void update(Product product) {
        Optional<Product> row = productRepository.findById(product.getId());
        if (row.isPresent()) {
            Product productItem = row.get();
            if (!product.getName().isEmpty()) {
                productItem.setName(product.getName());
            }
            productItem.setUpdated_at(LocalDate.now());
            productRepository.save(productItem);
        }
    }
}
