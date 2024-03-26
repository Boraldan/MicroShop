package boraldan.front.controller;


import boraldan.entitymicro.test.Lot;
import boraldan.front.service.IServiceApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class StartController {

    private final IServiceApi IServiceApi;

    @GetMapping
    public String start(Model model) {
        Lot lot = new Lot();
        try {
            lot = IServiceApi.getLot();
            model.addAttribute("lot", lot);
        } catch (Exception e) {
            model.addAttribute("lot", lot);
            System.out.println(e.getMessage());
        }
        return "index";
    }




}