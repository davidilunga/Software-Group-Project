/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author David Ilunga
 */
public class DeleteAUser extends javax.swing.JFrame {

    static String UserEmail, Password,UserFirstName, UserSurname, Last_Sign_Out, Firstname,fName,sName, Email;
    static Date FromDate, ToDate; // global variables that will be used in the java class
    static boolean isAdmin;
    private static UserDatabase users = new UserDatabase();
    User user;

    /**
     * Creates new form AdminDateChecker
     */
    public DeleteAUser() {
        initComponents();
        UserEmail = LoginFrame.getUserName();
        Password = LoginFrame.getPassword();
         addRowToJTable(); //  create the table based on the content in the method
        
        UserInfoList.setSelectionBackground(Color.RED);
        TableColumnModel columnModel = UserInfoList.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(150); // set the individual width of coluimns in the table
        columnModel.getColumn(5).setPreferredWidth(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        
        centerRenderer.setHorizontalAlignment( JLabel.CENTER ); // center the text of the table
        for (int i = 0; i < UserInfoList.getColumnCount();i++){
            UserInfoList.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        
        addWindowListener(new java.awt.event.WindowAdapter() { // add a window listener to let the project know when the close button was clicked
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int input = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to close the application? You will be signed out if you leave.", "Confirm",YES_NO_OPTION);
                if (input == 0) {
                    boolean success = LogTimeOut(UserEmail); // is confirm is yes, start the logout process
                    if (!success) {
                        JOptionPane.showMessageDialog(rootPane, "We are unable log the time you signed out.");
                    } else {
                        System.exit(0); // close the project
                    }
                }
            }
        });
    }

    public DeleteAUser(String username, String password) {
        initComponents();
        UserEmail = username;
        Password = password;
        user = users.get(UserEmail);
        if (user == null) {
            System.out.println("No user matches those details. \n");
        }
        Welcome.setText("Welcome, " + user.getFirstName() + "."); // show the user that's signed in's name in the JLabel
        Welcome.setVisible(true);
        
        UserFirstName = user.getFirstName();
        UserSurname = user.getSurname();
        
        addRowToJTable(); // add the table information of all the users stored in the database
        
        UserInfoList.setSelectionBackground(Color.RED);
        TableColumnModel columnModel = UserInfoList.getColumnModel();
        columnModel.getColumn(3).setPreferredWidth(150); // set the width of teh column for the JTable
        columnModel.getColumn(5).setPreferredWidth(25); 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < UserInfoList.getColumnCount();i++){ // Align the text of the JTable to the center
            UserInfoList.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        
        addWindowListener(new java.awt.event.WindowAdapter() {//a window listener to know when the close button ahs been clicked so the user can confirm
            @Override                                           //that they want to close the application
            public void windowClosing(java.awt.event.WindowEvent e) { 
                int input = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to close the application? You will be signed out if you leave.", "Confirm",YES_NO_OPTION);
                if (input == 0) {
                    boolean success = LogTimeOut(UserEmail); // If they click yes, it will start the logout process.
                    if (!success) {
                        JOptionPane.showMessageDialog(rootPane, "We are unable to log the time you signed out."); // Catch an error if log out isn't possible
                    } else {
                        System.exit(0); // If they do want to leave, the system will close
                    }
                }
            }
        });
    }

    private void addRowToJTable() { // Fill up the JTable with all the vaild accounts stored in the database
        DefaultTableModel model = (DefaultTableModel) UserInfoList.getModel();
        model.setRowCount(0);
        ArrayList<User> list = users.getAllDeleteTable(UserEmail, UserFirstName, UserSurname); // collect the Array from the database inside this function 
        Object rowData[] = new Object[UserInfoList.getColumnCount()];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < list.size(); i++) { // assign the values to the corresponding rows in the table
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getEmail();
                rowData[4] = list.get(i).getDoB();
                rowData[5] = list.get(i).getType();
                rowData[6] = list.get(i).getDay_Created();
                model.addRow(rowData);
            }
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

        Home = new javax.swing.JButton();
        Welcome = new javax.swing.JLabel();
        SignOut = new javax.swing.JButton();
        CheckAccess = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserInfoList = new javax.swing.JTable();
        DeleteUser = new javax.swing.JLabel();
        Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Admin Login Checker");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Home.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Home.setText("Back to Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Welcome.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N

        SignOut.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        SignOut.setText("Sign Out");
        SignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignOutActionPerformed(evt);
            }
        });

        CheckAccess.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        CheckAccess.setText("Check Access");
        CheckAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckAccessActionPerformed(evt);
            }
        });

        UserInfoList.setAutoCreateRowSorter(true);
        UserInfoList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Surname", "Email Address", "Date of Birth", "Admin", "Day Created"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UserInfoList.setFocusable(false);
        jScrollPane1.setViewportView(UserInfoList);

        DeleteUser.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        DeleteUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DeleteUser.setText("Delete A User");

        Delete.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Delete.setText("Delete this User");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CheckAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(510, 510, 510)
                .addComponent(SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(DeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(463, 463, 463))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(SignOut, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(Home, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Welcome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckAccess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(DeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        MainPage homepage = new MainPage(UserEmail, Password); // create a new instance of the main page
        homepage.setVisible(true);
        this.setVisible(false); // make this frame invisible
    }//GEN-LAST:event_HomeActionPerformed

    private void SignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out?", "Confirm", YES_NO_OPTION); // confirm that the user wants to sign out
        if (input == 0) {
            boolean success = LogTimeOut(UserEmail); // starts the sign out method if the confirm is yes
            if (!success) {
                JOptionPane.showMessageDialog(this, "We are unable log the time you signed out.");
            } else {
                LoginFrame home = new LoginFrame(); // start a new instance of the login pane
                home.setVisible(true);
                this.setVisible(false); // this turns the frame invisible
            }
        } else if (input == 1) {

        }
    }//GEN-LAST:event_SignOutActionPerformed

    private void CheckAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckAccessActionPerformed
        // TODO add your handling code here:
        AdminDateChecker check = new AdminDateChecker(UserEmail, Password); // starts a new instance of the AdminDateChecker
        check.setVisible(true);
        this.setVisible(false); // makes this Frame invisible
    }//GEN-LAST:event_CheckAccessActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        int fColumn = 1;
        int sColumn = 2;
        int EmailColumn = 3;
        int row = UserInfoList.getSelectedRow();
        fName = UserInfoList.getModel().getValueAt(row, fColumn).toString(); // gets the string value of the first name
        sName = UserInfoList.getModel().getValueAt(row, sColumn).toString(); // gets the string value of the surname
        Email = UserInfoList.getModel().getValueAt(row, EmailColumn).toString(); // gets the string value of the email
        
        int input = JOptionPane.showConfirmDialog(rootPane, "You have chosen to delete "+fName+" "+sName+" with the email: "+ Email +" from the database.\n Are you sure you want to continue with this action?", "Confirm", YES_NO_OPTION);
        if (input == 0) {
            boolean success = DeleteUser(fName, sName, Email); // start the Delete user process passing the first name, surname and email as parameters
            if (!success) {
                JOptionPane.showMessageDialog(this, "We are unable to delete a user at this time.");
            } else {
                JOptionPane.showMessageDialog(this, fName+" "+sName+" has been successfully deleted." ); //this message is shown if the deletion was successful
                DeleteAUser delete = new DeleteAUser(UserEmail, Password);
                delete.setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    public static boolean LogTimeOut(String Username) { //Log Out process
        User user = users.get(Username); // check if the user is a bvalid user in the database
        if (user == null) {
            System.out.println("No user matches those details. \n");
            return false;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // create a new date format to log the time the user  signed out
        Last_Sign_Out = format1.format(new Date());
        user.setLast_Sign_Out(Last_Sign_Out);
        boolean success = users.updateTimeOut(user); // commence the update timeout process 
        if (success) {
            System.out.println("Modifictaion SUCCESSFUL Time out"); // if successful, it will return this message
            return true;
        } else {
            System.err.println("Modifictaion FAILED Time out");
            return false;
        }
    }
    
    private boolean DeleteUser(String fName, String sName, String Email) { // start the delete user process
        User user = users.getFirstName(fName);
        if (user == null) {
            System.out.println("No user matches those details. \n");
            return false;
        }
        
        boolean success = users.deleteUser(fName, sName, Email); // delete the user based on the first name, surname and email
        if (success) {
            System.out.println("Modifictaion SUCCESSFUL Delete"); // if successful, it will return this message
            return true;
        } else {
            System.err.println("Modifictaion FAILED Delete");
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeleteAUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteAUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteAUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteAUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteAUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckAccess;
    private javax.swing.JButton Delete;
    private javax.swing.JLabel DeleteUser;
    private javax.swing.JButton Home;
    private javax.swing.JButton SignOut;
    private javax.swing.JTable UserInfoList;
    private javax.swing.JLabel Welcome;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
