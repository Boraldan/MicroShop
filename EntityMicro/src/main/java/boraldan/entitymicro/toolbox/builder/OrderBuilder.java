package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.account.entity.order.OrderPay;
import boraldan.entitymicro.account.entity.order.OrderStatus;
import boraldan.entitymicro.account.entity.order.OrderUnit;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.account.entity.seller.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderBuilder {
    private final Order order;

    private OrderBuilder() {
        this.order = new Order();
        this.order.setCreatAt(LocalDateTime.now());
    }

    public static OrderBuilder create() {
        return new OrderBuilder();
    }

    public OrderBuilder setId(UUID id) {
        this.order.setId(id);
        return this;
    }

    public OrderBuilder setSubTotal(BigDecimal subTotal) {
        this.order.setSubTotal(subTotal);
        return this;
    }

    public OrderBuilder setTotal(BigDecimal total) {
        this.order.setTotal(total);
        return this;
    }

    public OrderBuilder setOrderStatus(OrderStatus orderStatus) {
        this.order.setOrderStatus(orderStatus);
        return this;
    }

    public OrderBuilder setOrderPay(OrderPay pay) {
        this.order.setPay(pay);
        return this;
    }

    public OrderBuilder setCustomer(Customer customer) {
        this.order.setCustomer(customer);
        return this;
    }

    public OrderBuilder setSeller(Seller seller) {
        this.order.setSeller(seller);
        return this;
    }

    public OrderBuilder setItems(List<OrderUnit> items) {
        this.order.setItems(items);
        return this;
    }

    public OrderBuilder setCoupon(Coupon coupon) {
        if (coupon == null) return this;
        this.order.setCoupon(coupon);
        return this;
    }

    public OrderBuilder initPrices() {
        this.order.initPrices();
        return this;
    }


    public Order build() {
        return this.order;
    }
}
