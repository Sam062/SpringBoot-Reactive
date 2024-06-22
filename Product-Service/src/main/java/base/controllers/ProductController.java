package base.controllers;

import base.dto.ProductDto;
import base.services.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RequiredArgsConstructor
@RequestMapping(value = "product",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@RestController
public class ProductController {

    @NonNull
    private ProductService productService;


    @PostMapping("saveProduct")
    public Mono<ResponseEntity<ProductDto>> saveProduct(@RequestBody Mono<ProductDto> product) {
        return productService.saveProduct(product)
                .map(in -> ResponseEntity.ok().body(in))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @PostMapping("updateProduct")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@RequestBody Mono<ProductDto> product) {
        return productService.updateProduct(product)
                .map(in -> ResponseEntity.ok().body(in))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("deleteProductById/{id}")
    public Mono<Void> deleteProductById(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }

    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(in -> ResponseEntity.ok().body(in))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("productByPriceRange/{min}/{max}")
    public Flux<ProductDto> productByPriceRange(
            @PathVariable Double min,
            @PathVariable Double max) {
        return productService
                .getProductByPriceRange(min, max);
    }

    @GetMapping("getAllProducts")
    public Flux<ProductDto> getAllProducts() {
        return productService
                .getAllProducts();
    }

}
