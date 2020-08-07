# devtools-handlebars

## application.yml

```yml
spring:
  handlebars:
    prefix: classpath:/templates
    suffix: .hbs
```

## from controller 
```java

 @GetMapping("/hero")
    ModelAndView hero() {
        return new ModelAndView()
                .addObject("heros", Arrays.asList(
                        Hero.builder().name("수퍼면").strength(90).build(),
                        Hero.builder().name("원더우먼").strength(80).build(),
                        Hero.builder().name("스파이더맨").strength(40).build(),
                        Hero.builder().name("토르").strength(85).build()
                ));
    }
```

## VueJs를 사용하기 위해 helper를 작성
```java
@HandlebarsHelper
public class RawHelper {
    public String raw(Object value, Options options) throws IOException {
        return options.fn().toString();
    }
}
```

## from view(hero.hbs)

 ~로 끝나면 소스의 공백을 제거한다
[Whitespace Control](https://handlebarsjs.com/guide/expressions.html#whitespace-control)

```hbs
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.11/vue.min.js"></script>
</head>
<body>
    <h3>영웅들</h3>

    {{#heros~}}
    <li>{{name}}/{{strength}}</li>
    {{/heros~}}

    {{{{raw}}}}
    <div id="app">
        {{message}}
    </div>
    {{{{/raw}}}}

    <script>
        new Vue({
            el: '#app',
            data: {
                message: 'Vue시험중'
            }
        })
    </script>
</body>
</html>
```
