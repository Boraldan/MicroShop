package boraldan.front.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestItem implements Serializable {

    private int item;

    public TestItem(){
        item = TestCount.getCount();
    }

}
