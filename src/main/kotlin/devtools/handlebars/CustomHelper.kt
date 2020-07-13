package devtools.handlebars


import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options
import java.io.IOException


enum class CustomHelper : Helper<Any> {
    raw {
        override fun toApply(value: Any, options: Options): String {
            return options.fn().toString()
        }
    },

    firstCap {
        override fun toApply(value: Any, options: Options): String {
            val v = value.toString()
            return v.substring(0, 1).toUpperCase() + v.substring(1)
        }
    },

    sharp {
        override fun toApply(value: Any, options: Options): String {
            return String.format("#{%s}", value.toString())
        }
    },

    snakebar {
        override fun toApply(value: Any, options: Options): String {
            val regex = "([a-z])([A-Z]+)"
            val replacement = "$1-$2"
            return value.toString()
                    .replace(regex.toRegex(), replacement)
                    .toLowerCase()
        }
    };


    protected  abstract fun toApply(value: Any, options: Options): Any

    @Throws(IOException::class)
    override fun apply(value: Any?, options: Options): Any? {
        return if(options.isFalsy(value)) {
            val param = options.param(0, null as Any?)
            param?.toString()
        } else {
            value?.let { this.toApply(it, options) }
        }
    }


    companion object {

        fun register(handlebars: Handlebars) {
            val values = values()
            for (h in values) {
                handlebars.registerHelper(h.name, h)
            }
        }
    }
}