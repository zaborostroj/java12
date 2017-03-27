package maketalents;

import maketalents.datamodel.UserData;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * HtmlBuilder reads the template html, inserts values from data1.properties
 * and generates output.html
 */
@Component
class HtmlBuilder implements HtmlBuilderIntf {
    private String outputHtmlPath;
    private String templateHtml;

    HtmlBuilder() {
        templateHtml = "";
        outputHtmlPath = "output.html";

        readTemplateFile();
    }

    /**
     * Reads template.html
     */
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

    /**
     * Replace tags {} in templateHtml with corresponding userData fields
     *
     * @param userData - user data read from .properties file
     */
    @Override
    public void makeHtml(UserData userData) {
        templateHtml = templateHtml.replace("{name}", userData.getName());
        templateHtml = templateHtml.replace("{birth_date}", userData.getBirthDay());
        templateHtml = templateHtml.replace("{phone_number}", userData.getPhoneNumber());
        templateHtml = templateHtml.replace("{email}", userData.getEmail());
        templateHtml = templateHtml.replace("{skype}", userData.getSkype());
        templateHtml = templateHtml.replace("{photo}", userData.getPhoto());

        templateHtml = templateHtml.replace("{goal}", propertyToHtml(userData.getGoal(), userData.getPropertySeparator()));
        templateHtml = templateHtml.replace("{experience}", propertyToHtml(userData.getExperience(), userData.getPropertySeparator()));
        templateHtml = templateHtml.replace("{education}", propertyToHtml(userData.getEducation(), userData.getPropertySeparator()));
        templateHtml = templateHtml.replace("{add_education}", propertyToHtml(userData.getAddEducation(), userData.getPropertySeparator()));
        templateHtml = templateHtml.replace("{other_info}", propertyToHtml(userData.getOtherInfo(), userData.getPropertySeparator()));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputHtmlPath))) {
            writer.write(templateHtml);
        } catch (IOException ex) {
            System.out.println("Error writing output.html");
            ex.printStackTrace();
        }
    }

    /**
     * Generates <ol/> tag from multivalue property
     *
     * @param prop - multivalue property
     * @return String - ordered list tag build from multivalue property
     */
    private String propertyToHtml(String prop, String propertySeparator) {
        String[] text = prop.split(propertySeparator);

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
