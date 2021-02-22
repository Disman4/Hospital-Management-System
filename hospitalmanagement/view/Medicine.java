/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

/**
 *
 * @author Tanaka
 */
public class Medicine {
    private String id;
    private String name;
    private String group;
    private String expirydate;
    private String quantity;

    /**
     *
     * @param id used for medicine id
     * @param name used for medicine name
     * @param group used for medicine group
     * @param expirydate used for medicine expiry date
     * @param quantity used for medicine quantity
     */
    public Medicine(String id, String name, String group, String expirydate, String quantity) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.expirydate = expirydate;
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }
    
    
    
}
