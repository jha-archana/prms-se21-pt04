/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMaintenance;

/**
 *
 * @author erik.pyanto
 */
public class Role {

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
    
    
    public static String ROLE_ADMIN = "ADMIN";
    public static String ROLE_PRODUCER = "PRODUCER";
    public static String ROLE_PRESENTER = "PRESENTER";
}
