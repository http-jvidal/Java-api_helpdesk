package api.helpdesk.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {

      @Value("${security.config.prefix}")
      public String prefix;

      @Value("{security.config.key}")
      public String key;

      @Value("${security.config.expiration}")
      public Long expiration;

      public  void setPREFIX(String prefix) {
            this.prefix = prefix;
      }
      public  void setKEY(String key) {
            this.key = key;
      }
      public  void setEXPIRATION(Long expiration) {
            this.expiration = expiration;
      }
      public  String getPREFIX() {
            return this.prefix;
      }
      public  String getKEY() {
            return this.key;
      }
      public  Long getEXPIRATION() {
            return this.expiration;
      }


      
}
