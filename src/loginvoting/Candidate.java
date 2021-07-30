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
public class Candidate {

     private int id;
     private String fullname;
     private String nic;
     private int votingno;


 
 
      Candidate (int ID, String FullName, String NIC, int VotingNo)
    {
        this.id=ID;
        this.fullname= FullName;
        this.nic= NIC;
        this.votingno=VotingNo;



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
    public int getVotingNo()
    {
     return votingno;
    }

}
