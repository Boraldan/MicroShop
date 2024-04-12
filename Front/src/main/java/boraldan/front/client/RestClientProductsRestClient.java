package boraldan.front.client;

import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientProductsRestClient {

    private static final ParameterizedTypeReference<List<Item>> PRODUCTS_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;

    public ListItemDto findItem(Category category) {
        return this.restClient.post()
                .uri("/shop/category")
                .contentType(MediaType.APPLICATION_JSON)
                .body(category)
                .retrieve()
                .body(ListItemDto.class);
    }

    public List<Item> findAllProducts(String filter) {
        return this.restClient
                .get()
                .uri("/catalogue-api/products?filter={filter}", filter)
                .retrieve()
                .body(PRODUCTS_TYPE_REFERENCE);
    }

//    @Override
//    public Item createProduct(String title, String details) {
//        try {
//            return this.restClient
//                    .post()
//                    .uri("/catalogue-api/products")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(new NewProductPayload(title, details))
//                    .retrieve()
//                    .body(Item.class);
//        } catch (HttpClientErrorException.BadRequest exception) {
//            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
//            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
//        }
//    }

    public Optional<Item> findProduct(int productId) {
        try {
            return Optional.ofNullable(this.restClient.get()
                    .uri("/catalogue-api/products/{productId}", productId)
                    .retrieve()
                    .body(Item.class));
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }
    }
//
//    @Override
//    public void updateProduct(int productId, String title, String details) {
//        try {
//            this.restClient
//                    .patch()
//                    .uri("/catalogue-api/products/{productId}", productId)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(new UpdateProductPayload(title, details))
//                    .retrieve()
//                    .toBodilessEntity();
//        } catch (HttpClientErrorException.BadRequest exception) {
//            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
//            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
//        }
//    }

    public void deleteProduct(int productId) {
        try {
            this.restClient
                    .delete()
                    .uri("/catalogue-api/products/{productId}", productId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }
}
