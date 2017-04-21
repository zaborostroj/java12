package maketalents;

import maketalents.dao.DataPropReaderImpl;
import maketalents.datamodel.UserData;

public class MakeTalents {
    public static void main(String[] args) {
        UserData userDataReadFromFile = new UserData();

        String propFilePath1 = "/data1.properties"; // use absolute path to .properties from .jar root
        DataPropReaderImpl dataPropReaderImpl1 = new DataPropReaderImpl(propFilePath1, userDataReadFromFile);

        String propFilePath2 = "/data2.properties"; // use absolute path to .properties from .jar root
        DataPropReaderImpl dataPropReaderImpl2 = new DataPropReaderImpl(propFilePath2, userDataReadFromFile);

        Thread thread1 = new Thread(dataPropReaderImpl1);
        Thread thread2 = new Thread(dataPropReaderImpl2);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String templateHtmlPath = "/template.html"; // use absolute path to template.html from .jar root
        HtmlBuilderImpl htmlBuilder = new HtmlBuilderImpl(templateHtmlPath);
        htmlBuilder.makeHtml(userDataReadFromFile);
    }
}
