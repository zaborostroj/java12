package maketalents;

import maketalents.datamodel.UserData;
import maketalents.service.DataPropReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * HtmlBuilderImpl reads the template html, inserts values from data1.properties
 * and generates output.html
 */
@Component
class HtmlBuilderImpl implements HtmlBuilder {
    private String outputHtml;

    @Autowired
    private DataPropReaderService dataPropReaderService;

    HtmlBuilderImpl() {
        outputHtml = "";
    }

    /**
     * Replace tags {} in outputHtml with corresponding userData fields
     */
    @Override
    public void makeHtml() {
        UserData userData = dataPropReaderService.getUserData();
        readTemplateFile();

        outputHtml = outputHtml.replace("{name}", userData.getName());
        outputHtml = outputHtml.replace("{birth_date}", userData.getBirthDay());
        outputHtml = outputHtml.replace("{phone_number}", userData.getPhoneNumber());
        outputHtml = outputHtml.replace("{email}", userData.getEmail());
        outputHtml = outputHtml.replace("{skype}", userData.getSkype());
        outputHtml = outputHtml.replace("{photo}", userData.getPhoto());

        if (userData.getMultykeyProperties().contains("goal")) {
            outputHtml = outputHtml.replace("{goal}", propertyToHtml(userData.getGoal(), userData.getPropertySeparator()));
        } else {
            outputHtml = outputHtml.replace("{goal}", userData.getGoal());
        }

        if (userData.getMultykeyProperties().contains("experience")) {
            outputHtml = outputHtml.replace("{experience}", propertyToHtml(userData.getExperience(), userData.getPropertySeparator()));
        } else {
            outputHtml = outputHtml.replace("{experience}", userData.getExperience());
        }

        if (userData.getMultykeyProperties().contains("education")) {
            outputHtml = outputHtml.replace("{education}", propertyToHtml(userData.getEducation(), userData.getPropertySeparator()));
        } else {
            outputHtml = outputHtml.replace("{education}", userData.getEducation());
        }

        if (userData.getMultykeyProperties().contains("add_education")) {
            outputHtml = outputHtml.replace("{add_education}", propertyToHtml(userData.getAddEducation(), userData.getPropertySeparator()));
        } else {
            outputHtml = outputHtml.replace("{add_education}", userData.getAddEducation());
        }

        if (userData.getMultykeyProperties().contains("other_info")) {
            outputHtml = outputHtml.replace("{other_info}", propertyToHtml(userData.getOtherInfo(), userData.getPropertySeparator()));
        } else {
            outputHtml = outputHtml.replace("{other_info}", userData.getOtherInfo());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputHtmlPath))) {
            writer.write(outputHtml);
        } catch (IOException ex) {
            System.out.println("Error writing output.html");
            ex.printStackTrace();
        }
    }

    /**
     * Reads template.html
     */
    private void readTemplateFile() {
        try (InputStream is = getClass().getResourceAsStream(templateHtmlPath)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                outputHtml += line + "\n";
            }
        } catch (Exception ex) {
            System.out.println("Error reading template HTML");
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
