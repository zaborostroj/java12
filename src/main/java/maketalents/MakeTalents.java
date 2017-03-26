package maketalents;

import maketalents.dao.DataPropReader;
import maketalents.datamodel.UserData;

public class MakeTalents {
    public static void main(String[] args) {
        UserData userDataReadFromFile = new UserData();

        String propFilePath1 = "/data1.properties"; // use absolute path to .properties from .jar root
        DataPropReader dataPropReader1 = new DataPropReader(propFilePath1, userDataReadFromFile);

        String propFilePath2 = "/data2.properties"; // use absolute path to .properties from .jar root
        DataPropReader dataPropReader2 = new DataPropReader(propFilePath2, userDataReadFromFile);

        Thread thread1 = new Thread(dataPropReader1);
        Thread thread2 = new Thread(dataPropReader2);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String templateHtmlPath = "/template.html"; // use absolute path to template.html from .jar root
        HtmlBuilder htmlBuilder = new HtmlBuilder(templateHtmlPath);
        htmlBuilder.makeHtml(userDataReadFromFile);
    }
}
