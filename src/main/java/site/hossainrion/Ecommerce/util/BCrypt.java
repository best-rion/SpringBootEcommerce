package site.hossainrion.Ecommerce.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
    
    public static String hash(String password) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
