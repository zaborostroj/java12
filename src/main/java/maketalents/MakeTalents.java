package maketalents;

public class MakeTalents {
    public static void main(String[] args) {
        String propFilePath = "/data.properties"; // use absolute path to .properties from .jar root
        PropReader propReader = new PropReader(propFilePath);

        String templateHtmlPath = "/template.html"; // use absolute path to template.html from .jar root
        HtmlBuilder htmlBuilder = new HtmlBuilder(templateHtmlPath);
        htmlBuilder.makeHtml(propReader.getProperties());
    }
}
