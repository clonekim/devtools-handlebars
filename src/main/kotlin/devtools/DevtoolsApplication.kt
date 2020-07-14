package devtools

import com.github.jknack.handlebars.Options
import devtools.handlebars.HandlebarsHelper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@SpringBootApplication
class DevtoolsApplication

fun main(args: Array<String>) {
    runApplication<DevtoolsApplication>(*args)
}

@HandlebarsHelper
class RawHelper {
    fun raw(value: Any?, options: Options): String {
        return options.fn().toString()
    }
}


class Hero(val name: String, val strength: Int)

@Controller
class Controller {

    @GetMapping("/")
    fun index(): ModelAndView {

        return ModelAndView()
                .apply {
                    addObject("username", "spring")
                    addObject("foo", listOf(1, 2, 3, 4, 5))
                }

    }


    @GetMapping("/hero")
    fun hello(): ModelAndView {
        return ModelAndView()
                .apply {
                    addObject("heros", listOf(
                            Hero("수퍼맨", 90),
                            Hero("원더우먼", 80),
                            Hero("스파이더맨", 40),
                            Hero("토르", 80)
                    ))
                }
    }
}