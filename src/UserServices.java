import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserServices {

    static void logIn() throws Exception {
        int email = 0;
        int pw = 1;
        int logAttempts = 0;

        while (logAttempts < 5) {
            String[] userInputInfo = new String[2];
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Your Email: ");
            userInputInfo[email] = scanner.nextLine();
            System.out.println("Enter Password: ");
            userInputInfo[pw] = scanner.nextLine();
            checkCredentials(userInputInfo);
            logAttempts++;
        }
        if (logAttempts >= 5) {
            System.out.println("Too many failed login attempts, you are now locked out.");
        }
    }

    static void checkCredentials(String[] m_userInputInfo) throws Exception{

        int email = 0;
        int pw = 1;
        File file = new File("./src/data.txt");
        BufferedReader bufferedReader = null;
        String line = "";
        String[] userDBInfo;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                userDBInfo = line.split(",");
                if ((m_userInputInfo[email].equalsIgnoreCase(userDBInfo[email])
                        && m_userInputInfo[pw].equals(userDBInfo[pw]))) {
                    System.out.println("Welcome: " + userDBInfo[2]);
                    System.exit(0);
                }
            }
            System.out.println("Invalid login, please try again: ");

        } catch (NullPointerException e) {
            throw new Exception("NullPointerException has occurred.");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new Exception("IOException has occurred.");
            } catch (NullPointerException e) {
                throw new Exception("NullPointerException has occurred.");
            }
        }
    }
}