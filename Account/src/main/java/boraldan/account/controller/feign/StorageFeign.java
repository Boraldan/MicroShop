package boraldan.account.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "storage")
public interface StorageFeign {


}
