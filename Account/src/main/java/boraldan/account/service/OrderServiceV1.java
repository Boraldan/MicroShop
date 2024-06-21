package boraldan.account.service;


import boraldan.account.controller.feign.StorageFeign;
import boraldan.account.repository.CouponRepo;
import boraldan.account.repository.order.OrderRepo;
import boraldan.account.repository.order.OrderUnitRepo;
import boraldan.account.repository.person.CustomerRepo;
import boraldan.account.service.i_service.OrderService;
import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.account.entity.order.OrderPay;
import boraldan.entitymicro.account.entity.order.OrderStatus;
import boraldan.entitymicro.account.entity.order.OrderUnit;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.storage.dto.ReserveDto;
import boraldan.entitymicro.storage.dto.ReserveDtoList;
import boraldan.entitymicro.toolbox.builder.OrderBuilder;
import boraldan.entitymicro.toolbox.builder.ReserveDtoBuilder;
import boraldan.entitymicro.toolbox.builder.ReserveDtoListBuilder;
import boraldan.entitymicro.toolbox.builder.UnitOrderBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceV1 implements OrderService {

    private final OrderRepo orderRepo;
    private final StorageFeign storageFeign;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;
    private final OrderUnitRepo orderUnitRepo;


    @Transactional
    public Order creatOrder(CartDto cartDto) {

        Customer customer = customerRepo.findById(cartDto.getCustomer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Order order = orderRepo.save(OrderBuilder.create()
                .setCustomer(customer)
                .setCoupon(cartDto.getCoupon() != null ? couponRepo.findById(cartDto.getCoupon().getId())
                        .orElse(null) : null)
                .setOrderStatus(OrderStatus.CREATED)
                .setOrderPay(OrderPay.NOT_PAID)
                .build());

        List<OrderUnit> orderUnitList = cartDto.getCartUnitDtoList().stream()
                .map(unit -> UnitOrderBuilder.create()
                        .setItemId(unit.getItem().getId().toString())
                        .setItemTitle(unit.getItem().getTitle())
                        .setQuantity(unit.getUnitQuantity())
                        .setPriceItem(unit.getItem().getPrice().getCustomPrice())
                        .setPriceUnit(unit.getItem().getPrice().getCustomPrice().multiply(BigDecimal.valueOf(unit.getUnitQuantity())))
                        .setOrder(order)
                        .build()
                ).toList();

//        unitOrderList =  unitOrderRepo.saveAll(unitOrderList);
        order.setItems(orderUnitList);
        order.initPrices();

        if (order.getId() != null) {
            storageFeign.deleteReserveAfterSale(convertListReserveDto(cartDto));
        }

        return order;
    }

    private ReserveDtoList convertListReserveDto(CartDto cartDto) {
        List<ReserveDto> reserveDtoList = cartDto.getCartUnitDtoList().stream()
                .map(unit -> ReserveDtoBuilder.creat()
                        .setItemId(unit.getItem().getId())
                        .setReserve(unit.getUnitQuantity())
                        .build())
                .toList();
        return ReserveDtoListBuilder.creat().setReserveDtoList(reserveDtoList).build();
    }

}