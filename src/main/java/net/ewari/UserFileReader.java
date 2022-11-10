/**
 * Author: Ilja Kogans
 * Date-made: 10/11/2022
 * This class is responsible for reading User files
 */

package net.ewari;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class UserFileReader extends FileReader {
    final static int USERNAME_SIZE = 64;
    final static int PASSWORD_SIZE = 64;
    private final String fileName;
    public UserFileReader(@NotNull String fileName) throws FileNotFoundException{
        super(fileName);
        this.fileName = fileName;
    }
    private boolean readUser() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.fileName));
            String userLine = br.readLine();
            String[] decoded = decodeLine(userLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    // decode the inputted line into the Username and password strings
    // Lines are stored as 2 strings one that is a username that is the original username padded by // followed by 0s
    // e.g. Username: Ilja Output: Ilja//0000000000000000 till 64
    // passwords are a fixed hash so no decoding for that is needed.
    private String[] decodeLine(String line) {
        String[] result = {"",""};
        char[] lineChars = line.toCharArray();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0 ; i < USERNAME_SIZE; i++) {
            if ('/' != lineChars[i] && '/' != lineChars[i+1]) {
                sb1.append(lineChars[i]);
            }
        }
        for (int i = 64 ; i<(PASSWORD_SIZE+USERNAME_SIZE);i++) {
            sb2.append(lineChars[i]);
        }
        String username = sb1.toString();
        String password = sb2.toString();
        return new String[]{username,password};
    }
}
