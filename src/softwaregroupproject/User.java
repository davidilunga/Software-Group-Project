/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

/**
 *
 * @author David Ilunga
 */
public class User {
    private String Email, Password, FirstName, Surname, Gender, HashedPassword, SaltCode;
    private String DoB, Day_Created,Last_Sign_In,Last_Sign_Out;
    private boolean Type;
    private int UserId;
    
    public User(){} // Empty constructor to create a new instance

    public User(String Email, String Password, String FirstName, String Surname, String Gender, String Type, String DoB, String Day_Created,String Last_Sign_In, String Last_Sign_Out) { // Constructor to create a new instance
        this.Email = Email;
        this.Password = Password;
        this.FirstName = FirstName; // Assigning the parameters to the global variables
        this.Surname = Surname;
        this.Gender = Gender;
        this.Type = Type.equals("Admin");
        this.DoB = DoB;
        this.Day_Created = Day_Created;
        this.Last_Sign_In = Last_Sign_In;
        this.Last_Sign_Out = Last_Sign_Out;
    }

    public User(String FirstName, String SurName, String Type, String Last_Sign_In, String Last_Sign_Out) { // Constructor to create a new instance
        
        this.FirstName = FirstName;
        this.Surname = SurName;
        this.Type = Type.equals("Admin");   // Assigning the parameters to the global variables from the constructor
        this.Last_Sign_In = Last_Sign_In;
        this.Last_Sign_Out = Last_Sign_Out;
    }

    public User(String Email, String Password, String HashedPassword, String SaltCode) { // Constructor to create a new instance
        this.Email = Email;
        this.Password = Password;
        this.HashedPassword = HashedPassword; // Assigning the parameters to the global variables from the constructor
        this.SaltCode = SaltCode;
    }

    public int getUserId() {
        return UserId;  // get the UserId
    }

    public void setUserId(int UserId) {
        this.UserId = UserId; // set the UserId
    }

    public String getEmail() {
        return Email; // set the Email
    }

    public void setEmail(String Email) {
        this.Email = Email; // set the Email
    }

    public String getPassword() {
        return Password; // get the Password
    }

    public void setPassword(String Password) {
        this.Password = Password; // set the Password
    }

    public String getFirstName() {
        return FirstName; // get the First name
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName; // set the First name
    }

    public String getSurname() {
        return Surname; // get the Surname
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;  // set the Surname
    }

    public String getGender() {
        return Gender; // get the gender
    }

    public void setGender(String Gender) {
        this.Gender = Gender;  // set the gender
    }

    public boolean getType() {
        return Type;  // get the type of account
    }

    public void setType(String Type) {
        this.Type = Type.equals("Admin");  // set the type of account
    }

    public String getDoB() {
        return DoB; // get the DOB
    }

    public void setDoB(String DoB) {
        this.DoB = DoB;  // set the DOB
    }

    public String getLast_Sign_In() {
        return Last_Sign_In; // get the last sign in time
    }

    public void setLast_Sign_In(String Last_Sign_In) {
        this.Last_Sign_In = Last_Sign_In;  // set the last sign in time
    }

    public String getLast_Sign_Out() {
        return Last_Sign_Out; // get the last sign out time
    }

    public void setLast_Sign_Out(String Last_Sign_Out) {
        this.Last_Sign_Out = Last_Sign_Out;  // set the last sign out time
    }

    public String getDay_Created() {
        return Day_Created; // get the day the account was created
    }

    public void setDay_Created(String Day_Created) {
        this.Day_Created = Day_Created;  // set the day the account was created
    }

    public String getHashedPassword() {
        return HashedPassword; // get the hashed password
    }

    public void setHashedPassword(String HashedPassword) {
        this.HashedPassword = HashedPassword;  // set the hashed password
    }

    public String getSaltCode() {
        return SaltCode; // get the saltcode
    }

    public void setSaltCode(String SaltCode) {
        this.SaltCode = SaltCode;  // set the saltcode
    }
    
}
