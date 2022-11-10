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
    private final String fileName;
    public UserFileReader(@NotNull String fileName) throws FileNotFoundException{
        super(fileName);
        this.fileName = fileName;
    }

    private boolean readUser() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.fileName));
            String userLine = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    // decode the inputted line into the Username and password strings
    // Lines are stored as 2 strings one that is a username that is the original username padded by // followed by 0s
    // e.g. Username: Ilja Output: Ilja//0000000000000000 till 64
    // passwords are a fixed hash so no decoding for that is needed.
    private static String[] decodeLine(String line) {
        String[] result = {"",""};
        char[] lineChars = line.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 64; i++) {
            sb.append(lineChars[i]);
        }
        return result;
    }
}
