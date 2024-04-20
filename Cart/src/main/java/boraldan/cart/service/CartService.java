package boraldan.cart.service;


import boraldan.cart.repository.CartRepo;
import boraldan.entitymicro.cart.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo;

    public List<Cart> getAll() {
        return cartRepo.findAll();
    }

    public Optional<Cart> getById(UUID cartId) {
        return cartRepo.findById(cartId);
    }

    @Transactional
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Transactional
    public Cart saveNew() {
        Cart cart = new Cart();
        cart.setCreatAt(LocalDateTime.now());
        return cartRepo.save(cart);
    }

    @Transactional
    public Cart update(Cart cart) {
        return cartRepo.save(cart);
    }

//    public List<Car> cloneCart() {
//        Cart cart = (Cart) session.getAttribute("cart");
//        List<Car> oldCarList = new ArrayList<>();
//        for (Car car : cart.getCarList()) {
//            try {
//                oldCarList.add((Car) car.clone());
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//        }
//        return oldCarList;
//    }
//
//    public void addItem(Car dbCar, int lot) throws CloneNotSupportedException {
//        Cart cart = (Cart) session.getAttribute("cart");
//        Car car = dbCar.clone();
//        car.setVolume(lot);
//        if (cart.getCarList().stream().noneMatch(x -> x.getId() == dbCar.getId())) {
//            cart.getCarList().add(car);
//            carService.minusVolumeDB(dbCar, car.getVolume());
//        } else {
//            for (Car el : cart.getCarList()) {
//                if (el.getId() == car.getId()) {
//                    el.setVolume(el.getVolume() + car.getVolume());
//                    carService.minusVolumeDB(dbCar, car.getVolume());
//                }
//            }
//        }
//        session.setAttribute("cart", cart);
//    }
//
//    public void dellItem(int idCar) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        Car car = cart.getCarList().stream().filter(el -> el.getId() == idCar).findFirst().get();
//        carService.addVolumeDB(car);
//        cart.getCarList().remove(car);
//        session.setAttribute("cart", cart);
//    }
//
//    public boolean checkFalseLot(Cart cart, List<Car> oldCarList) {
//        for (int i = 0; i < cart.getCarList().size(); i++) {
//            if (cart.getCarList().get(i).getVolume() < 1) {
//                cart.setCarList(oldCarList);
//                return true;
//            }
//            if (cart.getCarList().get(i).getVolume() > oldCarList.get(i).getVolume()) {
//                int lot = cart.getCarList().get(i).getVolume() - oldCarList.get(i).getVolume();
//                if (carValidator.falsePlusLotInCart(cart.getCarList().get(i), lot)) {
//                    cart.setCarList(oldCarList);
//                    return true;
//                }
//            } else if (cart.getCarList().get(i).getVolume() < oldCarList.get(i).getVolume()) {
//                int lot = cart.getCarList().get(i).getVolume() - oldCarList.get(i).getVolume();
//                if (oldCarList.get(i).getVolume() < Math.abs(lot)) {
//                    cart.setCarList(oldCarList);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public void updateLotCart(Cart cart, List<Car> oldCarList) {
//        for (int i = 0; i < cart.getCarList().size(); i++) {
//            if (cart.getCarList().get(i).getVolume() != oldCarList.get(i).getVolume()) {
//                int lot = cart.getCarList().get(i).getVolume() - oldCarList.get(i).getVolume();
//                Car car = carService.findById(cart.getCarList().get(i).getId()).orElse(null);
//                if (car != null) {
//                    car.setVolume(car.getVolume() - lot);
//                    carService.save(car);
//                }
//            }
//        }
//    }


}
