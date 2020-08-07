package devtools.handlebars;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.ValueResolver;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HandlebarsView extends AbstractTemplateView {

    protected Template template;


    @Override
    protected void renderMergedTemplateModel(Map<String, Object> map, HttpServletRequest req, HttpServletResponse res) throws Exception {

        final Context context = Context.newBuilder(map)
                .resolver(ValueResolver.VALUE_RESOLVERS)
                .build();

        try {
            template.apply(context, res.getWriter());
        }finally {
            context.destroy();
        }

    }
}
