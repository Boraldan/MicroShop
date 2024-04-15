package boraldan.account.dto;

public class Counter {
    private static int num = 0;

    public static int getNum() {
        return ++num;
    }
}
