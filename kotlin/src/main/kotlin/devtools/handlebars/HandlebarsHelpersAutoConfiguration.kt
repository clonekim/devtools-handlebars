package devtools.handlebars

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationUtils.findAnnotation


@Configuration
@ConditionalOnBean(HandlebarsViewResolver::class)
class HandlebarsHelpersAutoConfiguration {


    @Bean
    fun handlebarsBeanPostProcessor(handlebarsViewResolver: HandlebarsViewResolver): BeanPostProcessor? {
        return object : BeanPostProcessor {

            @Throws(BeansException::class)
            override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
                return bean
            }

            @Throws(BeansException::class)
            override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
                val annotation: HandlebarsHelper? = findAnnotation(bean.javaClass, HandlebarsHelper::class.java)

                annotation?.let {
                    handlebarsViewResolver.registerHelpers(bean)
                }
                return bean
            }
        }
    }
}