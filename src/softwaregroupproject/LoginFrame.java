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
import javax.swing.JOptionPane;

/**
 *
 * @author David Ilunga
 */
public class LoginFrame extends javax.swing.JFrame {

    static String Username, Password, Last_Sign_In;
    String[] email;
    String domain; //  global variables taht will be used in the java class
    private static UserDatabase users = new UserDatabase();

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        ValidPassword.setText("Password must be 6 to 12 characters long.");
    }

    public LoginFrame(String Password) {
        initComponents();
        ValidPassword.setText("Password must be 6 to 12 characters long.");
        this.Password = Password;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Email = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        EmailField = new javax.swing.JTextField();
        ValidPassword = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ForgotPwd = new javax.swing.JButton();
        Register = new javax.swing.JButton();
        Quit = new javax.swing.JButton();
        LogIn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Tw Cen MT", 2, 36)); // NOI18N
        setForeground(java.awt.Color.white);

        Title.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setLabelFor(Title);
        Title.setText("Login: Registered Users");
        Title.setMaximumSize(new java.awt.Dimension(135, 15));
        Title.setMinimumSize(new java.awt.Dimension(135, 15));

        Email.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Email.setText("Please Enter a Valid Email Address:");

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Please Enter a Valid Password:");

        PasswordField.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        PasswordField.setNextFocusableComponent(LogIn);
        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });

        EmailField.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
        EmailField.setNextFocusableComponent(PasswordField);

        ValidPassword.setText("Please ensure that your password is between 6 and 12 characters long");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addComponent(ValidPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                        .addComponent(EmailField))
                    .addGap(0, 16, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(ValidPassword)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(46, 46, 46)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(71, Short.MAX_VALUE)))
        );

        ForgotPwd.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        ForgotPwd.setText("Forgot Password");
        ForgotPwd.setNextFocusableComponent(EmailField);
        ForgotPwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForgotPwdActionPerformed(evt);
            }
        });

        Register.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Register.setText("Register");
        Register.setNextFocusableComponent(Quit);
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });

        Quit.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Quit.setText("Quit");
        Quit.setNextFocusableComponent(ForgotPwd);
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitActionPerformed(evt);
            }
        });

        LogIn.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        LogIn.setText("Log In");
        LogIn.setNextFocusableComponent(Register);
        LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Quit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(ForgotPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ForgotPwd, LogIn, Quit, Register});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ForgotPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ForgotPwd, LogIn, Quit, Register});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(237, 237, 237))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(300, 300, 300))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        // TODO add your handling code here:
        SignUpFrame signUp = new SignUpFrame(); // When the register button is pressed, start a new instance of the Sign Up page
        signUp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegisterActionPerformed

    private void ForgotPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForgotPwdActionPerformed
        // TODO add your handling code here:
        ForgotPassword reset = new ForgotPassword(); // When the forgot password button is pressed, start a new instance of the Forgot Password page
        reset.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ForgotPwdActionPerformed

    private void QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitActionPerformed
        // TODO add your handling code here:
        System.exit(0); // When the quit button is clicked, it will exit the system
    }//GEN-LAST:event_QuitActionPerformed

    private void LogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogInActionPerformed
        // TODO add your handling code here:
        Username = EmailField.getText();
        Password = PasswordField.getText();
        if (Username.equals("") || Password.equals("")) { // This will check if any of the fields are empty
            JOptionPane.showMessageDialog(this, "Please make sure that all required field are filled in.");
        } else if (!Username.contains("@")) { // This will check if the user email contains the '@' symbol
            JOptionPane.showMessageDialog(this, "This is an invalid email. Please try again.");
            PasswordField.setText("");
        } else if (Username.contains("@")) { // if it does contain the '@' sign
            try {
                email = Username.split("@");
                domain = email[1];
                if (Username.contains("@") && (domain.contains(".com") || domain.contains(".co.uk") || domain.contains(".ac.uk") || domain.contains(".org") || domain.contains(".gov") || domain.contains(".edu") || domain.contains(".net"))) {
                    // if the domain contains any of the endings above then it will continue
                    boolean isValid = checkValidUser(Username);

                    if (!isValid) { // if the user isn't a valid user return the  message below
                        JOptionPane.showMessageDialog(this, "The email account is not a registered email account. Please use a different email account.");
                        PasswordField.setText("");
                    } else {
                        if (Password.length() < 6 || Password.length() > 12) { // check teh password length to see if it is between 6 anbd 12 chasracters long
                            JOptionPane.showMessageDialog(this, "Please ensure that your password is the correct length.");
                            ValidPassword.setText("Please ensure that your password is between 6 and 12 characters long.");
                            ValidPassword.setForeground(Color.RED); // it password is incorrect, turn the text red and clear the field
                            PasswordField.setText("");
                        } else {
                            boolean emailCheck = CheckEmail(Username); // check that the email inputted is a valid email
                            if (!emailCheck){ // if email is not valid
                                JOptionPane.showMessageDialog(this, "This email is not valid, please try again.");
                                PasswordField.setText("");
                            } else {
                                boolean pass = CheckPassword(Username, Password); // it the email is valid, check the password
                                if (!pass) {// if the password inputted idn't correct
                                    JOptionPane.showMessageDialog(this, "The password you have entered is not valid, please try again.");
                                    PasswordField.setText("");
                                } else {
                                    boolean checkHash = CheckUserHash(Username, Password); // this will check the password hash that is stored in the database
                                    if (!checkHash){ // if it doesn't match return this error
                                        JOptionPane.showMessageDialog(this, "The password you have entered is incorrect, please try again.");
                                    } else {
                                        boolean success = LogTimeIn(Username); // if everything is correct then it wo log the sign in time
                                        if (success) {
                                            JOptionPane.showMessageDialog(this, "You have successfully signed in.");
                                            MainPage home = new MainPage(Username,Password); // create a new instance of the main page
                                            home.setVisible(true);
                                            this.setVisible(false);
                                        }
                                    }
                                }
                            }

                        }
                    }
                } else { // If any errors occur or the statements return false, they will be caught
                    JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
                    PasswordField.setText("");
                }
            } catch (Throwable e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this, "There is an error with your details. Please try again.");
                PasswordField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
            PasswordField.setText("");
        }
    }//GEN-LAST:event_LogInActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    public static String getUserName() {
        return Username;
    }

    public static String getPassword() {
        return Password;
    }

    public static boolean LogTimeIn(String Username) { // Log the time that a user Signs in
        User user = users.get(Username); // checks if the user is a vbalid user 
        if (user == null) {
            System.out.println("No user matches those details. \n");
            return false;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // creates a new date format to save the log in time
        Last_Sign_In = format1.format(new Date());
        user.setLast_Sign_In(Last_Sign_In);
        boolean success = users.updateTimeIn(user); // updates the log in time in the database
        if (success) {
            System.out.println("Modifictaion SUCCESSFUL Time in"); // Lets the console know if the update was successful or not
            return true;
        } else {
            System.err.println("Modifictaion FAILED Time in");
            return false;
        }
    }

    private static boolean CheckPassword(String Username, String Password) { // This will check if the password is correct or not for the user to continue with the login process
        User user = users.get(Username); // checks if the user is a valid user
        if (user == null) {
            System.out.println("No user matches those details. \n");
        }
        return Password.matches(user.getPassword()); // retuurns the value of if the password is the same
    }

    private boolean checkValidUser(String Username) { // Checks if the user is a valid user
        ArrayList<User> list = users.CheckValidUsers();
        for (int i = 0; i < list.size(); i++) {
            if (Username.matches(list.get(i).getEmail())) { // is the email exists in the database, then it returns true otherwise it returns false
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JTextField EmailField;
    private javax.swing.JButton ForgotPwd;
    private javax.swing.JButton LogIn;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton Quit;
    private javax.swing.JButton Register;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel ValidPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    private boolean CheckEmail(String Username) { // checks if the email is a valid email in the database
        User user = users.get(Username); // if the user is a valid user
        if (user == null) {
            System.out.println("No user matches those details. \n");
        }
        return Username.matches(user.getEmail()); // retuurns the value of if the email is the same 
    }

    private boolean CheckUserHash(String Username, String Password) { // checks if the hash of the passowrd that was entered matches the hash saved in the database
        User user = users.get(Username);
        if (user == null) {
            System.out.println("No user matches those details. \n");
        }
        User person = users.checkHashCode(user);
        boolean valid = HashProject.verifyUserPassword(Password, person.getHashedPassword(), person.getSaltCode());  
        return valid; // return true is it matches, false if it doesn't
    }

}