package boraldan.cart.service;


import boraldan.cart.controller.feign.AccountFeign;
import boraldan.cart.controller.feign.ShopFeign;
import boraldan.cart.repository.CartRepo;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;
import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.cart.entity.CartUnit;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.toolbox.builder.CartBuilder;
import boraldan.entitymicro.toolbox.builder.CartDtoBuilder;
import boraldan.entitymicro.toolbox.builder.CartUnitBuilder;
import boraldan.entitymicro.toolbox.builder.UnitCartDtoBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CartService {

    private final CartRepo cartRepo;
    private final AccountFeign accountFeign;
    private final ShopFeign shopFeign;

    public List<Cart> getAll() {
        return cartRepo.findAll();
    }

    public Optional<Cart> getById(UUID cartId) {
        return cartRepo.findById(cartId);
    }

    @Transactional
    public CartDto getCartDtoByCustomer(Customer customer) {
        Optional<Cart> cartOptional = cartRepo.findByCustomerId(customer.getId());
        return cartOptional.map(cart -> {
                    cartRepo.delete(cart);
                    return convertToCartDto(cart, customer);
                })
                .orElse(CartDtoBuilder.creat().setCustomer(customer).build());
    }

    @Transactional
    public Cart convertToCartAndSave(CartDto cartDto) {
        Optional<Cart> cartOptional = cartRepo.findByCustomerId(cartDto.getCustomer().getId());
        cartOptional.ifPresent(cartRepo::delete);

        Cart cart = cartRepo.save(CartBuilder.creat()
                .setCustomerId(cartDto.getCustomer().getId())
                .setCouponName(cartDto.getCoupon() != null ? cartDto.getCoupon().getCouponName() : null)
                .build());

        List<CartUnit> cartUnitList = cartDto.getCartUnitDtoList().stream()
                .map(cartUnitDto -> {
                    CartUnit cartUnit = CartUnitBuilder.create()
                            .setItemId(cartUnitDto.getItem().getId())
                            .setQuantity(cartUnitDto.getUnitQuantity())
                            .build();
                    cartUnit.setCart(cart);
                    return cartUnit;
                }).toList();

        cart.setCartUnitList(cartUnitList);

        return cart;
    }

    /**
     * Из сохраненной Cart для Customer преобразуем в CartDto.
     *
     * @param cart
     * @param customer
     * @return
     */
    private CartDto convertToCartDto(Cart cart, Customer customer) {
        return CartDtoBuilder.creat()
                .setCustomer(customer)
                .setCoupon(cart.getCouponName() != null ? accountFeign.getCoupon(cart.getCouponName()).getBody() : null)
                .setCartUnitDtoList(getItemsFromShop(cart))
                .build();
    }

    /**
     * Из сохраненной Cart для Customer возвращаем  List<CartUnitDto>
     *
     * @param cart
     * @return List<CartUnitDto>
     */
    private List<CartUnitDto> getItemsFromShop(Cart cart) {
        List<CartUnitDto> cartUnitDtoList = new ArrayList<>();
        Hibernate.initialize(cart.getCartUnitList());
        if (!cart.getCartUnitList().isEmpty()) {
            List<UUID> uuidList = cart.getCartUnitList().stream().map(CartUnit::getItemId).toList();
            List<Item> itemList = shopFeign.getByUuidList(uuidList).getBody();
            if (itemList != null) {
                cartUnitDtoList = uuidListToItemList(cart.getCartUnitList(), itemList);
                return cartUnitDtoList;
            }
        }
        return cartUnitDtoList;
    }

    /**
     * Преобразуем  List<CartUnit> в  List<UnitCartDto> для CartDto.
     *
     * @param cartUnitList
     * @param itemList
     * @return List<CartUnitDto
     */
    private List<CartUnitDto> uuidListToItemList(List<CartUnit> cartUnitList, List<Item> itemList) {
        Map<UUID, Item> itemMap = new HashMap<>();
        for (Item item : itemList) {
            itemMap.put(item.getId(), item);
        }
        List<CartUnitDto> listCartUnitDto = new ArrayList<>();
        for (CartUnit cartUnit : cartUnitList) {
            CartUnitDto cartUnitDto = UnitCartDtoBuilder.creat()
                    .setItem(itemMap.get(cartUnit.getItemId()))
                    .setQuantity(cartUnit.getQuantity())
                    .build();
            listCartUnitDto.add(cartUnitDto);
        }
        return listCartUnitDto;
    }
}
