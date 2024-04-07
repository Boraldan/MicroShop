package boraldan.shop._test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class TestGson {

//    @JsonManagedReference
    private String name = "Yes Json";

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    private String name2 = "No Json";

}
