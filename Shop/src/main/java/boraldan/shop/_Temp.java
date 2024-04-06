package boraldan.shop;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.shop.session.TestGson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _Temp {

    // преобразование списка -------------
    List<Item> itemList = new ArrayList<>();
    List<Car> carList = itemList.stream()
            .filter(item -> item instanceof Car)
            .map(item -> (Car) item)
            .collect(Collectors.toList());
    // -------------------

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

//        Gson gson = new GsonBuilder().create();
        Car car = new Car();
        Item item = new Car();
        Storage storage = new Storage();

        TestGson testGson = new TestGson();

//        System.out.println(gson.toJson(testGson));

        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(car);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            Item testGson1 = objectMapper.readValue(jsonString, Car.class);
            System.out.println(testGson1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        Gson gson = new Gson();
//        Car deserializedItem = gson.fromJson(jsonString, Car.class);
//        System.out.println(deserializedItem);

    }



}
