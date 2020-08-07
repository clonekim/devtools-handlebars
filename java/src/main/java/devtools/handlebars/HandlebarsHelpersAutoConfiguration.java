package devtools.handlebars;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;


@Configuration
@ConditionalOnBean(HandlebarsViewResolver.class)
public class HandlebarsHelpersAutoConfiguration {


    @Bean
    public BeanPostProcessor handlebarBeanPostProcessor(final HandlebarsViewResolver viewResolver) {

        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                HandlebarsHelper annotation =  findAnnotation(bean.getClass(), HandlebarsHelper.class);
                if (annotation != null) {
                    viewResolver.registerHelpers(bean);
                }
                return bean;
            }
        };
    }
}
