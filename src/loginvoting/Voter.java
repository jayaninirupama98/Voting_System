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
public class Voter {

     private int id;
     private String fullname;
     private String nic;
     private String address;
     private int age;
     private String username;
     private String password;
 
 
      Voter (int ID, String FullName, String NIC, String Address, int Age, String Username, String Password)
    {
        this.id=ID;
        this.fullname= FullName;
        this.nic= NIC; 
        this.address=Address;
        this.age=Age;
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
    public String getAddress()
    {
     return address;
    }
    public int getAge()
    {
     return age;
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
