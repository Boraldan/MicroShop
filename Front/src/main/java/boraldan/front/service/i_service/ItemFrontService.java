package boraldan.front.service.i_service;

import boraldan.entitymicro.shop.entity.item.Item;

import java.util.List;
import java.util.UUID;

public interface ItemFrontService {

    Item getAndConvertItem(UUID itemId, String itemClassName);

    <T extends Item> List<String> getFieldNames(T obj, Class<?> clazz);

    <T extends Item> T convertToNeedItem(Object item, Class<?> clazz);
}
