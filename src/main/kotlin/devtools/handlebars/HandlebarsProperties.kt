package devtools.handlebars
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.handlebars")
data class HandlebarsProperties (
        val prefix: String = "",
        val suffix: String = "",
        var cache: Boolean = false)
