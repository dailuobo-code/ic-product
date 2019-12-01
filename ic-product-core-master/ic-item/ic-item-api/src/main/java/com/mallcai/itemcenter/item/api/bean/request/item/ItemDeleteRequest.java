package com.mallcai.itemcenter.item.api.bean.request.item;

import com.mallcai.itemcenter.api.request.AbstractRequest;
import com.mallcai.itemcenter.dto.IdVersionPair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ItemDeleteRequest
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/14 15:27<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDeleteRequest extends AbstractRequest {
    private static final long serialVersionUID = 3662545378490283803L;

    private List<IdVersionPair> targetList;

    private Long sellerId;

    @Override
    public void checkParam() {
        super.checkParam();
        nonNull(targetList, "target.list.is.null");
        nonNull(sellerId, "sellerId.id.is.null");
    }
}
