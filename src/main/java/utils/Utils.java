package utils;

public class Utils {
  public static String hashPassword(String plainPassword) {
    String salt = BCrypt.gensalt();
    return  BCrypt.hashpw(plainPassword, salt);
  }
}
