package maketalents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * HtmlBuilder reads the template html, inserts values from data.properties
 * and generates output.html
 */
class HtmlBuilder {
    private String templateHtmlPath;
    private String outputHtmlPath;
    private String templateHtml;

    HtmlBuilder(String path) {
        templateHtml = "";
        outputHtmlPath = "files/output.html";

        templateHtmlPath = path;
        File f = new File(templateHtmlPath);
        if (f.exists() && !f.isDirectory()) {
            readTemplateFile();
        } else {
            System.out.println("Template HTML not found");
        }
    }

    private void readTemplateFile() {
        try (Stream<String> stream = Files.lines(Paths.get(templateHtmlPath))) {
            stream.forEach(str-> templateHtml += str + "\n");
        } catch (IOException ex) {
            System.out.println("Error reading template.html");
            ex.printStackTrace();
        }
    }

    void makeHtml(Properties properties) {
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
