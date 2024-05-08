package boraldan.shop.service.i_service;

import boraldan.shop.repository.PriceUnifiedRepo;

import java.util.Optional;
import java.util.UUID;


public interface PriceUnifiedService<T, R extends PriceUnifiedRepo<T>> {

    R getPriceRepo();

    default Optional<T> getById(UUID id) {
        return getPriceRepo().findById(id);}

    default T save(T price) {
        return getPriceRepo().save(price);
    }

    default void delete(T price) {
        getPriceRepo().delete(price);
    }
}
