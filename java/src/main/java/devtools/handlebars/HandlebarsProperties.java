package devtools.handlebars;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.handlebars")
public class HandlebarsProperties {

    String prefix;
    String suffix;
    boolean cache;
    String delimiter = "{{,}}";

}
