package ee.ivkhk.springbootreststore.products;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("list")
    public List<Product> listProducts() {
        return productService.list();
    }

    @PostMapping("item")
    public void addProduct(@RequestBody Product product, @RequestBody MultipartFile file) {
        productService.add(product, file);
    }

    @DeleteMapping("item/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PutMapping("item")
    public void updateProduct(@RequestBody Product product) {
        productService.update(product);
    }
}
