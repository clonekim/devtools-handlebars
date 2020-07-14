package devtools.handlebars

import org.springframework.stereotype.Component
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
@Component
annotation class HandlebarsHelper