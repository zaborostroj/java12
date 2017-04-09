package maketalents;

import maketalents.dao.DataPropReaderImpl;
import maketalents.datamodel.UserData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MakeTalents {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MakeTalents.class, args);

        UserData userData = context.getBean("userData", UserData.class);

        DataPropReaderImpl dataPropReader1 = context.getBean("dataPropReader", DataPropReaderImpl.class);
        dataPropReader1.fillUserDataObject(userData);

        HtmlBuilderImpl htmlBuilder = context.getBean("htmlBuilder", HtmlBuilderImpl.class);
        htmlBuilder.makeHtml(userData);
    }
}
