package api.helpdesk.domain.models.dto;

import java.util.ArrayList;
import java.util.List;


import api.helpdesk.domain.models.Departament;

public class UserDTO {

      private long id;

      private String name;

      private String username;

      private String password;
      

      private List<String> roles = new ArrayList<>();

      private String contato;

      private Departament departamento;


      public UserDTO(Long id, String name, String username, List<String> roles, String contato, Departament departamento) {
            this.id = id;
            this.name = name;
            this.username = username;
            this.roles = roles;
            this.contato = contato;
            this.departamento = departamento;
      }


      public UserDTO(Long id, String string, Departament departament, List<String> list) {
      }


      public long getId() {
            return id;
      }


      public void setId(long id) {
            this.id = id;
      }


      public String getName() {
            return name;
      }


      public void setName(String name) {
            this.name = name;
      }


      public String getUsername() {
            return username;
      }


      public void setUsername(String username) {
            this.username = username;
      }


      public List<String> getRoles() {
            return roles;
      }


      public void setRoles(List<String> roles) {
            this.roles = roles;
      }


      public String getContato() {
            return contato;
      }


      public void setContato(String contato) {
            this.contato = contato;
      }


      public Departament getDepartamento() {
            return departamento;
      }


      public void setDepartamento(Departament departamento) {
            this.departamento = departamento;
      }
      
}
