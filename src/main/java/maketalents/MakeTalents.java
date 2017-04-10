package maketalents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MakeTalents {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MakeTalents.class, args);
        HtmlBuilderImpl htmlBuilder = context.getBean("htmlBuilderImpl", HtmlBuilderImpl.class);
        htmlBuilder.makeHtml();
    }
}
