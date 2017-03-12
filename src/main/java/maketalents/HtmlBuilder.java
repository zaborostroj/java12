package maketalents;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * HtmlBuilder reads the template html, inserts values from data.properties
 * and generates output.html
 */
class HtmlBuilder implements HtmlBuilderIntf {
    private String templateHtmlPath;
    private String outputHtmlPath;
    private String templateHtml;

    HtmlBuilder(String path) {
        templateHtml = "";
        outputHtmlPath = "output.html";

        templateHtmlPath = path;

        readTemplateFile();
    }

    private void readTemplateFile() {
        try {
            InputStream is = getClass().getResourceAsStream(templateHtmlPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                templateHtml += line + "\n";
            }
        } catch (Exception ex) {
            System.out.println("Error reading template HTML");
            ex.printStackTrace();
        }
    }

    @Override
    public void makeHtml(Properties properties) {
        templateHtml = templateHtml.replace("{name}", properties.getProperty("name"));
        templateHtml = templateHtml.replace("{birth_date}", properties.getProperty("birth_date"));
        templateHtml = templateHtml.replace("{phone_number}", properties.getProperty("phone_number"));
        templateHtml = templateHtml.replace("{email}", properties.getProperty("email"));
        templateHtml = templateHtml.replace("{skype}", properties.getProperty("skype"));

        templateHtml = templateHtml.replace("{photo}", properties.getProperty("photo"));

        templateHtml = templateHtml.replace("{goal}", propertyToHtml(properties, "goal"));
        templateHtml = templateHtml.replace("{experience}", propertyToHtml(properties, "experience"));
        templateHtml = templateHtml.replace("{education}", propertyToHtml(properties, "education"));
        templateHtml = templateHtml.replace("{add_education}", propertyToHtml(properties, "add_education"));
        templateHtml = templateHtml.replace("{other_info}", propertyToHtml(properties, "other_info"));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputHtmlPath))) {
            writer.write(templateHtml);
        } catch (IOException ex) {
            System.out.println("Error writing output.html");
            ex.printStackTrace();
        }
    }

    private String propertyToHtml(Properties properties, String prop) {
        String[] text = properties.getProperty(prop).split(";");

        StringBuilder strBuilder = new StringBuilder();
        if (text.length == 1) {
            strBuilder.append(text[0]);
        } else if (text.length > 1) {
            strBuilder.append("<ol>");
            for (String str : text) {
                strBuilder.append("<li>").append(str).append("</li>");
            }
            strBuilder.append("</ol>");
        }

        return strBuilder.toString();
    }
}
