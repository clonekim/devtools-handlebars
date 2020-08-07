package devtools.handlebars;

import com.github.jknack.handlebars.Handlebars;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class HandlebarsViewResolver extends AbstractTemplateViewResolver {

    final Handlebars handlebars;

    public HandlebarsViewResolver(Handlebars handlebars) {
        this.handlebars = handlebars;
        setContentType("text/html;charset=UTF-8");
        setViewClass(requiredViewClass());
    }


    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {

        if( viewName == null || viewName.trim().isEmpty()) {
            viewName = "index";
        }

        HandlebarsView view = (HandlebarsView) super.buildView(viewName);
        view.template = handlebars.compile(viewName);
        return view;
    }


    @Override
    protected Class<?> requiredViewClass() {
        return HandlebarsView.class;
    }

    public HandlebarsViewResolver registerHelpers(final Object helper) {
        handlebars.registerHelpers(helper);
        return this;
    }

}
