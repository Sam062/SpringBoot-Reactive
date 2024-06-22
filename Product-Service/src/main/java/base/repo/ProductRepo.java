package base.repo;

import entity.ProductEntity;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveMongoRepository<ProductEntity, Integer> {

    Flux<ProductEntity> findByPriceBetween(Range<Double> range);


}
