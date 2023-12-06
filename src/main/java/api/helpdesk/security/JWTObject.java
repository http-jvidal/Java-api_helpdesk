package api.helpdesk.security;

import java.util.Date;
import java.util.List;
import java.util.Arrays;
public class JWTObject {
    public String subject;
    public Date issuedAt;
    public Date expiration;
    public List roles;
    
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Date getIssuedAt() {
        return issuedAt;
    }
    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
    public Date getExpiration() {
        return expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles){
        this.roles = Arrays.asList(roles);
    }
    
}
