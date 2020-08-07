package devtools;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@RestController
public class Controller {

    @GetMapping("/")
    ModelAndView index() {
        return new ModelAndView();
    }

    @GetMapping("/hero")
    ModelAndView hero() {
        return new ModelAndView()
                .addObject("heros", Arrays.asList(
                        Hero.builder().name("수퍼면").strength(90).build(),
                        Hero.builder().name("원더우먼").strength(80).build(),
                        Hero.builder().name("스파이더맨").strength(40).build(),
                        Hero.builder().name("토르").strength(85).build()
                ));
    }
}