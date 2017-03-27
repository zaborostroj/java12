package maketalents;

import maketalents.datamodel.UserData;

public interface HtmlBuilderIntf {
    String templateHtmlPath = "/template.html";
    void makeHtml(UserData userData);
}
