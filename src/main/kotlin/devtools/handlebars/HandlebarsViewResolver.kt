package devtools.handlebars

import com.github.jknack.handlebars.Handlebars
import org.springframework.web.servlet.view.AbstractTemplateViewResolver
import org.springframework.web.servlet.view.AbstractUrlBasedView

class HandlebarsViewResolver: AbstractTemplateViewResolver {

    lateinit var handlebars: Handlebars

    constructor() {
        viewClass = requiredViewClass()
        contentType = "text/html;charset=UTF-8"
    }


    override fun buildView(viewName: String): AbstractUrlBasedView {
        val view = super.buildView(viewName) as HandlebarsView
        view.template = handlebars.compile(viewName)
        return view
    }

    override fun requiredViewClass(): Class<*> {
        return HandlebarsView::class.java
    }

    fun registerHelpers(bean: Any): HandlebarsViewResolver {
        handlebars.registerHelpers(bean)
        return this
    }
}

