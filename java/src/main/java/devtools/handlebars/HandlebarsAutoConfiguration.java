package devtools.handlebars;

import com.github.jknack.handlebars.EscapingStrategy;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.URLTemplateLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ResourceUtils;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.handlebars.enabled", matchIfMissing = true)
@EnableConfigurationProperties(HandlebarsProperties.class)
public class HandlebarsAutoConfiguration {

    private final HandlebarsProperties properties;

    public HandlebarsAutoConfiguration(HandlebarsProperties properties) {
        this.properties = properties;
    }


    @Bean
    @ConditionalOnMissingBean
    Handlebars handlebars() {
        Handlebars handlebars = new Handlebars(findLoder(properties.prefix, properties.suffix ))
                                .with(EscapingStrategy.NOOP)
                                .with(EscapingStrategy.XML);

        if(properties.delimiter != null) {
            String[] delimiters = properties.delimiter.split(",");
            handlebars.setStartDelimiter(delimiters[0]);
            handlebars.setEndDelimiter(delimiters[1]);
        }

        return handlebars;
    }

    @Bean
    @ConditionalOnMissingBean
    HandlebarsViewResolver handlebarsViewResolver() {
        HandlebarsViewResolver resolver = new HandlebarsViewResolver(handlebars());
        resolver.setCache(properties.cache);
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return resolver;
    }


    URLTemplateLoader findLoder(String prefix, String suffix) {
        if(prefix.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
            return new ClassPathTemplateLoader(prefix.substring(ResourceUtils.CLASSPATH_URL_PREFIX.length()), suffix);
        }
        else if(prefix.startsWith(ResourceUtils.FILE_URL_PREFIX)) {
            return new FileTemplateLoader(prefix.substring(ResourceUtils.FILE_URL_PREFIX.length()), suffix);
        }

        throw new IllegalArgumentException();

    }
}
