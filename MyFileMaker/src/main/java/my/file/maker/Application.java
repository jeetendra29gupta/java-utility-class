package my.file.maker;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        File propertiesFile = new File("application.properties");
        try (InputStream input = new FileInputStream(propertiesFile)) {
            Properties prop = new Properties();
            prop.load(input);
            System.out.println(prop.getProperty("app.name") + " is Started.... ");
            Date date = new Date();
            String owner = prop.getProperty("owner.name");
            String title = prop.getProperty("title.name");
            String[] wordSplit = title.split(" ");
            StringBuilder wordCamel = new StringBuilder();
            StringBuilder wordCapital = new StringBuilder();
            for (String s : wordSplit) {
                wordCamel.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
                wordCapital.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append("-");
            }
            File filename = new File("./data/" + wordCapital.substring(0, wordCapital.length() - 1) + ".MD");
            if (!filename.exists()) {
                FileWriter fw = new FileWriter(filename, true);
                fw.write("---\n");
                fw.write("Title: '" + wordCamel.toString().trim() + "'\n");
                fw.write("Owner: '" + owner + "'\n");
                fw.write("Date: '" + date + "'\n");
                fw.write("---\n");
                fw.close();
                System.out.println("File is created !!");
            }else{
                System.out.println("File is already exists !!");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
