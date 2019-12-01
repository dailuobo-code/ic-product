package dailuobo.dao.mapper;

import com.dailuobo.ic.domain.dao.model.product.ProductGroupItemDO;
import com.dailuobo.ic.dto.spec.ProductAttributeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductGroupMapper {
    List<ProductGroupItemDO> listAllGroupItems(@Param("cityId") Integer cityId);

    List<ProductAttributeDTO> listProductAttributes(@Param("cityId")Integer cityId,@Param("list")List<Integer> cityProductId);

    int insertProductAttr(ProductAttributeDTO productAttributeDTOS);
}
