package devtools.handlebars

import com.github.jknack.handlebars.Context
import com.github.jknack.handlebars.Template
import com.github.jknack.handlebars.ValueResolver
import org.springframework.web.servlet.view.AbstractTemplateView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class HandlebarsView: AbstractTemplateView() {

    lateinit var template: Template

    val valueResolvers: Array<ValueResolver> = ValueResolver.VALUE_RESOLVERS

    override fun renderMergedTemplateModel(map: MutableMap<String, Any>, req: HttpServletRequest, resp: HttpServletResponse) {

        val context = Context.newBuilder(map)
                .resolver(*valueResolvers)
                .build()
        try {
            template.apply(context, resp.writer)
        } finally {
            context.destroy()
        }
    }
}