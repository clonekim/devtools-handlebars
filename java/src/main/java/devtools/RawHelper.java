package devtools;

import com.github.jknack.handlebars.Options;
import devtools.handlebars.HandlebarsHelper;

import java.io.IOException;

@HandlebarsHelper
public class RawHelper {
    public String raw(Object value, Options options) throws IOException {
        return options.fn().toString();
    }
}
