package maketalents;

import maketalents.datamodel.UserData;

import java.io.IOException;

public interface HtmlBuilder {
    String templateHtmlPath = "/template.html";
    String outputHtmlPath = "output.html";

    void makeHtml() throws IOException;
}
