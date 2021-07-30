/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginvoting;

/**
 *
 * @author Dell
 */
class AdminUser {
    
    private int  id;
    private String fullname;
    private String nic;
    private String username;
    private String password;
    
    public AdminUser(int ID,String FullName, String NIC, String Username, String Password)
        {
            this.id=ID;
            this.fullname=FullName;
            this.nic=NIC;
            this.username=Username;
            this.password=Password;
                           
        }
    public int getID()
        {
            return id;
        }
    public String getFullName()
        {
            return fullname;
        }
    public String getNIC()
        {
            return nic;
        }
    public String getUsername()
        {
            return username;
        }
    public String getPassword()
        {
            return password;
        }    
}
