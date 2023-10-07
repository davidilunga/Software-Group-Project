/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author David Ilunga
 */
public class ForgotPassword extends javax.swing.JFrame {

    String Username, newPassword, domain;
    String ConfirmPassword; // Create global variable that will be reused later in the java class
    static String[] email;
    private static UserDatabase users = new UserDatabase(); // create a new instance of the user database

    /**
     * Creates new form ForgotPassword
     */
    public ForgotPassword() {
        initComponents();
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
        Back = new javax.swing.JButton();
        Confirm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Email = new javax.swing.JLabel();
        Email1 = new javax.swing.JLabel();
        Email2 = new javax.swing.JLabel();
        ConfirmPasswordField = new javax.swing.JPasswordField();
        PasswordField = new javax.swing.JPasswordField();
        emailField = new javax.swing.JTextField();
        ValidPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Forgot Password");
        setBackground(new java.awt.Color(255, 255, 255));

        Title.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Forgot Password");

        Back.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Back.setText("Back");
        Back.setNextFocusableComponent(Confirm);
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Confirm.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Confirm.setText("Confirm");
        Confirm.setNextFocusableComponent(emailField);
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        Email.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Email.setText("Please Enter a Valid Email Address:");

        Email1.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Email1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Email1.setText("Please Enter a new Password:");

        Email2.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Email2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Email2.setText("Please confirm your new Password:");

        ConfirmPasswordField.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        ConfirmPasswordField.setNextFocusableComponent(Back);

        PasswordField.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        PasswordField.setNextFocusableComponent(ConfirmPasswordField);

        emailField.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        emailField.setNextFocusableComponent(PasswordField);

        ValidPassword.setText("Please ensure that your password is between 6 and 12 characters long");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ValidPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Email2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addComponent(Email1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailField)
                            .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                            .addComponent(PasswordField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(ValidPassword)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        // TODO add your handling code here:
        Username = emailField.getText();
        newPassword = PasswordField.getText();
        ConfirmPassword = ConfirmPasswordField.getText();
        if (ConfirmPassword.equals("") || newPassword.equals("")) { // check to see if the textfield is blank
            JOptionPane.showMessageDialog(this, "Please check to make sure that the passwords you have entered match."); // if it's blank, return this message
        } else if (!Username.contains("@")) { // if the Username textfield doesn't contain '@' it will return the message below
            JOptionPane.showMessageDialog(this, "This is an invalid email. Please try again.");
        } else if (Username.contains("@")) { // if it does contaan the '@' it will continue
            try {
                email = Username.split("@");
                domain = email[1]; // it will split the text by the '@' sign 
                if (Username.contains("@") && (domain.contains(".com") || domain.contains(".co.uk") || domain.contains(".ac.uk") || domain.contains(".org") || domain.contains(".gov") || domain.contains(".edu") || domain.contains(".net"))) {
                    // if the domain that is entered comtains any of the following endings, it would continue
                    if ((newPassword.length() < 6 || newPassword.length() > 12) || (ConfirmPassword.length() < 6 || ConfirmPassword.length() > 12)) {
                        // This checks the length of the password, to ensure that it's the correct length
                        JOptionPane.showMessageDialog(this, "Please ensure that your password is the correct length.");
                        ValidPassword.setText("Please ensure that your password is between 6 and 12 characters long.");
                        ValidPassword.setForeground(Color.RED);
                    } else if (!ConfirmPassword.matches(newPassword)) { // This checks that the new password field and Confirm password field match
                        JOptionPane.showMessageDialog(this, "Please check to make sure that the passwords you have entered match.");
                    } else {
                        boolean success = updatePassword(Username, newPassword); // if everyting is correct, it will start the update password method
                        if (!success) {
                            JOptionPane.showMessageDialog(this, "You are unable to change your password at this time. Please try again later."); // if it was unable to update, it will return this message
                        } else {
                            JOptionPane.showMessageDialog(this, "You have successfully changed your password. Please sign in.");
                            // If everything is successful, it will return this message
                            LoginFrame login = new LoginFrame(); // this will create a instance of the login page
                            login.setVisible(true);
                            this.setVisible(false); // this will take the user back to the login page if successful
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
                }
            } catch (Throwable e) {
                JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again."); // if not successful or an error occcurs, it will return an error message
            }   
        } else {
            JOptionPane.showMessageDialog(this, "This is an invalid domain. Please try again.");
        }
    }//GEN-LAST:event_ConfirmActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        LoginFrame login = new LoginFrame(); //  when the back button is pressed, it will go back to the login page without updating the any passwords 
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BackActionPerformed

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
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ForgotPassword().setVisible(true);
            }
        });
    }

    public static boolean updatePassword(String Email, String Password) { // update password method
        User user = users.get(Email); // this will check if the user email is a valid email, corresponding to an actual user
        if (user == null) {
            System.out.println("No user matches those details. \n"); // if no use rin not found
            return false;
        }

        user.setPassword(Password);
        boolean success = users.updatePassword(user);// this will pass the user as a variable
        if (success) {
            System.out.println("Modifictaion SUCCESSFUL Password Update");
            return true; // if the update password was successful then return true else reutrn false.
        } else {
            System.err.println("Modifictaion FAILED Password Update");
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Confirm;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Email1;
    private javax.swing.JLabel Email2;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel ValidPassword;
    private javax.swing.JTextField emailField;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}