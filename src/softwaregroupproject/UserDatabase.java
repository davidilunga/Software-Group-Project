/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David Ilunga
 */
public class UserDatabase {

    private Connection connection;

    public UserDatabase() {
        try {
            String url = "jdbc:sqlite:UserData.sqlite"; // Establish a connection to the database called UserData in the folder
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to User database SUCCESS"); // if connection is successful
        } catch (Exception e) {
            System.err.println("Connection to User database FAILED"); // if connection is not successful
        }
    }

    public void CloseConnection() {
        try {
            if (connection != null) { //  end the connect whe finished
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private User getProductFromRow(ResultSet rs) throws SQLException { // retrieve all the data from the database when a query is executed
        String Username = rs.getString(1);
        String Password = rs.getString(2);
        String Firstname = rs.getString(3);
        String SurName = rs.getString(4);
        String Gender = rs.getString(5);
        boolean type = rs.getBoolean(6);        // sets the variables to the values of the string that was found in each column
        String Date_of_Birth = rs.getString(7);
        String Day_Created = rs.getString(8);
        String Last_Sign_In = rs.getString(9);
        String Last_Sign_Out = rs.getString(10);

        String Type;

        if (type == true) { // sets the value from 1 and 0 to a string
            Type = "Admin";
        } else {
            Type = "Employee";
        }

        User user = new User(Username, Password, Firstname, SurName, Gender, Type, Date_of_Birth, Day_Created,Last_Sign_In, Last_Sign_Out); 
        //create a nuew user once data is all collected for the row
        return user; // return user
    }

    private User getProductFromRowLog(ResultSet rs) throws SQLException { // get all the values for each row in the log table
        String Firstname = rs.getString(1);
        String SurName = rs.getString(2);
        boolean type = rs.getBoolean(3); // save each row value in a variable
        String Last_Sign_In = rs.getString(4);
        String Last_Sign_Out = rs.getString(5);

        String Type;

        if (type == true) {
            Type = "Admin"; // sets the value from 1 and 0 to a string
        } else {
            Type = "Employee";
        }

        User user = new User(Firstname, SurName, Type, Last_Sign_In, Last_Sign_Out); // adds a new user to the registry for the user log
        return user; // return the user
    }
    
    private User getProductFromRowPasswords(ResultSet rs) throws SQLException { // return all the password information that was saved from the database
        String Email = rs.getString(1);
        String Password = rs.getString(2);
        String HashedPassword = rs.getString(3);
        String SaltCode = rs.getString(4);

        User user = new User(Email, Password, HashedPassword, SaltCode); // save it to the user information for it to be reused 
        return user; // return the user info
    }

    public boolean add(User user) { // add the new user to the database
        String sql = "INSERT INTO UserInfo (UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());       // set the values to the values stored in the user functions
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getGender());
            ps.setBoolean(6, user.getType());
            ps.setString(7, user.getDoB());
            ps.setString(8, user.getDay_Created());
            ps.setString(9, user.getLast_Sign_In());
            ps.setString(10, user.getLast_Sign_Out());
            ps.executeUpdate();
            BeginHash(user); // begin the password hash process
            boolean hash = hashPassword(user); // add the hashed password information to that Password table in the database
            return hash; // return the result of that method
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean hashPassword(User user){ // Add the hashing information into the Password table
        String sql = "INSERT INTO PasswordStorage (UserEmail, UserPassword, HashedPassword, SaltCode)"
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getEmail()); // Assign the values to the the methods that saved the password information
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getHashedPassword());
            ps.setString(4, user.getSaltCode());
            ps.executeUpdate();
            return true; // return value
        } catch (SQLException e) { // catch any exceptions that may occur  during this process
            System.err.println(e);
            return false;
        }
    }
    
    private void BeginHash(User user) { // generate a hassed password using the Hash project class
        String slt = HashProject.getSalt(30); // set the salt size to 30 and generate a random salt code to scramble the password
        user.setSaltCode(slt); // assign the salt code to the user
        String password = HashProject.generateSecurePassword(user.getPassword(),slt); // create the hashed password for th user, using the salt that was generated
        user.setHashedPassword(password); // set the user's hashed password to the value returned from the method
    }
    
    public User get(String code) { // get everything fromt the userInfo table where it matches the User email
        String sql = "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out "
                + "FROM UserInfo "
                + "WHERE UserEmail = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code); // set the email to the the value stored in the variable
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = getProductFromRow(rs); // for each value found, store it in the User class
                rs.close();
                return user; //return the user
            } else {
                rs.close();
                return null;// if not found, close the query and return null
            }
        } catch (SQLException e) { // catch an exception if one is found
            System.err.println(e);
            return null;
        }
    }
    
    public User getFirstName(String code) { //  get the user info from the Table
        String sql = "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out "
                + "FROM UserInfo "
                + "WHERE FirstName = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code); // set the user's first name to the value stored in this variable
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = getProductFromRow(rs); // for each value, create a new user using the User class
                rs.close();
                return user; // return the User
            } else {
                rs.close(); // if not found, close the query and return null
                return null;
            }
        } catch (SQLException e) { // catch an error if one occurs
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<User> getAll() { // get all the values stored in the database
        String sql = "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out FROM UserInfo ";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = getProductFromRow(rs); // for each row found, create a new User entry using the User class
                users.add(u); // add the newly created user (u) to thge users ArrayList
            }
            return users; // return the users ArrayList 
        } catch (SQLException e) { // catch any exception if it is found
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<User> getAllDeleteTable(String UserEmail, String UserFirstName, String UserSurname) {
        String sql = "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out FROM UserInfo EXCEPT\n" +
        "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out FROM UserInfo WHERE UserEmail = '"+UserEmail+"' AND FirstName = '"+UserFirstName+"' AND Surname = '"+UserSurname+"';";
        // Select the table except the row where the the email, first name and surname are equal to the information that was used to sign in
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = getProductFromRow(rs); // for each row found, it will create a new User
                users.add(u); // it will add User (u) to the users ArrayList
            }
            return users; // return users ArrayList
        } catch (SQLException e) { // Catch any exception that might occur
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<User> getAllUserLog() { //  display all the user logs
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog ORDER BY LastSignIn DESC;";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users; // return the ArrayList users
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public boolean updateTimeIn(User user) {
        String sql = "UPDATE UserInfo SET "
                + "Last_Sign_In = ? "
                + "WHERE UserEmail = ?"; // update the database where it matches the the column UserEmail
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getLast_Sign_In()); // use the values stored in the methods
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            boolean logAdded = addLog(user); //call the method addLog() to add to the log table data
            return logAdded; //return the value of the log method
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }

    public boolean updateTimeOut(User user) {
        String sql = "UPDATE UserInfo SET Last_Sign_In = ? AND Last_Sign_Out = ? WHERE UserEmail = ?"; // update the database where it matches the the column UserEmail
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getLast_Sign_In()); // use the values stored in the methods
            ps.setString(2, user.getLast_Sign_Out());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            boolean logAdded = addLogIn(user); //call the method addLog() to add to the log table data
            return logAdded; //return the value of the log method
        } catch (SQLException e) {  // Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }

    public boolean addLogIn(User user) {
        String sql = "UPDATE UserLog SET LastSignOut = ? WHERE LastSignIn = ? AND FirstName = ? AND Surname = ?"; // update the database where it matches the the column Firstname and Surname
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getLast_Sign_Out());
            ps.setString(2, user.getLast_Sign_In()); // use the values stored in the methods
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getSurname());
            ps.executeUpdate(); // execute query
            return true;
        } catch (SQLException e) {// Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }

    public boolean addLog(User user) {
        String sql = "INSERT INTO UserLog (FirstName, Surname, Admin, LastSignIn) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getSurname()); // use the values stored in the methods
            ps.setBoolean(3, user.getType());
            ps.setString(4, user.getLast_Sign_In());
            ps.executeUpdate(); // execute query
            return true;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }

    public boolean updatePassword(User user) {
        String sql = "UPDATE UserInfo SET UserPassword = ? WHERE UserEmail = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail()); // use the values stored in the methods
            ps.executeUpdate();
            BeginHash(user); // begin the hash generation
            boolean hash =  UpdatehashPassword(user); // execute the hash sqeuence
            return hash;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e); 
            return false;
        }
    }
    
    private boolean UpdatehashPassword(User user) {
        String sql = "UPDATE PasswordStorage SET UserPassword = '"+user.getPassword()+"', HashedPassword = '"+user.getHashedPassword()+"', SaltCode = '"+user.getSaltCode()+"' WHERE UserEmail = '"+user.getEmail()+"';";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            return true; // return value
        } catch (SQLException e) { // catch any exceptions that may occur  during this process
            System.err.println(e);
            return false;
        }
    }
    
    public boolean deleteUser(String fName, String sName, String email) {
        String sql = "DELETE FROM UserInfo WHERE UserEmail = ? AND FirstName = ? AND Surname = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, fName); // use the values stored in the methods
            ps.setString(3, sName);
            ps.executeUpdate(); // execute query
            return deletePassword(email);
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }
    
    public boolean deletePassword(String email){
        String sql = "DELETE FROM PasswordStorage WHERE UserEmail = ? "; // Delete from the table where it matches UserEmail
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email); // use the values stored in the methods
            ps.executeUpdate(); // execute query
            return true;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }
    
    public ArrayList<User> CheckValidUsers() {
        String sql = "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out FROM UserInfo";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRow(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<User> CheckUsername(User user){
        String sql = "SELECT UserEmail, UserPassword, FirstName, Surname, Gender, UserAdmin, DoB, Day_Created, Last_Sign_In, Last_Sign_Out FROM UserInfo";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRow(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<User> FilterLog(String from, String to) {
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE datetime(LastSignIn) BETWEEN \""+from+" 00:00\" AND \""+to+" 23:59\" ORDER BY LastSignIn DESC;";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e); 
            return null;
        }
    }

    public ArrayList<User> FilterLogAdmin(boolean admin) {
        int value = 0;
        if (admin){
            value = 1; // if the user is an admin, then ste tha value to 1
        }
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE Admin = \""+value+"\" ORDER BY LastSignIn DESC";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<User> FilterLogFirstName(String name) {
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE FirstName = \""+name+"\" ORDER BY LastSignIn DESC;";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<User> FilterLogDateName(String from, String to, String name) {
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE FirstName = \""+name+"\" AND datetime(LastSignIn) BETWEEN \""+from+" 00:00\" AND \""+to+" 23:59\" ORDER BY LastSignIn DESC";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<User> FilterLogNameAdmin(String name, boolean admin) {
        int value = 0;
        if (admin){
            value = 1; // if the user is an admin, then ste tha value to 1
        }
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE Admin = \""+value+"\" AND FirstName = \""+name+"\" ORDER BY LastSignIn DESC";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<User> FilterLogDateAdmin(String from, String to, boolean admin) {
        int value = 0;
        if (admin){
            value = 1; // if the user is an admin, then ste tha value to 1
        }
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE Admin = \""+value+"\" AND LastSignIn BETWEEN \""+from+" 00:00\" AND \""+to+" 23:59\" ORDER BY LastSignIn DESC";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<User> FilterLogDateNameAdmin(String from, String to, String name, boolean admin) {
        int value = 0;
        if (admin){
            value = 1; // if the user is an admin, then ste tha value to 1
        }
        String sql = "SELECT FirstName, Surname, Admin, LastSignIn, LastSignOut FROM UserLog WHERE Admin = \""+value+"\" AND FirstName = \""+name+"\" AND datetime(LastSignIn) BETWEEN \""+from+" 00:00\" AND \""+to+" 23:59\" ORDER BY LastSignIn DESC";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute query
            while (rs.next()) {
                User u = getProductFromRowLog(rs); // for all the rows found, create a new User and add it to the ArrayList
                users.add(u);
            }
            return users;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }

    public User checkHashCode(User u){
        String sql = "SELECT UserEmail, UserPassword, HashedPassword, SaltCode FROM PasswordStorage WHERE UserEmail = '"+u.getEmail()+"';";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(); // execute query
            if (rs.next()) {
                User user = getProductFromRowPasswords(rs); // for all the rows found, create a new User and add it to the ArrayList
                rs.close();
                return user;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return null;
        }
    }
    
    public boolean DeleteLogs(){
        String sql = "DELETE FROM UserLog;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { // Catch an exception if it occurs
            System.err.println(e);
            return false;
        }
    }

}
