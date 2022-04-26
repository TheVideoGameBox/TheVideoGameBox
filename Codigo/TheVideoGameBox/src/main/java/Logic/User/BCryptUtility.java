package Logic.User;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtility {
    private static final int logRounds = 11;

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
