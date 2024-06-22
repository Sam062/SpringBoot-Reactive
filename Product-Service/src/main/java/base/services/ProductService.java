package base.services;

import base.dto.ProductDto;
import base.repo.ProductRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utils.MapperUtils;

@RequiredArgsConstructor
@Service
public class ProductService {

    @NonNull
    private ProductRepo productRepo;


    public Mono<ProductDto> saveProduct(Mono<ProductDto> product) {
        return product
                .map(MapperUtils::productToEntity)
                .flatMap(productRepo::insert)
                .map(MapperUtils::productToDto);

    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> product) {
        return product
                .flatMap(inp -> productRepo.findById(inp.getProductId()))
                .flatMap(ent -> product.map(MapperUtils::productToEntity))
                .flatMap(productRepo::save)
                .map(MapperUtils::productToDto);

    }

    public Mono<Void> deleteProductById(Integer productId) {
        return productRepo.deleteById(productId);
    }

    public Mono<ProductDto> getProductById(Integer productId) {
        return productRepo.findById(productId)
                .map(MapperUtils::productToDto);
    }

    public Flux<ProductDto> getProductByPriceRange(Double min, Double max) {
        return productRepo.findByPriceBetween(Range.closed(min, max))
                //Range.closed is inclusive & Range.open is exclusive
                .map(MapperUtils::productToDto);
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepo.findAll()
                .map(MapperUtils::productToDto);
    }

}
