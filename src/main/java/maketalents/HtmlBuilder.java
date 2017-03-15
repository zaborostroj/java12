package maketalents;

import maketalents.datamodel.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * HtmlBuilder reads the template html, inserts values from data.properties
 * and generates output.html
 */
class HtmlBuilder implements HtmlBuilderIntf {
    private String templateHtmlPath;
    private String outputHtmlPath;
    private String templateHtml;

    /**
     * @param path - path to template.html (absolute from .jar root)
     */
    HtmlBuilder(String path) {
        templateHtml = "";
        outputHtmlPath = "output.html";

        templateHtmlPath = path;

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

        templateHtml = templateHtml.replace("{goal}", propertyToHtml(userData.getGoal()));
        templateHtml = templateHtml.replace("{experience}", propertyToHtml(userData.getExperience()));
        templateHtml = templateHtml.replace("{education}", propertyToHtml(userData.getEducation()));
        templateHtml = templateHtml.replace("{add_education}", propertyToHtml(userData.getAddEducation()));
        templateHtml = templateHtml.replace("{other_info}", propertyToHtml(userData.getOtherInfo()));

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputHtmlPath))) {
            writer.write(templateHtml);
        } catch (IOException ex) {
            System.out.println("Error writing output.html");
            ex.printStackTrace();
        }
    }

    /**
     * Generates <ol/> tag from property
     *
     * @param prop - multivalue property
     * @return String - ordered list tag build from multivalue property
     */
    private String propertyToHtml(String prop) {
        String[] text = prop.split(";");

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
