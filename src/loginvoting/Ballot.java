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
public class Ballot {
    
     private String fullname;
     private int votingno;
     private int candidate_id;


 
 
      Ballot (String FullName, int VotingNo, int Candidate_ID)
    {
        this.fullname= FullName;
        this.votingno=VotingNo;
        this.candidate_id=Candidate_ID;
        
    }
      
    public String getFullName()
    {
     return fullname;
    }
    public int getVotingNo()
    {
     return votingno;
    }
    public int getCandidate_ID()
    {
     return candidate_id;
    }

}
