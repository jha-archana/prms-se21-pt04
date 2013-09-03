/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMaintenance;

import java.util.ArrayList;

/**
 *
 * @author erik.pyanto
 */
public class UserInfo {

    private String id;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private ArrayList<Role> roles = new ArrayList<Role>();

    public ArrayList<Role> getAllRoles() {
        return this.roles;
    }

    public void AddRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public boolean IsAdmin() {
        if(this.searchRole(Role.ROLE_ADMIN) == null)
            return false;
        return true;
    }

    public boolean IsProducer() {
        if(this.searchRole(Role.ROLE_PRODUCER) == null)
            return false;
        return true;
    }

    public boolean IsPresenter() {
        if(this.searchRole(Role.ROLE_PRESENTER) == null)
            return false;
        return true;
    }

    private Role searchRole(String roleName) {
        for (Role role : getAllRoles()) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        return null;
    }
}
