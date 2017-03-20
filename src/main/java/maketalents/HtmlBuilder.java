package maketalents;

import maketalents.datamodel.UserData;
import maketalents.datamodel.UserPropertyKeys;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

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
     * Same as void makeHtml(UserData)
     *
     * @param userData - hashMap read from .properties file
     */
    public void makeHtmlFromHashMap(Map<String, String> userData) {
        // разбираем список множественных свойств
        ArrayList<String> multykeyProperties = new ArrayList<>();
        if (userData.containsKey(UserPropertyKeys.MULTYKEY_PROPERTIES) && userData.containsKey(UserPropertyKeys.PROPERTY_SEPARATOR)) {

            String multykeyProps = userData.get(UserPropertyKeys.MULTYKEY_PROPERTIES);
            for (String str : multykeyProps.split(userData.get(UserPropertyKeys.PROPERTY_SEPARATOR))) {
                multykeyProperties.add(str);
            }
        }

        // эти поля не могут иметь несколько значений
        templateHtml = templateHtml.replace("{name}", userData.get(UserPropertyKeys.NAME));
        templateHtml = templateHtml.replace("{birth_date}", userData.get(UserPropertyKeys.BIRTHDAY));
        templateHtml = templateHtml.replace("{phone_number}", userData.get(UserPropertyKeys.PHONE_NUMBER));
        templateHtml = templateHtml.replace("{email}", userData.get(UserPropertyKeys.EMAIL));
        templateHtml = templateHtml.replace("{skype}", userData.get(UserPropertyKeys.SKYPE));
        templateHtml = templateHtml.replace("{photo}", userData.get(UserPropertyKeys.PHOTO));

        // индусский код
        if (multykeyProperties.contains(UserPropertyKeys.GOAL)) {
            templateHtml = templateHtml.replace(
                    "{goal}",
                    propertyToHtml(userData.get(UserPropertyKeys.GOAL), userData.get(UserPropertyKeys.PROPERTY_SEPARATOR)));
        } else {
            templateHtml = templateHtml.replace("{goal}", userData.get(UserPropertyKeys.GOAL));
        }

        if (multykeyProperties.contains(UserPropertyKeys.EXPERIENCE)) {
            templateHtml = templateHtml.replace(
                    "{experience}",
                    propertyToHtml(userData.get(UserPropertyKeys.EXPERIENCE), userData.get(UserPropertyKeys.PROPERTY_SEPARATOR)));
        } else {
            templateHtml = templateHtml.replace("{experience}", userData.get(UserPropertyKeys.EXPERIENCE));
        }

        if (multykeyProperties.contains(UserPropertyKeys.EDUCATION)) {
            templateHtml = templateHtml.replace(
                    "{education}",
                    propertyToHtml(userData.get(UserPropertyKeys.EDUCATION), userData.get(UserPropertyKeys.PROPERTY_SEPARATOR)));
        } else {
            templateHtml = templateHtml.replace("{education}", userData.get(UserPropertyKeys.EDUCATION));
        }

        if (multykeyProperties.contains(UserPropertyKeys.ADD_EDUCATION)) {
            templateHtml = templateHtml.replace(
                    "{add_education}",
                    propertyToHtml(userData.get(UserPropertyKeys.ADD_EDUCATION), userData.get(UserPropertyKeys.PROPERTY_SEPARATOR)));
        } else {
            templateHtml = templateHtml.replace("{add_education}", userData.get(UserPropertyKeys.ADD_EDUCATION));
        }

        if (multykeyProperties.contains(UserPropertyKeys.OTHER_INFO)) {
            templateHtml = templateHtml.replace(
                    "{other_info}",
                    propertyToHtml(userData.get(UserPropertyKeys.OTHER_INFO), userData.get(UserPropertyKeys.PROPERTY_SEPARATOR)));
        } else {
            templateHtml = templateHtml.replace("{other_info}", userData.get(UserPropertyKeys.OTHER_INFO));
        }

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

    /**
     * Generates <ol/> tag from property
     *
     * @param prop - multivalue property
     * @param separator - values separator
     * @return String - ordered list tag build from multivalue property
     */
    private String propertyToHtml(String prop, String separator) {
        String[] text = prop.split(separator);

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
