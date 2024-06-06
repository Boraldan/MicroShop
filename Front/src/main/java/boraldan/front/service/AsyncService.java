package boraldan.front.service;

//public class AsyncService {

//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private final RedisService redisService;
//    private final Map<String, Thread> taskThreads = new ConcurrentHashMap<>();
//    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
//
//    @Async
//    public void reserveItemWithDelayCancel(CartDto cartDto) {
//        // Логика, выполняемая вначале
//        String reserveKey = String.format("%s_reserve", cartDto.getCustomer().getUsername().toLowerCase());
//
//
//        // Запуск логики через 2 минуты
//        scheduler.schedule(() -> {
//            System.out.println("Логика, выполняемая через 2 минуты...");
//            if (redisService.getCart(reserveKey) != null) {
//            }
//        }, 2, TimeUnit.MINUTES);
//    }
//// -------------------------------------------
//
//    @Async
//    public void executeTaskWithInterrupt(String taskId) {
//        Thread currentThread = Thread.currentThread();
//        taskThreads.put(taskId, currentThread);
//        try {
//            // Начальная логика
//            System.out.println("Начальная логика выполняется...");
//
////            // Долгий процесс
//            for (int i = 0; i < 120; i++) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Процесс " + taskId + " был прерван.");
//                    return;
//                }
//                Thread.sleep(1000); // Симуляция работы
//            }
//
//        } catch (InterruptedException e) {
//            System.out.println("Процесс " + taskId + " был прерван (InterruptedException).");
//            Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
//        } finally {
//            taskThreads.remove(taskId);
//        }
//    }
//
//    public void interruptTask(String taskId) {
//        Thread thread = taskThreads.get(taskId);
//        if (thread != null) {
//            thread.interrupt();
//        }
//    }
//    // ----------------------------------------

//    @Async
//    public void executeTaskWithInterrupt2(String taskId) {
//        // Логика, выполняемая при старте
//        System.out.println("Логика, выполняемая при старте задачи " + taskId);
//        int delay = 10;
//
//        ScheduledFuture<?> scheduledFuture = scheduler.schedule(() -> {
//            try {
//                // Логика, выполняемая через 2 минуты
//                System.out.printf("Логика, выполняемая через %d задачи %s%n", delay, taskId);
//            } finally {
//                scheduledTasks.remove(taskId);  // Удаляет задачу из карты после завершения
//                System.out.println("Задача " + taskId + " завершена и удалена из списка.");
//            }
//        }, delay, TimeUnit.SECONDS);
//
//        scheduledTasks.put(taskId, scheduledFuture);
//    }
//
//    public void interruptTask2(String taskId) {
//        ScheduledFuture<?> scheduledFuture = scheduledTasks.get(taskId);
//        if (scheduledFuture != null) {
//            scheduledFuture.cancel(true);  // Отменяет задачу
//            scheduledTasks.remove(taskId);  // Удаляет задачу из карты
//            System.out.println("Задача " + taskId + " была прервана.");
//        } else {
//            System.out.println("Такой задачи не существует.");
//        }
//    }

//}