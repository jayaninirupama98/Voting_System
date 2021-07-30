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
public class Result1 {

     private String candidate_id;
     private int result;
     private String candidate_username;


 
 
      Result1 (String Candidate_ID, int Result)
    {
  
        this.candidate_id= Candidate_ID;
        this.result=Result;
        


    }

    public String getCandidate_ID()
    {
     return candidate_id;
    }
    public int getResult()
    {
     return result;
    }
  

    
}
