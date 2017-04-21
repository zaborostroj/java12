package maketalents;

import maketalents.datamodel.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * HtmlBuilderImpl reads the template html, inserts values from data1.properties
 * and generates output.html
 */
class HtmlBuilderImpl implements HtmlBuilder {
    private String templateHtmlPath;
    private String outputHtmlPath;
    private String templateHtml;

    /**
     * @param path - path to template.html (absolute from .jar root)
     */
    HtmlBuilderImpl(String path) {
        templateHtml = "";
        outputHtmlPath = "output.html";

        templateHtmlPath = path;

        readTemplateFile();
    }

    /**
     * Reads template.html
     */
    private void readTemplateFile() {
        try(InputStream is = getClass().getResourceAsStream(templateHtmlPath)) {
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
        String propertySeparator = userData.getPropertySeparator();
        templateHtml = templateHtml.replace("{name}", propertyToHtml(userData.getName(), propertySeparator));
        templateHtml = templateHtml.replace("{birth_date}", propertyToHtml(userData.getBirthDay(), propertySeparator));
        templateHtml = templateHtml.replace("{phone_number}", propertyToHtml(userData.getPhoneNumber(), propertySeparator));
        templateHtml = templateHtml.replace("{email}", propertyToHtml(userData.getEmail(), propertySeparator));
        templateHtml = templateHtml.replace("{skype}", propertyToHtml(userData.getSkype(), propertySeparator));
        templateHtml = templateHtml.replace("{photo}", propertyToHtml(userData.getPhoto(), propertySeparator));

        templateHtml = templateHtml.replace("{goal}", propertyToHtml(userData.getGoal(), propertySeparator));
        templateHtml = templateHtml.replace("{experience}", propertyToHtml(userData.getExperience(), propertySeparator));
        templateHtml = templateHtml.replace("{education}", propertyToHtml(userData.getEducation(), propertySeparator));
        templateHtml = templateHtml.replace("{add_education}", propertyToHtml(userData.getAddEducation(), propertySeparator));
        templateHtml = templateHtml.replace("{other_info}", propertyToHtml(userData.getOtherInfo(), propertySeparator));

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
        if (prop == null) { // default value for empty properties
            return "N/A";
        } else if (propertySeparator == null) { // don't split props without separator
            return prop;
        } else {
            String[] text = prop.split(propertySeparator);
            StringBuilder strBuilder = new StringBuilder();

            if (text.length == 1) { // if multikey prop has only one value
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
}
