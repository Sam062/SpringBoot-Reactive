package base.repo;

import entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepo extends ReactiveMongoRepository<ProductEntity, Integer> {


}
