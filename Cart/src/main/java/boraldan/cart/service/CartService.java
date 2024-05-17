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
import boraldan.entitymicro.toolbox.builder.UnitCartDtoBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
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
        List<CartUnit> cartUnitList = cartDto.getCartUnitDtoList().stream()
                .map(cartUnitDto -> {
                    CartUnit cartUnit = new CartUnit();
                    cartUnit.setItemId(cartUnitDto.getItem().getId());
                    cartUnit.setQuantity(cartUnitDto.getQuantity());
                    return cartUnit;
                }).toList();

        Optional<Cart> cartOptional = cartRepo.findByCustomerId(cartDto.getCustomer().getId());
        cartOptional.ifPresent(cartRepo::delete);

        Cart cart = CartBuilder.creat()
                .setCustomerId(cartDto.getCustomer().getId())
                .setCouponName(cartDto.getCoupon() != null ? cartDto.getCoupon().getCouponName() : null)
                .setCartUnitList(cartUnitList)
                .build();
        return cartRepo.save(cart);
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

    private List<CartUnitDto> getItemsFromShop(Cart cart) {
        List<CartUnitDto> cartUnitDtoList = new ArrayList<>();
        Hibernate.initialize(cart.getCartUnitList());
        if (!cart.getCartUnitList().isEmpty()) {
            List<UUID> listUuidItem = cart.getCartUnitList().stream().map(CartUnit::getItemId).toList();
            List<Item> itemList = shopFeign.getByUuidList(listUuidItem).getBody();
            if (itemList != null) {
                cartUnitDtoList = uuidsToItems(cart, itemList);
                return cartUnitDtoList;
            }
        }
        return cartUnitDtoList;
    }

    /**
     * Преобразуем  List<UUID> uuidItemList из Cart в  List<UnitCartDto> для CartDto.
     *
     * @param cart
     * @param listItem
     * @return List<UnitCartDto>
     */
    private List<CartUnitDto> uuidsToItems(Cart cart, List<Item> listItem) {
        Map<UUID, Item> itemMap = new HashMap<>();
        for (Item item : listItem) {
            itemMap.put(item.getId(), item);
        }
        List<CartUnitDto> listCartUnitDto = new ArrayList<>();
        for (CartUnit cartUnit : cart.getCartUnitList()) {
            CartUnitDto cartUnitDto = UnitCartDtoBuilder.creat()
                    .setItem(itemMap.get(cartUnit.getItemId()))
                    .setQuantity(cartUnit.getQuantity())
                    .build();
            listCartUnitDto.add(cartUnitDto);
        }
        return listCartUnitDto;
    }
}
