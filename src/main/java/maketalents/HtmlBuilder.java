package maketalents;

import maketalents.datamodel.UserData;

public interface HtmlBuilder {
    String templateHtmlPath = "/template.html";
    String outputHtmlPath = "output.html";

    void makeHtml();
}
