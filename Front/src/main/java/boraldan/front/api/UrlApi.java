package boraldan.front.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Адреса url для запросов JSON
 */
@Data
@Component
@ConfigurationProperties("url")
@PropertySource("classpath:api.properties")
public class UrlApi {

    private String lot_api;
    private String car_api;
    private String fly_api;
    private String items_api;
    private String carts_api;
    private String card_api;
    private String session_shop_api;

}
