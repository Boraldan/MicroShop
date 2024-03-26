package boraldan.order.service;


import boraldan.order.repository.OrderRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;


//////-------  SecurityContextHolder.getContext() в Spring Security предоставляет доступ к текущему объекту аутентификации в приложении.
////// Создание нового объекта аутентификации с обновленными данными (например, обновление ролей)
////            UsernamePasswordAuthenticationToken updatedAuthentication =
////                    new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
////                            authentication.getCredentials(), updatedAuthorities);
////
////// Установка обновленного объекта аутентификации в SecurityContext
////            SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
//
//    public Optional<Orders> findById(int id) {
//        return ordersRepo.findById(id);
//    }
//
//    public List<OrdersCar> findAll() {
//        return ordersCarRepo.findAll();
//    }
//
//
//    public List<OrdersCar> findByOrderId(int id) {
//        return ordersCarRepo.findOrdersCarByOrdersId(id);
//    }
//
//
//    @Transactional
//    public Orders saveOrdersCar(Cart cart) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String role = authentication.getAuthorities().stream().toList().get(0).toString();
//        // --------------------создаем Orders
//        Orders orders = new Orders();
//        if (!role.equals("ROLE_ANONYMOUS")) {
//            Person person = ((PersonDetails) authentication.getPrincipal()).getPerson();
//            orders.setCreatAt(LocalDateTime.now());
//            orders.setStatus(Status.CREATED);
//            orders.setPerson(person);
//            if (cart.getCoupon() != null) orders.setCoupon(cart.getCoupon());
//            orders.setCars(cart.getCarList());
//            orders = ordersRepo.save(orders);
//
//            for (Car car : cart.getCarList()) {
//                // --- помечаем Car как купленные, чтобы не удалять из базы:  boolean purchased
//                Car updateCar = carService.findById(car.getId()).get();
//                updateCar.setPurchased(true);
//                carService.save(updateCar);
//
//                //  ---  записываем lot в таблицу OrdersCar
//                OrdersCar ordersCar = ordersCarRepo.findOrdersCarByOrdersAndCar(orders, car).get();
//                ordersCar.setLot(car.getVolume());
//                ordersCarRepo.save(ordersCar);
//            }
//        }
//        return orders;
//    }
//
//    public List<Orders> setLotCarList(List<Orders> list) {
//        for (Orders orders : list) {
//           setLotCar(orders);
//        }
//        return list;
//    }
//
//    public Orders setLotCar(Orders orders) {
//        int i = 0;
//        for (Car car : orders.getCars()) {
//            car.setVolume(ordersCarRepo.findOrdersCarByOrdersIdAndCarId(orders.getId(), car.getId()).get().getLot());
//            try {
//                Car cloneCar = car.clone();
//                orders.getCars().set(i++, cloneCar);
//                System.out.println(cloneCar);
//            } catch (CloneNotSupportedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return orders;
//    }
//
//    @Transactional
//    public void cancelOrders(int id) {
//        Orders orders = ordersRepo.findById(id).orElse(null);
//        if (orders != null) {
//            Hibernate.initialize(orders.getCars());
//            orders.setStatus(Status.CANCELLED);
//            ordersRepo.save(orders);
//            for (Car car : orders.getCars()) {
//                car.setVolume(car.getVolume() + ordersCarRepo.findOrdersCarByOrdersIdAndCarId(orders.getId(), car.getId()).get().getLot());
//                carService.save(car);
//            }
//        }
//    }

}