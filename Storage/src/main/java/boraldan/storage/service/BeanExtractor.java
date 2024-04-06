package boraldan.storage.service;

//// попробовать реализовать рефлексию по созданию бинов репозитория
//@Component
//public class BeanExtractor {
//
//    @Autowired
//    private StorageServiceConfig storageServiceConfig;
//
//    public Map<Class<?>, StorageService<?, GlobalJpaRepository<?>>> extractStorageServicesFromClass(Class<?> clazz) {
//        Map<Class<?>, StorageService<?, GlobalJpaRepository<?>>> beanMap = new HashMap<>();
//        try {
//            // Получаем экземпляр класса по его имени
////            Object instance = applicationContext.getBean(clazz);
//            Object instance = storageServiceConfig.getClass();
//
//            // Получаем все методы класса
//            Method[] methods = clazz.getDeclaredMethods();
//            for (Method method : methods) {
//                // Проверяем, является ли метод бином
//                if (method.isAnnotationPresent(org.springframework.context.annotation.Bean.class)) {
//                    // Получаем возвращаемый тип бина
//                    Class<?> returnType = method.getReturnType();
//                    System.out.println("1  --->  " + returnType);
//                    // Получаем бин
//
//                    Object bean = method.invoke(instance);
//                    System.out.println("2  --->  " + bean);
//                    // Добавляем бин в карту, приводя его к StorageService
//                    beanMap.put(returnType, (StorageService<?, GlobalJpaRepository<?>>) bean);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return beanMap;
//    }
//
//    @Component
//    public class Test_1 {
//
//        BeanExtractor beanExtractor = new BeanExtractor();
//
//        public void getMapBean() {
//            Map<Class<?>, StorageService<?, GlobalJpaRepository<?>>> beanMap = beanExtractor.extractStorageServicesFromClass(StorageServiceConfig.class);
//            for (Map.Entry<Class<?>, StorageService<?, GlobalJpaRepository<?>>> entry : beanMap.entrySet()) {
//                System.out.println("Class: " + entry.getKey() + ", Bean: " + entry.getValue());
//            }
//        }
//
//
//    }
//}
//
