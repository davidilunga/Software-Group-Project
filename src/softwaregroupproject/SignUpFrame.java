
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import javax.swing.JOptionPane;
import java.awt.Color;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author David Ilunga
 */
public class SignUpFrame extends javax.swing.JFrame {

    static String Username, Password, ConfirmPassword, Firstname, SurName, Gender, Type, Date_of_Birth, Day_Created, Last_Sign_In, Last_Sign_Out;
    static Date DoB; //  global variables used throughout the project
    static boolean isAdmin = false;
    static String regex = "^[a-zA-Z\\s]+$"; // regular expression used to validate inouts by the user
    String[] email;
    String domain;
    private static UserDatabase users = new UserDatabase();

    /**
     * Creates new form LoginFrame
     */
    public SignUpFrame() { // Sign Up Frame constructor
        initComponents();
        ValidPassword.setText("Password must be 6 to 12 characters long.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ValidPassword = new javax.swing.JLabel();
        uName = new javax.swing.JTextField();
        AccountPwd = new javax.swing.JPasswordField();
        AccountPwdConfirmation = new javax.swing.JPasswordField();
        CreateAccount = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        FirstName = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        Surname = new javax.swing.JLabel();
        SurnameField = new javax.swing.JTextField();
        Title = new javax.swing.JLabel();
        DoBDateChooser = new com.toedter.calendar.JDateChooser();
        DOB = new javax.swing.JLabel();
        GenderSelection = new javax.swing.JComboBox<>();
        PasswordConfirmation = new javax.swing.JLabel();
        ValidPassword1 = new javax.swing.JLabel();
        GenderTitle = new javax.swing.JLabel();
        EmployeeType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Please Enter an Email:");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Please Enter a Password:");

        ValidPassword.setFont(new java.awt.Font("Tw Cen MT", 0, 13)); // NOI18N
        ValidPassword.setText("Please ensre that your password is between 6 and 12 characters long");

        uName.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        uName.setNextFocusableComponent(AccountPwd);
        uName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uNameActionPerformed(evt);
            }
        });

        AccountPwd.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        AccountPwd.setNextFocusableComponent(AccountPwdConfirmation);
        AccountPwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountPwdActionPerformed(evt);
            }
        });

        AccountPwdConfirmation.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        AccountPwdConfirmation.setNextFocusableComponent(firstNameField);
        AccountPwdConfirmation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountPwdConfirmationActionPerformed(evt);
            }
        });

        CreateAccount.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        CreateAccount.setText("Create Account");
        CreateAccount.setNextFocusableComponent(Cancel);
        CreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAccountActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Cancel.setText("Cancel");
        Cancel.setNextFocusableComponent(uName);
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("What type of user are you registering?");

        FirstName.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        FirstName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FirstName.setText("Please Enter your First name:");

        firstNameField.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        firstNameField.setNextFocusableComponent(SurnameField);
        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        Surname.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Surname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Surname.setText("Please Enter your Surname:");

        SurnameField.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        SurnameField.setNextFocusableComponent(DoBDateChooser);
        SurnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SurnameFieldActionPerformed(evt);
            }
        });

        Title.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        Title.setText("Create An Account");

        DoBDateChooser.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        DoBDateChooser.setNextFocusableComponent(GenderSelection);

        DOB.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        DOB.setText("Date Of Birth:");

        GenderSelection.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        GenderSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        GenderSelection.setName("GenderSelection"); // NOI18N
        GenderSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderSelectionActionPerformed(evt);
            }
        });

        PasswordConfirmation.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        PasswordConfirmation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PasswordConfirmation.setText("Please confirm your Password:");

        ValidPassword1.setFont(new java.awt.Font("Tw Cen MT", 0, 13)); // NOI18N
        ValidPassword1.setText("Please ensre that your password is between 6 and 12 characters long");

        GenderTitle.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        GenderTitle.setText("Gender:");

        EmployeeType.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        EmployeeType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee", "Admin" }));
        EmployeeType.setNextFocusableComponent(CreateAccount);
        EmployeeType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(365, 365, 365)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(uName)
                                .addComponent(AccountPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ValidPassword))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(397, 397, 397)
                                .addComponent(EmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AccountPwdConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ValidPassword1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Title)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DOB, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .addComponent(GenderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DoBDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(GenderSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Surname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SurnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(firstNameField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(FirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(75, 75, 75)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Title))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SurnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DoBDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GenderSelection, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addComponent(GenderTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AccountPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ValidPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AccountPwdConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ValidPassword1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void uNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uNameActionPerformed

    private void AccountPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountPwdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountPwdActionPerformed

    private void CreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAccountActionPerformed
        // TODO add your handling code here:
        Username = uName.getText(); // when the Create account button is pressed
        Password = AccountPwd.getText();
        DoB = DoBDateChooser.getDate();
        Gender = GenderSelection.getSelectedItem().toString(); // assigning the value to all the variables that were defined
        Type = EmployeeType.getSelectedItem().toString();
        ConfirmPassword = AccountPwdConfirmation.getText();
        Firstname = CheckName(firstNameField.getText()); // this will check that the name and surname is valid as soon as the button is pressed
        SurName = CheckName(SurnameField.getText());

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy"); // creae a new date format to save the user's Date of Birth
        Date_of_Birth = format.format(DoB);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); // create a new data format to save the date and time the account was created to the database
        Day_Created = format1.format(new Date());
        Last_Sign_In = format1.format(new Date()); // register a new date (one for each variable)
        Last_Sign_Out = format1.format(new Date());

        if (Username.equals("") || Password.equals("") || ConfirmPassword.equals("")) { // if the fields are empty
            JOptionPane.showMessageDialog(this, "Please make sure that all required field are filled in.");
        } else if (!Username.contains("@")) { // if the Username textfield doesn't contain '@' it will return the message below
            JOptionPane.showMessageDialog(this, "This is an invalid email. Please try again.");
        } else if (Username.contains("@")) { // if it does contaan the '@' it will continue
            try {
                email = Username.split("@");
                domain = email[1]; // it will split the text by the '@' sign 
                if (Username.contains("@") && (domain.contains(".com") || domain.contains(".co.uk") || domain.contains(".ac.uk") || domain.contains(".org") || domain.contains(".gov") || domain.contains(".edu") || domain.contains(".net"))) {
                    // if the domain that is entered comtains any of the following endings, it would continue
                    if (Password.length() < 6 || Password.length() > 12 || (ConfirmPassword.length() < 6 || ConfirmPassword.length() > 12)) {
                        // This checks the length of the password, to ensure that it's the correct length
                        JOptionPane.showMessageDialog(this, "Please ensure that your password is the correct length.");
                        ValidPassword.setText("Please ensure that your password is between 6 and 12 characters long.");
                        ValidPassword.setForeground(Color.RED);
                    } else if (!ConfirmPassword.matches(Password)) { // This checks that the new password field and Confirm password field match
                        JOptionPane.showMessageDialog(this, "Please double check that your passwords match.");
                    } else if (!checkDate(DoB)) {
                        JOptionPane.showMessageDialog(this, "Please ensure thate your Date of Birth is correct. You must be at least 18 years old to create an account.");
                    } else if (Firstname.equals("invalid")) { // if the first name and/or the surname equals "invalid" return an error
                        if (Firstname.equals("invalid") && SurName.equals("invalid")) {
                            JOptionPane.showMessageDialog(this, "Please ensure that you enter a valid first name and surname.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Please ensure that you enter a valid first name .");
                        }
                    } else if (SurName.equals("invalid")) {
                        JOptionPane.showMessageDialog(this, "Please ensure that you enter a valid surname .");
                    } else {
                        boolean isValid = checkValidUser(Username); // check that the user is a valid user

                        if (isValid) { // if the email is found, return a message to tell the user that it is already found
                            JOptionPane.showMessageDialog(this, "The email account that you have entered is already in use. Please use a different email account.");
                        } else {
                            JOptionPane.showMessageDialog(this, "All entered fields are correct. Please continue.");
                            newUser(); // savve the new user using the details that were inputted

                            LoginFrame login = new LoginFrame(); // take the user back to the login frame
                            login.setVisible(true);
                            this.setVisible(false);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
                }
            } catch (Throwable e) {
                JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
        }
    }//GEN-LAST:event_CreateAccountActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        LoginFrame login = new LoginFrame(); // if the cancel button is pressed, take the suer back to the login page
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CancelActionPerformed

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void SurnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SurnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SurnameFieldActionPerformed

    private void AccountPwdConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountPwdConfirmationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountPwdConfirmationActionPerformed

    private void GenderSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderSelectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderSelectionActionPerformed

    private void EmployeeTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmployeeTypeActionPerformed

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
            java.util.logging.Logger.getLogger(SignUpFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpFrame().setVisible(true);
            }
        });
    }

    public static String getUsername() {
        return Username;
    }

    public static String getPassword() {
        return Password;
    }

    private static String CheckName(String name) { // this is the name validator using Regular Expression

        while (!"".equals(name)) {
            if (name.matches(regex)) { // Checks to see if the name contains any of the characters registered in the global variable regex ("^[a-zA-Z\\s]+$")
                return name;
            } else {
                name = "invalid";
            }// while it isn't the correct form of a name, it will continue to ask until it is valid to pass.
        }
        return name;
    }

    public static void newUser() {
        User user = new User(Username, Password, Firstname, SurName, Gender, Type, Date_of_Birth, Day_Created, Last_Sign_In, Last_Sign_Out); 
        //Creates a new user using the User class
        boolean success = users.add(user); // if successful, return message 1 else message 2.
        if (success) {
            System.out.println(Firstname + " has been added to the database.\n");
        } else {
            System.out.println("Error! Unable to add product.\n");
        }
    }

    public static boolean checkDate(Date DoB) { // check that the date is a valid date.
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new Date();
        if (DoB.compareTo(date) == 1) { // check the date compared to today. If after today, ten return false
            return false;
        } else {
            try {
                String[] today = format.format(date).split("-");
                int TodayYear = parseInt(today[2]);
                String beforeYear = String.valueOf(TodayYear - 18);//this will check that the user is at least 18 years old when putting in their age

                Date BeforeDate = format.parse(today[0] + "-" + today[1] + "-" + beforeYear);

                return DoB.compareTo(BeforeDate) <= 0; // return the vaue of if the date is before or the day of the value of beforeDate
            } catch (ParseException e) { // Catch any exception that cna be found
                return false;
            }
        }
    }

    private boolean checkValidUser(String Username) { // check that the user is a valid user 
        ArrayList<User> list = users.CheckValidUsers();
        for (int i = 0; i < list.size(); i++) {
            if (Username.matches(list.get(i).getEmail())) { // if the user's email exists in the database
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField AccountPwd;
    private javax.swing.JPasswordField AccountPwdConfirmation;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton CreateAccount;
    private javax.swing.JLabel DOB;
    private com.toedter.calendar.JDateChooser DoBDateChooser;
    private javax.swing.JComboBox<String> EmployeeType;
    private javax.swing.JLabel FirstName;
    private javax.swing.JComboBox<String> GenderSelection;
    private javax.swing.JLabel GenderTitle;
    private javax.swing.JLabel PasswordConfirmation;
    private javax.swing.JLabel Surname;
    private javax.swing.JTextField SurnameField;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel ValidPassword;
    private javax.swing.JLabel ValidPassword1;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField uName;
    // End of variables declaration//GEN-END:variables

}
