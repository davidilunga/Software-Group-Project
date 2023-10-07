/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ilunga
 */
public class AdminDateChecker extends javax.swing.JFrame {

    static String UserName, Password, Last_Sign_Out, Firstname;
    static Date FromDate, ToDate; // Global Variables that will be used throughout the program
    static boolean isAdmin;
    private static UserDatabase users = new UserDatabase();

    /**
     * Creates new form AdminDateChecker
     */
    public AdminDateChecker() {
        initComponents();
        UserName = LoginFrame.getUserName();
        Password = LoginFrame.getPassword(); // Constructor to create an instance without any parameters 
        addRowToJTable();
        
        AccessTable.setAutoCreateRowSorter(true);
        
        addWindowListener(new java.awt.event.WindowAdapter() {//a window listener to know when the close button ahs been clicked so the user can confirm
            @Override                                           //that they want to close the application
            public void windowClosing(java.awt.event.WindowEvent e) { 
                int input = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to close the application? You will be signed out if you leave.", "Confirm",YES_NO_OPTION);
                if (input == 0) {
                    boolean success = LogTimeOut(UserName); // If they click yes, it will start the logout process.
                    if (!success) {
                        JOptionPane.showMessageDialog(rootPane, "We are unable to log the time you signed out."); // Catch an error if log out isn't possible
                    } else {
                        System.exit(0); // If they do want to leave, the system will close
                    }
                }
            }
        });
    }

    public AdminDateChecker(String username, String password) { // This will create a new instance of the User logs using the username and password as parameters
        initComponents();
        UserName = username;
        Password = password;
        User user = users.get(UserName);
        if (user == null) { // This will check if the user that is signed in is a valid user
            System.out.println("No user matches those details. \n");
        }
        Welcome.setText("Welcome, " + user.getFirstName() + ".");
        Welcome.setVisible(true);
        addRowToJTable(); // This creates to table that holds all the logs that the admin can see

        addWindowListener(new java.awt.event.WindowAdapter() {//a window listener to know when the close button ahs been clicked so the user can confirm
            @Override                                           //that they want to close the application
            public void windowClosing(java.awt.event.WindowEvent e) { 
                int input = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to close the application? You will be signed out if you leave.", "Confirm",YES_NO_OPTION);
                if (input == 0) {
                    boolean success = LogTimeOut(UserName); // If they click yes, it will start the logout process.
                    if (!success) {
                        JOptionPane.showMessageDialog(rootPane, "We are unable to log the time you signed out."); // Catch an error if log out isn't possible
                    } else {
                        System.exit(0); // If they do want to leave, the system will close
                    }
                }
            }
        });
    }

    private void addRowToJTable() {
        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel(); // This constructor allows my to create a variable to edit my table
        model.setRowCount(0); // sets the number of rows in the table to 0
        ArrayList<User> list = users.getAllUserLog(); // this will check the database for all the logs that have been recorded
        Object rowData[] = new Object[6]; // setting the column length
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else { // this assigns what will be represented inside each column
            for (int i = 0; i < list.size(); i++) {
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
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
        FromDateText = new javax.swing.JLabel();
        ToDateText = new javax.swing.JLabel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AccessTable = new javax.swing.JTable();
        FirstNameField = new javax.swing.JTextField();
        Filter = new javax.swing.JButton();
        Admin = new javax.swing.JCheckBox();
        Clear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        DeleteAllLogs = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Admin Login Checker");
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);

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

        FromDateText.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        FromDateText.setText("Select a date to display from:");

        ToDateText.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        ToDateText.setText("Select a date to display to:");

        jDateChooserFrom.setFocusable(false);
        jDateChooserFrom.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N

        jDateChooserTo.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel1.setText("Enter a first name that you would like to filter by: ");

        AccessTable.setAutoCreateRowSorter(true);
        AccessTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.lightGray, java.awt.Color.darkGray, java.awt.Color.lightGray));
        AccessTable.setFont(new java.awt.Font("Tw Cen MT", 2, 12)); // NOI18N
        AccessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "First Name", "Surname", "Admin", "Last Sign in", "Last Sign out"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AccessTable.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(AccessTable);

        FirstNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Filter.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Filter.setText("Filter");
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        Admin.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Admin.setText("User Is an Admin");
        Admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminActionPerformed(evt);
            }
        });

        Clear.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 3, 18)); // NOI18N
        Clear.setText("Clear All Filters");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jButton1.setText("Delete a User");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        DeleteAllLogs.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        DeleteAllLogs.setText("Delete All Logs");
        DeleteAllLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllLogsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ToDateText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(FromDateText, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(jLabel1))
                                            .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(258, 258, 258)
                                        .addComponent(Admin))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(197, 197, 197)
                                        .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 34, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(DeleteAllLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(510, 510, 510)
                        .addComponent(SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(SignOut, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(Home, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Welcome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FromDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ToDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(FirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(Admin)
                        .addGap(70, 70, 70)
                        .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteAllLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        MainPage homepage = new MainPage(UserName, Password);// creates a new instance of the MainPage using the Username and Password as parameters
        homepage.setVisible(true); // sets the Main Page frame to visible.
        this.setVisible(false); //sets this to jframe to invisible 
    }//GEN-LAST:event_HomeActionPerformed

    private void SignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out?", "Confirm", YES_NO_OPTION); // when sign out button is pressed ask the user to confirm
        if (input == 0) { // if the confirm == yes
            boolean success = LogTimeOut(UserName); // start the logout process
            if (!success) {
                JOptionPane.showMessageDialog(this, "We are unable log the time you signed out.");
            } else {
                LoginFrame home = new LoginFrame(); //new instance of the login Frame
                home.setVisible(true); // return to the login frame wher eyou can sign in
                this.setVisible(false); // make this frame invisible
            }
        } else if (input == 1) {

        }
    }//GEN-LAST:event_SignOutActionPerformed

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        // TODO add your handling code here:
        FromDate = jDateChooserFrom.getDate(); // retrieve the date from the date field
        ToDate = jDateChooserTo.getDate();
        Firstname = FirstNameField.getText(); // retireve the text from the text field
        if (jDateChooserFrom.getCalendar() != null && jDateChooserTo.getCalendar() != null && !FirstNameField.getText().trim().equals("") && isAdmin == true) {
            FilterLogDateNameAdmin(FromDate, ToDate, Firstname, isAdmin); // Filter the log using the dates selected, the name inputted and if they're an admin  
        } else if (jDateChooserFrom.getCalendar() == null && jDateChooserTo.getCalendar() == null && !FirstNameField.getText().trim().equals("") && isAdmin == true) {
            FilterLogNameAdmin(Firstname, isAdmin); // Filter the log using the name inputted and if they're an admin  
        } else if (jDateChooserFrom.getCalendar() != null && jDateChooserTo.getCalendar() != null && FirstNameField.getText().trim().equals("") && isAdmin == true) {
            FilterLogAdminDate(FromDate, ToDate, isAdmin); // Filter the log using the dates selected and if they're an admin  
        } else if (jDateChooserFrom.getCalendar() != null && jDateChooserTo.getCalendar() != null && !FirstNameField.getText().trim().equals("") && isAdmin == false) {
            FilterLogDateName(FromDate, ToDate, Firstname); // Filter the log using the dates selected and the name inputted  
        } else if (jDateChooserFrom.getCalendar() != null && jDateChooserTo.getCalendar() != null && FirstNameField.getText().trim().equals("") && isAdmin == false) {
            FilterLogDate(FromDate, ToDate); // Filter the log using the dates selected  
        } else if (jDateChooserFrom.getCalendar() == null && jDateChooserTo.getCalendar() == null && !FirstNameField.getText().trim().equals("") && isAdmin == false) {
            FilterLogName(Firstname); // Filter the log using the name inputted  
        } else if (jDateChooserFrom.getCalendar() == null && jDateChooserTo.getCalendar() == null && FirstNameField.getText().trim().equals("") && isAdmin == true) {
            FilterLogAdmin(isAdmin); // Filter the log checking if they're an admin  
        } else {
            addRowToJTable(); // reset the contents of the table
        }
    }//GEN-LAST:event_FilterActionPerformed

    private void AdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminActionPerformed
        // TODO add your handling code here:
        isAdmin = Admin.isSelected();// when checked, it iwill change the value of the variable
    }//GEN-LAST:event_AdminActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
        jDateChooserFrom.setCalendar(null); // when clear is pressed, it will reeset all the fields on the page
        jDateChooserTo.setCalendar(null);
        FirstNameField.setText("");
        Admin.setSelected(false);
        addRowToJTable(); // reset the log table 
    }//GEN-LAST:event_ClearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DeleteAUser delete = new DeleteAUser(UserName, Password); // when this button is clicked, it will create a new instance of the delete user page
        delete.setVisible(true); // set the delete page to visible
        this.setVisible(false); // set this page to invisble
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DeleteAllLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllLogsActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all the log information?","Clear Logs",JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            boolean verdict = users.DeleteLogs();
            if (verdict == true){
                JOptionPane.showMessageDialog(this, "You have successfully deleted all the logs.");
                addRowToJTable();
            } else {
                JOptionPane.showMessageDialog(this, "There was an error deleting all the logs.");
            }
        } else if (n == JOptionPane.NO_OPTION) {} 
    }//GEN-LAST:event_DeleteAllLogsActionPerformed

    public boolean CompareDate(Date DateFrom, Date DateTo) {
        Date date = new Date(); // Create the date for today
        if (DateFrom.compareTo(date) == 1 || DateTo.compareTo(date) == 1) { // If any of the days  selected are after today, return false
            return false;
        } else {
            if (DateFrom.compareTo(DateTo) <= 0) { // Date validation to check if the second date selected is before or after the first date
                return true; // if the dateFrom is before or is the same day as dateTo, then return true else return false
            } else {
                return false;
            }
        }
    }

    public static boolean LogTimeOut(String Username) { //Logout sequence
        User user = users.get(Username); // check if the user is a valid user
        if (user == null) {
            System.out.println("No user matches those details. \n");
            return false;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // create a new format for the date
        Last_Sign_Out = format1.format(new Date()); // create a new date format and set the format to the format above
        user.setLast_Sign_Out(Last_Sign_Out);
        boolean success = users.updateTimeOut(user); // this sequence will update the database and log the time the user logged out
        if (success) { // if success is true
            System.out.println("Modifictaion SUCCESSFUL Time out");
            return true; // logout was succcessful
        } else {
            System.err.println("Modifictaion FAILED Time out");
            return false; // logout wasn't successful
        }
    }

    public void FilterLogDate(Date FromDate, Date ToDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // create a new date format (year - month - date)
        String from = format.format(FromDate); //format a date to a string fusing the formatter for both dated 
        String to = format.format(ToDate);

        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel(); // create an editable model for the table
        model.setRowCount(0);// set the row count to 0
        ArrayList<User> list = users.FilterLog(from, to); // filter the products from the database using the dates
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < list.size(); i++) { // add the products to the table based on what wasfound from the query
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    } //Date

    public void FilterLogName(String name) { // filter the table by the name inputted
        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel();
        model.setRowCount(0); // set the row count to 0
        ArrayList<User> list = users.FilterLogFirstName(name); // filter the table using the name that was inputtted 
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < list.size(); i++) { // add the products of the filter to the table for the use rto see
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    } //Name

    public void FilterLogAdmin(boolean admin) {
        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel(); 
        model.setRowCount(0);// make the row count 0
        ArrayList<User> list = users.FilterLogAdmin(admin); // filter by if the user that signed in is a use or not
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < list.size(); i++) { // add the products to the table for the user to see in the JFrame
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    } //Admin

    public void FilterLogDateName(Date FromDate, Date ToDate, String name) { // filter by name and by the dates selected
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // create a new format for the dates
        String from = format.format(FromDate);
        String to = format.format(ToDate);// format the dates to strings using the formatter

        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel();
        model.setRowCount(0); // set the row count to 0
        ArrayList<User> list = users.FilterLogDateName(from, to, name);
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n"); // if the logs can't be found return this message
        } else {
            for (int i = 0; i < list.size(); i++) { // display the products in the tables for the user to see
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    } //Date + Name

    public void FilterLogNameAdmin(String name, boolean admin) {
        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel(); // filter using the name inputted and if they're an admin or not
        model.setRowCount(0);
        ArrayList<User> list = users.FilterLogNameAdmin(name, admin);// pass the name and the boolean through the method so select from the database
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n"); // if  the database is unable to return the logs
        } else {
            for (int i = 0; i < list.size(); i++) { // add the logs from the database to the table
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    } // Name + Admin

    public void FilterLogAdminDate(Date FromDate, Date ToDate, boolean admin) { // Filter using the dates selected and the boolean of if they're an admin or not
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // create a new format to format the date
        String from = format.format(FromDate);
        String to = format.format(ToDate); // format the date using the formatter

        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel();
        model.setRowCount(0);
        ArrayList<User> list = users.FilterLogDateAdmin(from, to, admin); // filter using the dates and if the users are amins through as parameters
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n"); // if there are no logs to show or returns an error
        } else {
            for (int i = 0; i < list.size(); i++) {// add the products of the method to the table
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    }

    public void FilterLogDateNameAdmin(Date FromDate, Date ToDate, String name, boolean admin) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String from = format.format(FromDate);
        String to = format.format(ToDate);

        DefaultTableModel model = (DefaultTableModel) AccessTable.getModel(); // create an editable table model to edit the table
        model.setRowCount(0); // set the row count to 0
        ArrayList<User> list = users.FilterLogDateNameAdmin(from, to, name, admin); // filter using the dates, name and the isAdmin boolean
        Object rowData[] = new Object[6];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < list.size(); i++) {
                rowData[0] = (i + 1);
                rowData[1] = list.get(i).getFirstName();
                rowData[2] = list.get(i).getSurname();
                rowData[3] = list.get(i).getType();
                rowData[4] = list.get(i).getLast_Sign_In();
                rowData[5] = list.get(i).getLast_Sign_Out();
                model.addRow(rowData);
            }
        }
    } // Date + Name + Admin

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
            java.util.logging.Logger.getLogger(AdminDateChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDateChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDateChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDateChecker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDateChecker().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AccessTable;
    private javax.swing.JCheckBox Admin;
    private javax.swing.JButton Clear;
    private javax.swing.JButton DeleteAllLogs;
    private javax.swing.JButton Filter;
    private javax.swing.JTextField FirstNameField;
    private javax.swing.JLabel FromDateText;
    private javax.swing.JButton Home;
    private javax.swing.JButton SignOut;
    private javax.swing.JLabel ToDateText;
    private javax.swing.JLabel Welcome;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
