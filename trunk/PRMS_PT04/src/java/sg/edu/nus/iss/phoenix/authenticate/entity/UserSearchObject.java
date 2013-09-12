/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.entity;

/**
 *
 * @author Jeremy
 */
public class UserSearchObject {
	  private String name;
	    private String role;
		public UserSearchObject() {
			super();
		}
		public UserSearchObject(String name, String role) {
			super();
			this.name = name;
			this.role = role;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
}