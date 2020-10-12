

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Login {


    public static String[] loadInformation() throws IOException,ArrayIndexOutOfBoundsException {

        Path inputPath = Paths.get("src/main/input.txt");
        String[] userPassMsg = new String[3];

        try {
            List<String> fileContents = Files.readAllLines(inputPath);
            if(fileContents.size()>=4) {
                System.err.println("Enter 3 lines in your input.txt file: email, password, and desired automatic message");
                System.exit(-1);
            }

            int i = 0;
            for (String line : fileContents) {
                userPassMsg[i] = line.trim();
                i++;
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return userPassMsg;


    }


}
