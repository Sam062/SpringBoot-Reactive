package utils;

import base.dto.ProductDto;
import entity.ProductEntity;
import org.springframework.beans.BeanUtils;


public class MapperUtils {

    private MapperUtils() {
    }

    public static ProductDto productToDto(ProductEntity entity) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(entity, productDto);
        return productDto;
    }

    public static ProductEntity productToEntity(ProductDto dto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(dto, productEntity);
        return productEntity;
    }
}
