package maketalents;

import maketalents.datamodel.UserData;

public interface HtmlBuilder {
    String templateHtmlPath = "/template.html";
    void makeHtml(UserData userData);
}
