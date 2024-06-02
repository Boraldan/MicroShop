package boraldan.front.service;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.front.rest_client.ShopRestClient;
import boraldan.front.service.i_service.ItemFrontService;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemFrontServiceV_1 implements ItemFrontService {

    private final ShopRestClient restClient;
    private final ModelMapper modelMapper;


    public Item getAndConvertItem(UUID itemId, String itemClassName) {
        Class<? extends Item> clazz = null;
        try {
            clazz = (Class<? extends Item>) Class.forName(itemClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restClient.getItem(itemId, clazz);
    }

    public <T extends Item> List<String> getFieldNames(T obj, Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                fieldNames.add(field.getName() + ": " + value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldNames;
    }

    public <T extends Item> T convertToNeedItem(Object item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(item, targetType);
    }

}
