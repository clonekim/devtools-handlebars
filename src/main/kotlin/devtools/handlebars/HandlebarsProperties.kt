package devtools.handlebars

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.handlebars")
class HandlebarsProperties (val prefix: String, val suffix: String)
