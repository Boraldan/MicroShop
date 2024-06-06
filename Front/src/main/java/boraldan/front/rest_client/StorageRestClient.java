package boraldan.front.rest_client;

import boraldan.entitymicro.storage.dto.ReserveDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class StorageRestClient {

    private final RestClient restClient;
    public ResponseEntity<Void> setReserve(ReserveDtoList reserveDtoList) {
        return this.restClient.patch()
                .uri("/storage/reserve/set")
                .contentType(MediaType.APPLICATION_JSON)
                .body(reserveDtoList)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteReserve(ReserveDtoList reserveDtoList) {
        return this.restClient.patch()
                .uri("/storage/reserve/del")
                .contentType(MediaType.APPLICATION_JSON)
                .body(reserveDtoList)
                .retrieve()
                .toBodilessEntity();
    }
}
