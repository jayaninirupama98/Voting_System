/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginvoting;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dell
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        jTableAddVoter();
        jTableAddAdmin();
        jtblResult();
        
        jTableAddCandidate();
       
        
        Toolkit toolkit= getToolkit(); //to center the opening window
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
    }
    public Connection getConnection()
    {
        Connection con;
        try{
              con = DriverManager.getConnection("jdbc:mysql://localhost/votedb","root","");
              return con;
        } catch (Exception e){
          e.printStackTrace();
          return null;
        }
    }
    
    public ArrayList<Voter> getVotersList()
    {
        ArrayList <Voter> voterList = new ArrayList<Voter> ();
        Connection connection = getConnection();
        String query = "SELECT * FROM `voter`";
        Statement st;
        ResultSet rs;        
        
        try 
        { 
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Voter voter;
            while(rs.next())
                {
                    voter = new Voter(rs.getInt("voter_id"),rs.getString("fullname"), rs.getString("nic"),rs.getString("address"),rs.getInt("age"),rs.getString("username"),rs.getString("password"));
                    voterList.add(voter);
                }
        } 
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return voterList;
    }
    //Display values in tables
    public void jTableAddVoter()
        {
            ArrayList<Voter> list = getVotersList();
            DefaultTableModel model = (DefaultTableModel) jTableAddVoter.getModel();
            Object[] row = new Object [7];
            for(int i=0; i<list.size();i++)
                {
                    row[0]= list.get(i).getID();
                    row[1]= list.get(i).getFullName();
                    row[2]= list.get(i).getNIC();
                    row[3]= list.get(i).getAddress();
                    row[4]= list.get(i).getAge();
                    row[5]= list.get(i).getUsername();
                    row[6]= list.get(i).getPassword();
                    
                    model.addRow(row);
                    
                }
        }
    
    //Execute the sql Query
    public void executeSQLQuery1(String query, String message)
        {
            Connection con = getConnection();
            Statement st;
            try{
                st= con.createStatement();
                if((st.executeUpdate(query))==1)                                
                    {
                        
                        //refresh jtable data
                    DefaultTableModel model = (DefaultTableModel)jTableAddVoter.getModel();
                    model.setRowCount(0);
                      jTableAddVoter();
                    
                    JOptionPane.showMessageDialog(null,"Data "+message+" successfully");
                    }
                else
                    {
                    JOptionPane.showMessageDialog(null,"Data Not "+message);
                        
                    }
                }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
        }
    
    
    
public ArrayList<AdminUser> getAdminUsersList()
    {
    ArrayList<AdminUser> adminuserList= new ArrayList<AdminUser> ();
    Connection connection= getConnection();
    String query = "SELECT * FROM `admin`";
    Statement st;
    ResultSet rs;
    
    try
        {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            AdminUser adminuser;
            while(rs.next())
            {
                adminuser = new AdminUser(rs.getInt("admin_id"), rs.getString("fullname"), rs.getString("nic"), rs.getString("username"), rs.getString("password"));
                adminuserList.add(adminuser);
            }
           
            
        }
    
    catch (Exception e)
            {
                e.printStackTrace();
            }
    return adminuserList;
    }
    
    
    //Display values in tables
    public void jTableAddAdmin()
        {
            ArrayList<AdminUser> list = getAdminUsersList();
            DefaultTableModel model = (DefaultTableModel) jTableAddAdmin.getModel();
            Object[] row = new Object [5];
            for(int i=0; i<list.size();i++)
                {
                    row[0]= list.get(i).getID();
                    row[1]= list.get(i).getFullName();
                    row[2]= list.get(i).getNIC();
                    row[3]= list.get(i).getUsername();
                    row[4]= list.get(i).getPassword();
                    
                    model.addRow(row);
                    
                }
        }    
    
        
    //Execute the sql Query
    public void executeSQLQuery2(String query, String message)
        {
            Connection con = getConnection();
            Statement st;
            try{
                st= con.createStatement();
                if((st.executeUpdate(query))==1)                                
                    {
                        
                        //refresh jtable data
                    DefaultTableModel model = (DefaultTableModel)jTableAddAdmin.getModel();
                    model.setRowCount(0);
                      jTableAddAdmin();
                    
                    JOptionPane.showMessageDialog(null,"Data "+message+" successfully");
                    }
                else
                    {
                    JOptionPane.showMessageDialog(null,"Data Not "+message);
                        
                    }
                }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
        }
    
        public ArrayList<Candidate> getCandidateList()
    {
        ArrayList <Candidate> candidateList = new ArrayList<Candidate> ();
        Connection connection = getConnection();
        String query = "SELECT * FROM `candidate`";
        Statement st;
        ResultSet rs;        
        
        try 
        { 
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Candidate candidate;
            while(rs.next())
                {
                    candidate = new Candidate(rs.getInt("candidate_id"),rs.getString("fullname"), rs.getString("nic"),rs.getInt("voting_no"));
                    candidateList.add(candidate);
                }
        } 
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return candidateList;
    }
    //Display values in tables
    public void jTableAddCandidate()
        {
            ArrayList<Candidate> list = getCandidateList();
            DefaultTableModel model = (DefaultTableModel) jTableAddCandidate.getModel();
            Object[] row = new Object [4];
            for(int i=0; i<list.size();i++)
                {
                    row[0]= list.get(i).getID();
                    row[1]= list.get(i).getFullName();
                    row[2]= list.get(i).getNIC();
                    row[3]= list.get(i).getVotingNo();                    
                    model.addRow(row);
                    
                }
        }
    
    //Execute the sql Query
    public void executeSQLQuery3(String query, String message)
        {
            Connection con = getConnection();
            Statement st;
            try{
                st= con.createStatement();
                if((st.executeUpdate(query))==1)                                
                    {
                        
                        //refresh jtable data
                    DefaultTableModel model = (DefaultTableModel)jTableAddCandidate.getModel();
                    model.setRowCount(0);
                      jTableAddCandidate();
                    
                    JOptionPane.showMessageDialog(null,"Data "+message+" successfully");
                    }
                else
                    {
                    JOptionPane.showMessageDialog(null,"Data Not "+message);
                        
                    }
                }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            
        }
    
    
    
        public ArrayList<Result1> getResultsList()
    {
        ArrayList <Result1> resultList = new ArrayList<Result1> ();
        Connection connection = getConnection();
        String query = "SELECT candidate_name as 'candidate_id', count(*) as 'result' FROM vote_final GROUP by candidate_name";
        Statement st;
        ResultSet rs;        
        
        try 
        { 
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Result1 result1;
            while(rs.next())
                {
                    result1 = new Result1(rs.getString("candidate_id"),rs.getInt("result"));
                    resultList.add(result1);
                }
        } 
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resultList;
    }
    //Display values in tables
    public void jtblResult()
        {
            ArrayList<Result1> list = getResultsList();
            DefaultTableModel model = (DefaultTableModel) jtblResult.getModel();
            Object[] row = new Object [3];
            for(int i=0; i<list.size();i++)
                {
                    row[0]= list.get(i).getCandidate_ID();
                    row[1]= list.get(i).getResult();

                    
                    model.addRow(row);
                    
                }
        }
    
   
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlAdmin = new javax.swing.JPanel();
        jPnlLeft = new javax.swing.JPanel();
        jbtnAdd = new javax.swing.JButton();
        jbtnResult = new javax.swing.JButton();
        jtabAdmin = new javax.swing.JTabbedPane();
        jPnlAdd = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jbtnAddVoter = new javax.swing.JButton();
        jbtnAddAdmin = new javax.swing.JButton();
        jbtnAddBallotPaper = new javax.swing.JButton();
        jpnlParent1 = new javax.swing.JPanel();
        jPnlAddVoter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jtxtAddVoterID = new javax.swing.JTextField();
        jtxtAddVoterFName = new javax.swing.JTextField();
        jtxtAddVoterNICNo = new javax.swing.JTextField();
        jtxtAddVoterAddress = new javax.swing.JTextField();
        jtxtAddVoterAge = new javax.swing.JTextField();
        jtxtAddVoterUsername = new javax.swing.JTextField();
        jbtnAddVoterToTable = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAddVoter = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        jtxtAddVoterPassword = new javax.swing.JTextField();
        jbtnUpdateVoterToTable = new javax.swing.JButton();
        jbtnDeleteVoterToTable = new javax.swing.JButton();
        jPnlAddAdmin = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jtxtAddAdminID = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jtxtAddAdminFName = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jtxtAddAdminNICNo = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jtxtAddAdminUsername = new javax.swing.JTextField();
        jbtnAddAdminToTable = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jtxtAddAdminPassword = new javax.swing.JTextField();
        jbtnUpdateAdminToTable = new javax.swing.JButton();
        jbtnDeleteAdminToTable = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAddAdmin = new javax.swing.JTable();
        jPnlAddBallotPaper = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jtxtAddCandidateID = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jtxtAddCandidateFName = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jtxtAddCandidateNICNo = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAddCandidate = new javax.swing.JTable();
        jbtnDeleteCandidateToTable = new javax.swing.JButton();
        jbtnUpdateCandidateToTable = new javax.swing.JButton();
        jbtnAddCandidateToTable = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jtxtAddCandidateVotingNo = new javax.swing.JTextField();
        jPnlResults = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblResult = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPnlAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jPnlAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPnlAdmin.setPreferredSize(new java.awt.Dimension(300, 300));

        jPnlLeft.setBackground(new java.awt.Color(0, 0, 102));

        jbtnAdd.setBackground(new java.awt.Color(255, 255, 255));
        jbtnAdd.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jbtnAdd.setForeground(new java.awt.Color(0, 0, 153));
        jbtnAdd.setText("Admin Home");
        jbtnAdd.setBorder(null);
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jbtnResult.setBackground(new java.awt.Color(255, 255, 255));
        jbtnResult.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jbtnResult.setForeground(new java.awt.Color(0, 0, 153));
        jbtnResult.setText("Results");
        jbtnResult.setBorder(null);
        jbtnResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlLeftLayout = new javax.swing.GroupLayout(jPnlLeft);
        jPnlLeft.setLayout(jPnlLeftLayout);
        jPnlLeftLayout.setHorizontalGroup(
            jPnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlLeftLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnResult, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPnlLeftLayout.setVerticalGroup(
            jPnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlLeftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jbtnResult, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );

        jtabAdmin.setBackground(new java.awt.Color(51, 0, 153));
        jtabAdmin.setForeground(new java.awt.Color(0, 0, 102));

        jPnlAdd.setBackground(new java.awt.Color(51, 0, 153));
        jPnlAdd.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));

        jbtnAddVoter.setText("Voter");
        jbtnAddVoter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddVoterActionPerformed(evt);
            }
        });

        jbtnAddAdmin.setText("Admin");
        jbtnAddAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddAdminActionPerformed(evt);
            }
        });

        jbtnAddBallotPaper.setText("Ballot Paper");
        jbtnAddBallotPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddBallotPaperActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jbtnAddVoter)
                .addGap(240, 240, 240)
                .addComponent(jbtnAddAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(jbtnAddBallotPaper)
                .addGap(132, 132, 132))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddVoter)
                    .addComponent(jbtnAddAdmin)
                    .addComponent(jbtnAddBallotPaper))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPnlAdd.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jpnlParent1.setBackground(new java.awt.Color(204, 204, 255));
        jpnlParent1.setLayout(new java.awt.CardLayout());

        jPnlAddVoter.setBackground(new java.awt.Color(153, 255, 204));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 102));
        jLabel34.setText("  VOTER ");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("       Voter_ID");
        jLabel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("      Full Name");
        jLabel53.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("   National ID No");
        jLabel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("       Address");
        jLabel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("           Age");
        jLabel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("       Username");
        jLabel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddVoterID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterID.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterIDActionPerformed(evt);
            }
        });

        jtxtAddVoterFName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterFName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterFNameActionPerformed(evt);
            }
        });

        jtxtAddVoterNICNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterNICNo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterNICNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterNICNoActionPerformed(evt);
            }
        });

        jtxtAddVoterAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterAddress.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterAddressActionPerformed(evt);
            }
        });

        jtxtAddVoterAge.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterAge.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterAgeActionPerformed(evt);
            }
        });

        jtxtAddVoterUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterUsername.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterUsernameActionPerformed(evt);
            }
        });

        jbtnAddVoterToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnAddVoterToTable.setText("ADD Voter");
        jbtnAddVoterToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnAddVoterToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddVoterToTableActionPerformed(evt);
            }
        });

        jTableAddVoter.setBackground(new java.awt.Color(153, 255, 204));
        jTableAddVoter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Voter_ID", "Full Name", "NIC", "Address", "Age", "Username", "Password"
            }
        ));
        jTableAddVoter.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jTableAddVoter.setSelectionForeground(new java.awt.Color(0, 0, 51));
        jTableAddVoter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddVoterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAddVoter);

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("       Password");
        jLabel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddVoterPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddVoterPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddVoterPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddVoterPasswordActionPerformed(evt);
            }
        });

        jbtnUpdateVoterToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnUpdateVoterToTable.setText("Update Voter");
        jbtnUpdateVoterToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnUpdateVoterToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateVoterToTableActionPerformed(evt);
            }
        });

        jbtnDeleteVoterToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnDeleteVoterToTable.setText("Delete Voter");
        jbtnDeleteVoterToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnDeleteVoterToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteVoterToTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlAddVoterLayout = new javax.swing.GroupLayout(jPnlAddVoter);
        jPnlAddVoter.setLayout(jPnlAddVoterLayout);
        jPnlAddVoterLayout.setHorizontalGroup(
            jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(390, 390, 390)
                        .addComponent(jLabel34))
                    .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddVoterID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddVoterFName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddVoterNICNo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddVoterAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddVoterAge, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddVoterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnAddVoterToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                                        .addComponent(jbtnUpdateVoterToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jbtnDeleteVoterToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtxtAddVoterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlAddVoterLayout.setVerticalGroup(
            jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jtxtAddVoterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jtxtAddVoterFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(jtxtAddVoterNICNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(jtxtAddVoterAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jtxtAddVoterAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jtxtAddVoterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(jtxtAddVoterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPnlAddVoterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAddVoterToTable)
                            .addComponent(jbtnUpdateVoterToTable)
                            .addComponent(jbtnDeleteVoterToTable)))
                    .addGroup(jPnlAddVoterLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpnlParent1.add(jPnlAddVoter, "card4");

        jPnlAddAdmin.setBackground(new java.awt.Color(0, 204, 204));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 102));
        jLabel30.setText("Add Admin");
        jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setText("   Admin_ID");
        jLabel60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddAdminID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddAdminID.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddAdminID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddAdminIDActionPerformed(evt);
            }
        });

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("       Full Name");
        jLabel61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddAdminFName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddAdminFName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddAdminFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddAdminFNameActionPerformed(evt);
            }
        });

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("   National ID No");
        jLabel62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddAdminNICNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddAdminNICNo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddAdminNICNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddAdminNICNoActionPerformed(evt);
            }
        });

        jLabel63.setBackground(new java.awt.Color(255, 255, 255));
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel63.setText("       Username");
        jLabel63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddAdminUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddAdminUsername.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddAdminUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddAdminUsernameActionPerformed(evt);
            }
        });

        jbtnAddAdminToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnAddAdminToTable.setText("Add Admin");
        jbtnAddAdminToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnAddAdminToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddAdminToTableActionPerformed(evt);
            }
        });

        jLabel91.setBackground(new java.awt.Color(255, 255, 255));
        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setText("       Password");
        jLabel91.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtxtAddAdminPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddAdminPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddAdminPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddAdminPasswordActionPerformed(evt);
            }
        });

        jbtnUpdateAdminToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnUpdateAdminToTable.setText("Update Admin");
        jbtnUpdateAdminToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnUpdateAdminToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateAdminToTableActionPerformed(evt);
            }
        });

        jbtnDeleteAdminToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnDeleteAdminToTable.setText("Delete Admin");
        jbtnDeleteAdminToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnDeleteAdminToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteAdminToTableActionPerformed(evt);
            }
        });

        jTableAddAdmin.setBackground(new java.awt.Color(0, 204, 204));
        jTableAddAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Admin_ID", "Full Name", "NIC", "Username", "Password"
            }
        ));
        jTableAddAdmin.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jTableAddAdmin.setSelectionForeground(new java.awt.Color(0, 0, 51));
        jTableAddAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddAdminMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableAddAdmin);

        javax.swing.GroupLayout jPnlAddAdminLayout = new javax.swing.GroupLayout(jPnlAddAdmin);
        jPnlAddAdmin.setLayout(jPnlAddAdminLayout);
        jPnlAddAdminLayout.setHorizontalGroup(
            jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addComponent(jbtnAddAdminToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnUpdateAdminToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnDeleteAdminToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddAdminFName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddAdminNICNo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtAddAdminID, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPnlAddAdminLayout.setVerticalGroup(
            jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(jtxtAddAdminID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(jtxtAddAdminFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62)
                                    .addComponent(jtxtAddAdminNICNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel63)
                                    .addComponent(jtxtAddAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel91)
                                    .addComponent(jtxtAddAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlAddAdminLayout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPnlAddAdminLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPnlAddAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAddAdminToTable)
                            .addComponent(jbtnUpdateAdminToTable)
                            .addComponent(jbtnDeleteAdminToTable))))
                .addContainerGap())
        );

        jpnlParent1.add(jPnlAddAdmin, "card3");

        jPnlAddBallotPaper.setBackground(new java.awt.Color(0, 102, 102));
        jPnlAddBallotPaper.setForeground(new java.awt.Color(255, 255, 255));

        jLabel72.setBackground(new java.awt.Color(255, 255, 255));
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("   Candidte_ID");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jtxtAddCandidateID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddCandidateID.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddCandidateID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddCandidateIDActionPerformed(evt);
            }
        });

        jLabel73.setBackground(new java.awt.Color(255, 255, 255));
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("   Candidate Full Name");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jtxtAddCandidateFName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddCandidateFName.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddCandidateFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddCandidateFNameActionPerformed(evt);
            }
        });

        jLabel74.setBackground(new java.awt.Color(255, 255, 255));
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("    Candidate NIC No");
        jLabel74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jtxtAddCandidateNICNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddCandidateNICNo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddCandidateNICNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddCandidateNICNoActionPerformed(evt);
            }
        });

        jLabel90.setBackground(new java.awt.Color(255, 255, 255));
        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("     Candidates");
        jLabel90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jTableAddCandidate.setBackground(new java.awt.Color(0, 102, 102));
        jTableAddCandidate.setForeground(new java.awt.Color(255, 255, 255));
        jTableAddCandidate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Candidate_ID", "Full Name", "NIC", "VoterNo"
            }
        ));
        jTableAddCandidate.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jTableAddCandidate.setSelectionForeground(new java.awt.Color(0, 0, 51));
        jTableAddCandidate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddCandidateMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableAddCandidate);

        jbtnDeleteCandidateToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnDeleteCandidateToTable.setText("Delete Candidate");
        jbtnDeleteCandidateToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnDeleteCandidateToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteCandidateToTableActionPerformed(evt);
            }
        });

        jbtnUpdateCandidateToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnUpdateCandidateToTable.setText("Update Candidate");
        jbtnUpdateCandidateToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnUpdateCandidateToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateCandidateToTableActionPerformed(evt);
            }
        });

        jbtnAddCandidateToTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnAddCandidateToTable.setText("ADD Candidate");
        jbtnAddCandidateToTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jbtnAddCandidateToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddCandidateToTableActionPerformed(evt);
            }
        });

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("         Voting No");
        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jtxtAddCandidateVotingNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtAddCandidateVotingNo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtAddCandidateVotingNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAddCandidateVotingNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlAddBallotPaperLayout = new javax.swing.GroupLayout(jPnlAddBallotPaper);
        jPnlAddBallotPaper.setLayout(jPnlAddBallotPaperLayout);
        jPnlAddBallotPaperLayout.setHorizontalGroup(
            jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAddBallotPaperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlAddBallotPaperLayout.createSequentialGroup()
                        .addComponent(jbtnAddCandidateToTable, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAddBallotPaperLayout.createSequentialGroup()
                        .addComponent(jbtnUpdateCandidateToTable)
                        .addGap(41, 41, 41)
                        .addComponent(jbtnDeleteCandidateToTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jtxtAddCandidateVotingNo)
                    .addComponent(jtxtAddCandidateID)
                    .addComponent(jtxtAddCandidateFName)
                    .addComponent(jtxtAddCandidateNICNo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPnlAddBallotPaperLayout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlAddBallotPaperLayout.setVerticalGroup(
            jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAddBallotPaperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAddBallotPaperLayout.createSequentialGroup()
                        .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtAddCandidateID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel72))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jtxtAddCandidateFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(jtxtAddCandidateNICNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(jtxtAddCandidateVotingNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlAddBallotPaperLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPnlAddBallotPaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAddCandidateToTable)
                    .addComponent(jbtnUpdateCandidateToTable)
                    .addComponent(jbtnDeleteCandidateToTable))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jpnlParent1.add(jPnlAddBallotPaper, "card2");

        jPnlAdd.add(jpnlParent1, java.awt.BorderLayout.CENTER);

        jtabAdmin.addTab("", jPnlAdd);

        jPnlResults.setBackground(new java.awt.Color(0, 0, 51));

        jLabel92.setBackground(new java.awt.Color(255, 255, 255));
        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("      View Results");
        jLabel92.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jtblResult.setBackground(new java.awt.Color(0, 0, 51));
        jtblResult.setForeground(new java.awt.Color(255, 255, 255));
        jtblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Canidate Name", "Votes"
            }
        ));
        jScrollPane4.setViewportView(jtblResult);

        javax.swing.GroupLayout jPnlResultsLayout = new javax.swing.GroupLayout(jPnlResults);
        jPnlResults.setLayout(jPnlResultsLayout);
        jPnlResultsLayout.setHorizontalGroup(
            jPnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlResultsLayout.createSequentialGroup()
                .addGroup(jPnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlResultsLayout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlResultsLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        jPnlResultsLayout.setVerticalGroup(
            jPnlResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        jtabAdmin.addTab("", jPnlResults);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 102));
        jLabel1.setText("Admin Panel");

        javax.swing.GroupLayout jPnlAdminLayout = new javax.swing.GroupLayout(jPnlAdmin);
        jPnlAdmin.setLayout(jPnlAdminLayout);
        jPnlAdminLayout.setHorizontalGroup(
            jPnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAdminLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtabAdmin)
                        .addContainerGap())
                    .addGroup(jPnlAdminLayout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPnlAdminLayout.setVerticalGroup(
            jPnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlAdminLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jtabAdmin))
                    .addComponent(jPnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jPnlAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 1119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1156, 545));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResultActionPerformed
        // TODO add your handling code here:
        jtabAdmin.setSelectedIndex(1);
    }//GEN-LAST:event_jbtnResultActionPerformed

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        // TODO add your handling code here:
        jtabAdmin.setSelectedIndex(0);
    }//GEN-LAST:event_jbtnAddActionPerformed

    private void jtxtAddCandidateVotingNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddCandidateVotingNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddCandidateVotingNoActionPerformed

    private void jbtnAddCandidateToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddCandidateToTableActionPerformed
        // TODO add your handling code here:
        String query   = "INSERT INTO `candidate`(`fullname`, `nic`, `voting_no`) VALUES ('"+jtxtAddCandidateFName.getText()+"','"+jtxtAddCandidateNICNo.getText()+"','"+jtxtAddCandidateVotingNo.getText()+"')";
        executeSQLQuery3(query,"Inserted");
    }//GEN-LAST:event_jbtnAddCandidateToTableActionPerformed

    private void jbtnUpdateCandidateToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateCandidateToTableActionPerformed
        // TODO add your handling code here:
        String query = "UPDATE `candidate` SET `fullname`='"+jtxtAddCandidateFName.getText()+"',`nic`='"+jtxtAddCandidateNICNo.getText()+"',`voting_no`='"+jtxtAddCandidateVotingNo.getText()+"' WHERE `candidate_id`= "+jtxtAddCandidateID.getText();
        executeSQLQuery3(query," Updated");
    }//GEN-LAST:event_jbtnUpdateCandidateToTableActionPerformed

    private void jbtnDeleteCandidateToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteCandidateToTableActionPerformed
        // TODO add your handling code here:
        String query="DELETE FROM `candidate` WHERE candidate_id = "+jtxtAddCandidateID.getText();
        executeSQLQuery3(query," Deleted");
    }//GEN-LAST:event_jbtnDeleteCandidateToTableActionPerformed

    private void jTableAddCandidateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddCandidateMouseClicked
        // TODO add your handling code here:
        int i= jTableAddCandidate.getSelectedRow();
        TableModel model= jTableAddCandidate.getModel();
        jtxtAddCandidateID.setText(model.getValueAt(i,0).toString());
        jtxtAddCandidateFName.setText(model.getValueAt(i,1).toString());
        jtxtAddCandidateNICNo.setText(model.getValueAt(i,2).toString());
        jtxtAddCandidateVotingNo.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_jTableAddCandidateMouseClicked

    private void jtxtAddCandidateNICNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddCandidateNICNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddCandidateNICNoActionPerformed

    private void jtxtAddCandidateFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddCandidateFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddCandidateFNameActionPerformed

    private void jtxtAddCandidateIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddCandidateIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddCandidateIDActionPerformed

    private void jTableAddAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddAdminMouseClicked
        // TODO add your handling code here:
        //Display Selected Row In text fields
        int i= jTableAddAdmin.getSelectedRow();
        TableModel model= jTableAddAdmin.getModel();
        jtxtAddAdminID.setText(model.getValueAt(i,0).toString());
        jtxtAddAdminFName.setText(model.getValueAt(i,1).toString());
        jtxtAddAdminNICNo.setText(model.getValueAt(i,2).toString());
        jtxtAddAdminUsername.setText(model.getValueAt(i,3).toString());
        jtxtAddAdminPassword.setText(model.getValueAt(i,4).toString());
    }//GEN-LAST:event_jTableAddAdminMouseClicked

    private void jbtnDeleteAdminToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteAdminToTableActionPerformed
        // TODO add your handling code here:
        String query="DELETE FROM `admin` WHERE admin_id = "+jtxtAddAdminID.getText();
        executeSQLQuery2(query," Deleted");
    }//GEN-LAST:event_jbtnDeleteAdminToTableActionPerformed

    private void jbtnUpdateAdminToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateAdminToTableActionPerformed
        // TODO add your handling code here:        String query = "UPDATE `voter` SET `fullname`='"+jtxtAddVoterFName.getText()+"',`nic`='"+jtxtAddVoterNICNo.getText()+"',`address`='"+jtxtAddVoterAddress.getText()+"',`age`='"+jtxtAddVoterAge.getText()+"',`username`='"+jtxtAddVoterUsername.getText()+"',`password`="+jtxtAddVoterPassword.getText()+" WHERE `voter_id`= "+jtxtAddVoterID.getText();

        String query = "UPDATE `admin` SET `fullname`='"+jtxtAddAdminFName.getText()+"',`nic`='"+jtxtAddAdminNICNo.getText()+"',`username`='"+jtxtAddAdminUsername.getText()+"',`password`='"+jtxtAddAdminPassword.getText()+"' WHERE `admin_id`= "+jtxtAddAdminID.getText();
        executeSQLQuery2(query," Updated");
    }//GEN-LAST:event_jbtnUpdateAdminToTableActionPerformed

    private void jtxtAddAdminPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddAdminPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddAdminPasswordActionPerformed

    private void jbtnAddAdminToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddAdminToTableActionPerformed
        // TODO add your handling code here:
        String query   = "INSERT INTO `admin`(`fullname`, `nic`, `username`, `password`) VALUES ('"+jtxtAddAdminFName.getText()+"','"+jtxtAddAdminNICNo.getText()+"','"+jtxtAddAdminUsername.getText()+"','"+jtxtAddAdminPassword.getText()+"')";
        executeSQLQuery2(query,"Inserted");
    }//GEN-LAST:event_jbtnAddAdminToTableActionPerformed

    private void jtxtAddAdminUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddAdminUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddAdminUsernameActionPerformed

    private void jtxtAddAdminNICNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddAdminNICNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddAdminNICNoActionPerformed

    private void jtxtAddAdminFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddAdminFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddAdminFNameActionPerformed

    private void jtxtAddAdminIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddAdminIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddAdminIDActionPerformed

    private void jbtnDeleteVoterToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteVoterToTableActionPerformed
        // TODO add your handling code here:
        String query="DELETE FROM `voter` WHERE voter_id = "+jtxtAddVoterID.getText();
        executeSQLQuery1(query," Deleted");
    }//GEN-LAST:event_jbtnDeleteVoterToTableActionPerformed

    private void jbtnUpdateVoterToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateVoterToTableActionPerformed
        // TODO add your handling code here:
        String query = "UPDATE `voter` SET `fullname`='"+jtxtAddVoterFName.getText()+"',`nic`='"+jtxtAddVoterNICNo.getText()+"',`address`='"+jtxtAddVoterAddress.getText()+"',`age`='"+jtxtAddVoterAge.getText()+"',`username`='"+jtxtAddVoterUsername.getText()+"',`password`='"+jtxtAddVoterPassword.getText()+"' WHERE `voter_id`= "+jtxtAddVoterID.getText();
        executeSQLQuery1(query," Updated");
    }//GEN-LAST:event_jbtnUpdateVoterToTableActionPerformed

    private void jtxtAddVoterPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterPasswordActionPerformed

    private void jTableAddVoterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddVoterMouseClicked
        // TODO add your handling code here:
        //Display Selected Row In text fields
        int i= jTableAddVoter.getSelectedRow();
        TableModel model= jTableAddVoter.getModel();
        jtxtAddVoterID.setText(model.getValueAt(i,0).toString());
        jtxtAddVoterFName.setText(model.getValueAt(i,1).toString());
        jtxtAddVoterNICNo.setText(model.getValueAt(i,2).toString());
        jtxtAddVoterAddress.setText(model.getValueAt(i,3).toString());
        jtxtAddVoterAge.setText(model.getValueAt(i,4).toString());
        jtxtAddVoterUsername.setText(model.getValueAt(i,5).toString());
        jtxtAddVoterPassword.setText(model.getValueAt(i,6).toString());
    }//GEN-LAST:event_jTableAddVoterMouseClicked

    private void jbtnAddVoterToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddVoterToTableActionPerformed
        // TODO add your handling code here:
        String query   = "INSERT INTO `voter`(`fullname`, `nic`, `address`, `age`, `username`, `password`) VALUES ('"+jtxtAddVoterFName.getText()+"','"+jtxtAddVoterNICNo.getText()+"','"+jtxtAddVoterAddress.getText()+"','"+jtxtAddVoterAge.getText()+"','"+jtxtAddVoterUsername.getText()+"','"+jtxtAddVoterPassword.getText()+"')";
        executeSQLQuery1(query,"Inserted");
    }//GEN-LAST:event_jbtnAddVoterToTableActionPerformed

    private void jtxtAddVoterUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterUsernameActionPerformed

    private void jtxtAddVoterAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterAgeActionPerformed

    private void jtxtAddVoterAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterAddressActionPerformed

    private void jtxtAddVoterNICNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterNICNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterNICNoActionPerformed

    private void jtxtAddVoterFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterFNameActionPerformed

    private void jtxtAddVoterIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAddVoterIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAddVoterIDActionPerformed

    private void jbtnAddBallotPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddBallotPaperActionPerformed
        // TODO add your handling code here:
        jpnlParent1.removeAll();
        jpnlParent1.add(jPnlAddBallotPaper);
        jpnlParent1.repaint();
        jpnlParent1.revalidate();
    }//GEN-LAST:event_jbtnAddBallotPaperActionPerformed

    private void jbtnAddAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddAdminActionPerformed
        // TODO add your handling code here:
        jpnlParent1.removeAll();
        jpnlParent1.add(jPnlAddAdmin);
        jpnlParent1.repaint();
        jpnlParent1.revalidate();
    }//GEN-LAST:event_jbtnAddAdminActionPerformed

    private void jbtnAddVoterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddVoterActionPerformed
        // TODO add your handling code here:
        jpnlParent1.removeAll();
        jpnlParent1.add(jPnlAddVoter);
        jpnlParent1.repaint();
        jpnlParent1.revalidate();
    }//GEN-LAST:event_jbtnAddVoterActionPerformed
                                                                                                                                                                                            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         /**/
        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfojpnlAddx.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLoojbtnAdd(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPnlAdd;
    private javax.swing.JPanel jPnlAddAdmin;
    private javax.swing.JPanel jPnlAddBallotPaper;
    private javax.swing.JPanel jPnlAddVoter;
    private javax.swing.JPanel jPnlAdmin;
    private javax.swing.JPanel jPnlLeft;
    private javax.swing.JPanel jPnlResults;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableAddAdmin;
    private javax.swing.JTable jTableAddCandidate;
    private javax.swing.JTable jTableAddVoter;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnAddAdmin;
    private javax.swing.JButton jbtnAddAdminToTable;
    private javax.swing.JButton jbtnAddBallotPaper;
    private javax.swing.JButton jbtnAddCandidateToTable;
    private javax.swing.JButton jbtnAddVoter;
    private javax.swing.JButton jbtnAddVoterToTable;
    private javax.swing.JButton jbtnDeleteAdminToTable;
    private javax.swing.JButton jbtnDeleteCandidateToTable;
    private javax.swing.JButton jbtnDeleteVoterToTable;
    private javax.swing.JButton jbtnResult;
    private javax.swing.JButton jbtnUpdateAdminToTable;
    private javax.swing.JButton jbtnUpdateCandidateToTable;
    private javax.swing.JButton jbtnUpdateVoterToTable;
    private javax.swing.JPanel jpnlParent1;
    private javax.swing.JTabbedPane jtabAdmin;
    private javax.swing.JTable jtblResult;
    private javax.swing.JTextField jtxtAddAdminFName;
    private javax.swing.JTextField jtxtAddAdminID;
    private javax.swing.JTextField jtxtAddAdminNICNo;
    private javax.swing.JTextField jtxtAddAdminPassword;
    private javax.swing.JTextField jtxtAddAdminUsername;
    private javax.swing.JTextField jtxtAddCandidateFName;
    private javax.swing.JTextField jtxtAddCandidateID;
    private javax.swing.JTextField jtxtAddCandidateNICNo;
    private javax.swing.JTextField jtxtAddCandidateVotingNo;
    private javax.swing.JTextField jtxtAddVoterAddress;
    private javax.swing.JTextField jtxtAddVoterAge;
    private javax.swing.JTextField jtxtAddVoterFName;
    private javax.swing.JTextField jtxtAddVoterID;
    private javax.swing.JTextField jtxtAddVoterNICNo;
    private javax.swing.JTextField jtxtAddVoterPassword;
    private javax.swing.JTextField jtxtAddVoterUsername;
    // End of variables declaration//GEN-END:variables
}
 
    
  

 
 
 
 
 



