package api.helpdesk.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {
    
    public static String PREFIX;
    public static String KEY;
    public static Long EXPIRATION;

    public static void setPREFIX(String pREFIX) {
        PREFIX = pREFIX;
    }
    public static void setKEY(String kEY) {
        KEY = kEY;
    }
    public static void setEXPIRATION(Long eXPIRATION) {
        EXPIRATION = eXPIRATION;
    }


    
}
