
// import java.util.scanner;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Locale.LanguageRange;
import java.io.File;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Compile {

    // execute all code here
    public static void main(String[] args) {
        init();
    }

    static void print(String text) {
        System.out.println(text);
    }

    static void info() {
        print("Welcome To Lagrange Interpolation Compiler");
        print("");
        print("-l-c --langrange-compile : compile the LagrangeInterpolation");
        print("");
        print("-c-f --compile-file : compile the codes written in that file.");
        print("");
    }

    static void init() {
        // print information to terminal
        info();
        // user request info
        print("Enter some options from the info above");
        print("");

        // ask user for inputs

        Scanner scannerObj = new Scanner(System.in);

        String options = scannerObj.nextLine();

        // check options arguments
        if ("-c-f".equals(options) || "--compile-file".equals(options)) {
            // ask user for file name
            print("");
            print("Enter the name of the file you would like to compile: ");
            print("");
            String filename = scannerObj.nextLine();

            if (filename == "") {
                System.out.print("Input field cant be empty");
                return;
            }
            compileJavaFileCode(filename, "dscdsc");
        } else if ("-l-c".equals(options) || "--langrange-compile".equals(options)) {
            // compile lagrange codes here
            Lagrange();
        } else {
            // if not options match the options above
            print("Sorry, command not found, use the list below as a refrence.");
            print("");
            info();
        }
    }

    static Boolean fileExist(String filename) {
        String parentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        String fileDir = parentDir.concat("/javaFiles/" + filename);

        File file = new File(fileDir);

        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    static void compileJavaFileCode(String filename, String code) {
        String parentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        String javaFiles = parentDir.concat("/javaFiles");
        // check if file exist
        Boolean fileCheck = fileExist(filename);

        if (!fileCheck) {
            print("File provided doesnt exist");
            print("");
            return;
        }

        String os = System.getProperty("os.name");

        String command;

        // check the os for some certain commands to run

        if (os == "Linux") {
            command = "java " + javaFiles + "/" + filename;
        } else if (os == "Windows 10") {
            command = "cd " + javaFiles + " && java " + filename;
        } else {
            command = "java " + javaFiles + "/" + filename;
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("***The Output is ***");
                System.out.println(output);
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void Lagrange() {
        // Declaration of the scanner variable
        Scanner myScanner = new Scanner(System.in);

        // Declaration of variables
        int n; // Number of terms
        int counta, countb; // Loop variables, for counting loops

        float[] arrayx = new float[200]; // Array limit 199
        float[] arrayy = new float[200]; // Array limit 199
        // The arbitrary value, x to be entered for
        // which the value of y can be known
        float x = 0;
        float y = 0; // The corresponding value, f(x)=y
        float numerator; // The numerator
        float denominator; // The denominator

        // Prompts a user to enter a value
        System.out.print("Enter the number of terms n: ");
        n = myScanner.nextInt(); // Store the value in n

        // Prompts user to enter the array for X
        System.out.println("Enter the values that are in xi.");

        for (counta = 0; counta < n; counta++) // Start the loop for X
        {
            // Prompts the user to enter the sequel for xi
            System.out.print("Enter the value for x" + counta + ": ");
            // Store the sequel in the Array, arrayx
            arrayx[counta] = myScanner.nextFloat();
        }
        // Promt user to enter the array for Y
        System.out.println("Enter the values that are in yi.");
        for (counta = 0; counta < n; counta++) // loop for Y
        {
            // Promp the user to enter the sequel for yi
            System.out.print("Enter the value for y" + counta + ": ");
            // Store the sequel in the Array, arrayy
            arrayy[counta] = myScanner.nextFloat();
        }
        // Prompts the user to enter any (the arbitray)
        // value x to get the corresponding value of y
        System.out.println("Enter the arbitrary value x for which you want the value y: ");
        x = myScanner.nextFloat(); // Store the value in x
        System.out.println("The point of interpolation. ");
        // first Loop for the polynomial calculation
        for (counta = 0; counta < n; counta++) {
            // Initialisation of variable
            numerator = 1;
            denominator = 1;

            // second Loop for the polynomial calculation
            for (countb = 0; countb < n; countb++) {
                if (countb != counta) {
                    numerator = numerator * (x - arrayx[countb]);
                    denominator = denominator * (arrayx[counta] - arrayx[countb]);
                }
            }
            y = y + (numerator / denominator) * arrayy[counta];
        }
        System.out.println("When x = " + x + "," + " y = " + y);
    }

}
