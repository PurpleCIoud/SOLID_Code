/**
 * Author: Ilja Kogans
 * Date-made: 10/11/2022
 * This class is responsible for writing User files
 */

package net.ewari;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class UserFileWriter extends FileWriter {
    private final int USERNAME_SIZE = 64;
    private final String filename;

    public UserFileWriter(@NotNull String fileName) throws IOException {
        super(fileName);
        this.filename = fileName;
    }

    public void writeUser(String username, String password) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename));
        bw.write(encodeLine(username,password));

    }

    // use username and password are encoded into a standard
    // Lines are stored as 2 strings one that is a username that is the original username padded by // followed by 0s
    // e.g. Username: Ilja Output: Ilja//0000000000000000 till 64
    // passwords are a fixed hash so no decoding for that is needed.
    public String encodeLine(String username, String password) {
        char[] unChar = username.toCharArray();
        char[] pwChar = password.toCharArray();
        StringBuilder sb1 = new StringBuilder();
        sb1.append(unChar);
        sb1.append('/').append('/');
        System.out.println(unChar);
        while (sb1.length()<=USERNAME_SIZE) {
            sb1.append('0');
        }
        return sb1.append(' ').append(pwChar).toString();
    }
}
