package devtools

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

@Controller
class Controller {

	@GetMapping("/hello")
	fun hello(): ModelAndView {

		return ModelAndView()
				.apply {
					addObject("username", "spring")
					addObject("foo", listOf(1, 2, 3, 4, 5))
				}

	}
}