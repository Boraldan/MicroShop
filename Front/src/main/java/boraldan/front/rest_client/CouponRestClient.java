package boraldan.front.rest_client;

import boraldan.entitymicro.account.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;


@RequiredArgsConstructor
public class CouponRestClient {

    private final RestClient restClient;

    public Coupon getCoupon(String couponName) {
        return this.restClient.post()
                .uri("/account/coupon/get")
                .contentType(MediaType.APPLICATION_JSON)
                .body(couponName)
                .retrieve()
                .body(Coupon.class);
    }

}
