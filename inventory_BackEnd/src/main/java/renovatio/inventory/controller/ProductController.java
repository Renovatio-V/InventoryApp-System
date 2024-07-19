package renovatio.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import renovatio.inventory.exception.resourceNotFoundException;
import renovatio.inventory.model.Product;
import renovatio.inventory.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/inventory-app
@RequestMapping("inventory-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    //http://localhost:8080/inventory-app/products
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> products = this.productService.listProducts();
        logger.info("Products Obtained");
        products.forEach((product -> logger.info(product.toString())));
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        logger.info("Product to add: " + product);
        return this.productService.saveProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = this.productService.searchProductById(id);
        if (product != null){
            return ResponseEntity.ok(product);
        } else {
            throw new resourceNotFoundException("Unable to find the Id" +  id);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productUpdated){
        Product product = this.productService.searchProductById(id);
        if(product == null){
            throw new resourceNotFoundException("ID Not Found "+ id);
        }
        product.setDescription(productUpdated.getDescription());
        product.setPrice(productUpdated.getPrice());
        product.setStock(productUpdated.getStock());
        this.productService.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id){
        Product product = productService.searchProductById(id);
        if(product == null){
            throw new resourceNotFoundException("ID Not found" + id);
        }
        this.productService.deleteProductById(product.getIdProduct());
        Map<String, Boolean> answer = new HashMap<>();
        answer.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(answer);
    }
}
