package devtools.handlebars

import com.github.jknack.handlebars.EscapingStrategy
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.util.ResourceUtils

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = ["spring.handlebars.enabled"], matchIfMissing = true)
@EnableConfigurationProperties(HandlebarsProperties::class)
class HandlebarsAutoConfiguration(private val properties: HandlebarsProperties) {


    @Bean
    @ConditionalOnMissingBean
    fun handlebars(): Handlebars {
        return Handlebars(ClassPathTemplateLoader( resolve(properties.prefix), properties.suffix)).apply {
            with(EscapingStrategy.NOOP)
            with(EscapingStrategy.XML)
        }
    }

    @Bean
    @ConditionalOnMissingBean
    fun handlebarsViewResolver(): HandlebarsViewResolver {

        return HandlebarsViewResolver()
                .apply {
                    handlebars = handlebars()
                    isCache = properties.cache
                    order = Ordered.HIGHEST_PRECEDENCE
                }
    }


    fun resolve(prefix: String) :String? {
        var protocol: String? = null

        if (prefix.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
            protocol = ResourceUtils.CLASSPATH_URL_PREFIX;
        } else if (prefix.startsWith(ResourceUtils.FILE_URL_PREFIX)) {
            protocol = ResourceUtils.FILE_URL_PREFIX;
        }

        protocol?.let {
            return prefix.substring(protocol.length)
        }

        return null
    }


}