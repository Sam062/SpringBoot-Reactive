package entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductEntity {

    @Id
    private Integer productId;
    private String productName;
    private Double price;

}
