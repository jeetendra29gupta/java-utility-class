package my.number.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        File propertiesFile = new File("src/main/resources/application.properties");
        try (InputStream input = new FileInputStream(propertiesFile)) {
            Properties prop = new Properties();
            prop.load(input);
            Date date = new Date();
            System.out.println(date + " -- " + prop.getProperty("app.name") + " is Started. !! ");

            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter an integer number: \n");
            if (userInput.hasNextInt()) {
                int number = userInput.nextInt();
                if (number <= 0) {
                    System.out.println("Enter numbers greater than ZERO");
                } else {
                    Application app = new Application();
                    app.numberToWord((number / 1000000000), "ARAB");
                    app.numberToWord((number / 10000000) % 100, "CRORE ");
                    app.numberToWord(((number / 100000) % 100), "LAKH ");
                    app.numberToWord(((number / 1000) % 100), "THOUSAND ");
                    app.numberToWord(((number / 100) % 10), "HUNDRED ");
                    app.numberToWord((number % 100), "");
                }
            }
            userInput.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            System.out.println("\n");
        }
    }

    public void numberToWord(int number, String word) {
        String[] one = {"", "ONE ", "TWO ", "THREE ", "FOUR ", "FIVE ", "SIX ", "SEVEN ", "EIGHT ",
                "NINE ", "TEN ", "ELEVEN ", "TWELVE ", "THIRTEEN ", "FOURTEEN ", "FIFTEEN ", "SIXTEEN ",
                "SEVENTEEN ", "EIGHTEEN ", "NINETEEN "};
        String[] ten = {"", "", "TWENTY ", "THIRTY ", "FORTY ", "FIFTY ",
                "SIXTY ", "SEVENTY ", "EIGHTY ", "NINETY "};
        if (number > 19) {
            System.out.print(ten[number / 10] + one[number % 10]);
        } else {
            System.out.print(one[number]);
        }

        if (number > 0) {
            System.out.print(word);
        }

    }
}
