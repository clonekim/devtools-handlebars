package devtools

import com.github.jknack.handlebars.EscapingStrategy
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import devtools.handlebars.CustomHelper
import devtools.handlebars.HandlebarsViewResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration: WebMvcConfigurer {

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.viewResolver(viewResolver())
    }

    @Bean
    fun handlebars( ): Handlebars {
        return Handlebars(ClassPathTemplateLoader("/templates", ".hbs")).apply {
            with(EscapingStrategy.NOOP)
            with(EscapingStrategy.XML)
            CustomHelper.register(this)
        }
    }

    @Bean
    fun viewResolver(): HandlebarsViewResolver {
        return HandlebarsViewResolver()
                .apply {
                    handlebars = handlebars()
                    order = 1
                   isCache= false
                }
    }
}