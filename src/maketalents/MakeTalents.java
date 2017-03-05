package maketalents;

public class MakeTalents {
    public static void main(String[] args) {
        String propFilePath = "files/data.properties"; // файл с исходными данными
        PropReader propReader = new PropReader(propFilePath);

        String templateHtmlPath = "files/template.html"; // шаблон анкеты
        HtmlBuilder htmlBuilder = new HtmlBuilder(templateHtmlPath);
        htmlBuilder.makeHtml(propReader.getProperties());
    }
}
