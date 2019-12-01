package com.mallcai.itemcenter.base.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mallcai.itemcenter.common.BaseConverter;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.api.bean.response.item.ImageInfo;
import com.mallcai.itemcenter.item.api.bean.response.item.ItemDetailContentInfo;
import com.mallcai.itemcenter.item.model.Image;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BaseItemConverter
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 16:58<br/>
 */
public interface BaseItemConverter extends BaseConverter {
    default List<ImageInfo> domain2dto(List<Image> s) {
        if ( s == null ) {
            return null;
        }

        List<ImageInfo> infos = new ArrayList<>(s.size());
        for ( Image image : s ) {
            infos.add( imageToImageInfo( image ) );
        }

        return infos;
    }

    default ImageInfo imageToImageInfo(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageInfo imageInfo = new ImageInfo();

        imageInfo.setName( image.getName() );
        imageInfo.setUrl( image.getUrl() );

        return imageInfo;
    }

    TypeReference<List<ItemDetailContentInfo>> LIST_TYPE_REFERENCE = new TypeReference<List<ItemDetailContentInfo>>() {};

    default List<ItemDetailContentInfo> json2list(String json) {
        if (StringUtils.isEmpty(json)) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(json, LIST_TYPE_REFERENCE);
        } catch (IOException e) {
            throw new ServiceException("fail.to.transfer.detail.content.to.info");
        }
    }

}
