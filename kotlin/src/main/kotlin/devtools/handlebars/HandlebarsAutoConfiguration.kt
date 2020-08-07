package devtools.handlebars

import com.github.jknack.handlebars.EscapingStrategy
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.FileTemplateLoader
import com.github.jknack.handlebars.io.URLTemplateLoader
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
        return Handlebars(findLoder(properties.prefix, properties.suffix )).apply {
            with(EscapingStrategy.NOOP)
            with(EscapingStrategy.XML)

            properties.delimiter?.split(",").let {
                if(it?.size == 2) {
                    startDelimiter = it[0]
                    endDelimiter = it[1]
                }
            }

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

    fun findLoder(prefix: String, suffix: String): URLTemplateLoader {
        return when {
            prefix.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX) ->
                ClassPathTemplateLoader(prefix.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length), suffix)
            prefix.startsWith(ResourceUtils.FILE_URL_PREFIX) ->
                FileTemplateLoader(prefix.substring(ResourceUtils.FILE_URL_PREFIX.length), suffix)
            else -> throw IllegalArgumentException()
        }

    }


}