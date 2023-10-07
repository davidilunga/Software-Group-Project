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
public class DatasetDatabase {
    private Connection connection;

    public DatasetDatabase() {
        try {
            String url = "jdbc:sqlite:DataSet.sqlite";
            connection = DriverManager.getConnection(url); // establish a connection to the database called DataSet.sqlite
            System.out.println("Connection to Dataset database SUCCESS"); // if successful
        } catch (Exception e) {
            System.err.println("Connection to Dataset database FAILED"); // if unsuccessful
        }
    }

    public void CloseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    private GroupOneData getProductFromRow(ResultSet rs) throws SQLException { // retrieve database contents when a query is passed through it
        int projectRcn = rs.getInt(1);
        int ProjectID = rs.getInt(2);
        String ProjectAcronym = rs.getString(3);
        String Role = rs.getString(4);
        int ID = rs.getInt(5);
        String Name = rs.getString(6);
        String ShortName = rs.getString(7);
        String ActivityType = rs.getString(8);
        boolean EndOfParticipation = rs.getBoolean(9);
        float EcContribution = rs.getFloat(10);
        String Country = rs.getString(11);
        String Street = rs.getString(12);
        String City = rs.getString(13);
        String Postcode = rs.getString(14);
        String OrganisationURL = rs.getString(15);
        String vatNumber = rs.getString(16);
        String ContactForm = rs.getString(17);
        
        GroupOneData data = new GroupOneData(projectRcn, ProjectID, ProjectAcronym, Role, ID, Name, ShortName, ActivityType, EndOfParticipation, EcContribution, Country, Street, City, Postcode, OrganisationURL, vatNumber, ContactForm );
        // Create a new instance of  GroupOnedata to store all the data.
        return data;
    }
    
    private GroupOneData getProductFromRowProject(ResultSet rs) throws SQLException { //retrieve the data when a query is passed in regards to a project
        int projectRcn = rs.getInt(1);
        int ProjectID = rs.getInt(2);
        String ProjectAcronym = rs.getString(3);
        GroupOneData data = new GroupOneData(projectRcn, ProjectID, ProjectAcronym);
        // Create a new instance of  GroupOnedata to store all the data retieved
        return data;
    }
    
    private GroupOneData getProductFromRowOrganisation (ResultSet rs) throws SQLException { //retrieve the data when a query is passed in regards to an organisation
        int ID = rs.getInt(1);
        String Name = rs.getString(2);
        String ShortName = rs.getString(3);
        String ActivityType = rs.getString(4);
        String vatNumber = rs.getString(5);
        String OrganisationURL = rs.getString(6);
        GroupOneData data = new GroupOneData(ID, Name, ShortName, ActivityType, vatNumber,OrganisationURL);
        // Create a new instance of  GroupOnedata to store all the data retieved
        return data;
    }
    
    private GroupOneData getProductFromRowCountries (ResultSet rs) throws SQLException { //retrieve the data when a query is passed in regards to a Country
        String Country = rs.getString(1);
        
        GroupOneData data = new GroupOneData();
        data.setCountry(Country);
        // store the data in a setter
        return data;
    }
    
    private GroupOneData getProductFromRowContribution (ResultSet rs) throws SQLException { //retrieve the data when a query is passed in regards to the contribution
        int id = rs.getInt(1);
        float Contribution = rs.getFloat(2);
        
        GroupOneData data = new GroupOneData();
        data.setID(id);
        data.setEcContribution(Contribution);
        return data;
    }
    
    private GroupOneData getProductFromRowTotalCost (ResultSet rs) throws SQLException { //retrieve the data when a query is passed in regards to the project total cost
        String Acronym = rs.getString(1);
        String StartDate = rs.getString(2);
        String EndDate = rs.getString(3);
        double TotalCost = rs.getDouble(4);
        
        
        GroupOneData data = new GroupOneData();
        data.setProjectAcronym(Acronym);
        data.setStartDate(StartDate);
        data.setEndDate(EndDate);
        data.setTotalCost(TotalCost);
        return data;
    }
    
    public ArrayList<GroupOneData> getAll(){ // retrieve all the data from the database
        String sql = "SELECT * FROM GroupOneDataTable;"; // SQL query to collect the data
        ArrayList<GroupOneData> dataset = new ArrayList<>(); //instantiating a new ArrayList that will be used again later
        try (PreparedStatement ps = connection.prepareStatement(sql); // pass the query through to the database
                ResultSet rs = ps.executeQuery()) { // execute
            while (rs.next()) { // for each row found, the data will be added to the ArrayList
                GroupOneData u = getProductFromRow(rs); 
                dataset.add(u);// Data is stored in in the GroupOneData class
            }
            return dataset;
        } catch (SQLException e) { // if an error arises, it will be caught 
            System.err.println(e); // the error will be printed in the console
            return null;
        }
    }
    
    public ArrayList<GroupOneData> getAllCountries(){ // return all the countries found in the database
        String sql = "SELECT DISTINCT country FROM GroupOneDataTable;"; // Store the query inside the sting
        ArrayList<GroupOneData> dataset = new ArrayList<>(); // create a new ArrayList to store the data
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute the query to the DataSet database
            while (rs.next()) {
                GroupOneData u = getProductFromRowCountries(rs);
                dataset.add(u); // store the data from the database in the ArrayList
            }
            return dataset;
        } catch (SQLException e) { // if an error arises, it will be caught 
            System.err.println(e); // the error will be printed in the console
            return null;
        }
    }

    public ArrayList<GroupOneData> getAllProjects() { // Search all the projects in the Database
        String sql = "SELECT DISTINCT projectRcn, projectID, projectAcronym FROM GroupOneDataTable ORDER BY projectAcronym;";
        ArrayList<GroupOneData> dataset = new ArrayList<>(); // create a new ArrayList to store the data
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GroupOneData u = getProductFromRowProject(rs);
                dataset.add(u);// add the data to the database
            }
            return dataset;// return dataset to the mthod call
        } catch (SQLException e) { // catch Exception
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<GroupOneData> getAllOrganisations() { // Select all organisations forund in the database
        String sql = "SELECT DISTINCT id, name, shortName, activityType, vatNumber, organizationUrl FROM GroupOneDataTable ORDER BY name;"; // select every unique organisation from the database
        ArrayList<GroupOneData> dataset = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // Execute the query to the databse
            while (rs.next()) {
                GroupOneData u = getProductFromRowOrganisation(rs);
                dataset.add(u); // add the data found in the database to the ArrayList
            }
            return dataset;
        } catch (SQLException e) { // catch Exception if it arises
            System.err.println(e);
            return null;
        }
    } 

    public int getParticipants(String Project) { // Find the number of participants in a project
        String sql = "SELECT projectRcn, projectID, projectAcronym, role, id, name, shortName, activityType, endOfParticipation, ecContribution, country, street, city, postCode, organizationUrl, vatNumber, contactForm FROM GroupOneDataTable WHERE projectAcronym = '"+Project+"';";
        int temp = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute the query
            while (rs.next()) {
                temp += 1; // add 1 for each row found from the query
            }
            return temp; // return the number of participants
        } catch (SQLException e) { //catch the error if found
            System.err.println(e);
            return 0;
        }
    }
    
    public int getCountryParticipants(String CountryCode){ //get the number of countries that participates in the in the entire DataSet 
        String sql = "SELECT projectRcn, projectID, projectAcronym, role, id, name, shortName, activityType, endOfParticipation, ecContribution, country, street, city, postCode, organizationUrl, vatNumber, contactForm FROM GroupOneDataTable where country =  '"+CountryCode+"';";
        int temp = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {// execute query in the database
            while (rs.next()) {
                temp += 1; // for each row found, add 1 to temp and retrun temp to the method call.
            }
            return temp;
        } catch (SQLException e) { //catch an error if found whilst compiling
            System.err.println(e);
            return 0;
        }
    }
    
    public ArrayList<GroupOneData> checkDatabaseContaining(String orgName){ // check the databas eto see if the organisation contains anything typed in the search bar
        String sql = "SELECT DISTINCT id, name, shortName, activityType, vatNumber, organizationUrl FROM GroupOneDataTable WHERE name LIKE '"+orgName+"%'ORDER BY name;";
        ArrayList<GroupOneData> dataset = new ArrayList<>(); // new ArrayList to add to add the  data found from that database
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute the query
            while (rs.next()) {
                GroupOneData u = getProductFromRowOrganisation(rs); //Create the group data using what was found in the database 
                dataset.add(u); // for each row found, store it in the ArrayList
            }
            return dataset; // return the database
        } catch (SQLException e) { // catch exceptions if found
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<GroupOneData> checkProjectsContaining(String projectAc){ // search for projects where the organisation name contain the search field 
        String sql = "SELECT DISTINCT projectRcn, projectID, projectAcronym FROM GroupOneDataTable WHERE name LIKE '"+projectAc+"%' ORDER BY projectAcronym;";
        ArrayList<GroupOneData> dataset = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GroupOneData u = getProductFromRowProject(rs); // create a new instance of the data from the data found in the database
                dataset.add(u); //add the data to the ArrayList
            }
            return dataset; // return the ArrayList to the method call
        } catch (SQLException e) { // catch any Exception that may be found while compiling
            System.err.println(e);
            return null; //return value
        }
    }
    
    public ArrayList<GroupOneData> checkProjects(String orgName){ // select all the projects where the organisation name  equals the variable orgName
        String sql = "SELECT projectRcn, projectID, projectAcronym FROM GroupOneDataTable WHERE name = '"+orgName+"' ORDER BY projectAcronym;";
        ArrayList<GroupOneData> dataset = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GroupOneData u = getProductFromRowProject(rs); // for each row found, it will create a new instance of GroupOneData
                dataset.add(u);// it will add the new instance to the ArrayList
            }
            return dataset; // return the ArrayList back to the method call
        } catch (SQLException e) {
            System.err.println(e); // Catch any Exception if found
            return null;
        }
    }
    
    public ArrayList<GroupOneData> checkOrganisations(String project) { // select all the organisations fromt he database where the projectAcronym equals the parameter passed
        String sql = "SELECT DISTINCT id, name, shortName, activityType, vatNumber, organizationUrl FROM GroupOneDataTable WHERE projectAcronym = '"+project+"' ORDER BY name;";
        ArrayList<GroupOneData> dataset = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GroupOneData u = getProductFromRowOrganisation(rs);
                dataset.add(u); // add all the rows found to an ArrayList
            }
            return dataset; // return the Arrraylist
        } catch (SQLException e) { // Catch any exceptions found
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<GroupOneData> getEcContribution(String ProjectAc){ // get EcContribution from the database where the projectAcronym equals the parameter passed
        String sql = "SELECT id,ecContribution FROM GroupOneDataTable WHERE projectAcronym = '"+ProjectAc+"';";
        ArrayList<GroupOneData> dataset = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GroupOneData u = getProductFromRowContribution(rs); // add all the values found from the database to an ArrayList
                dataset.add(u); 
            }
            return dataset; // return the ArrayList
        } catch (SQLException e) { // Catch any Exceptions that my be found
            System.err.println(e);
            return null;
        }
    }
    
    public ArrayList<GroupOneData> getTotalCost(){ // Select the total costb of each project from the projects table based on the contents of the database
        String sql = "SELECT DISTINCT Acronym, StartDate, EndDate, TotalCost FROM ProjectAll INNER JOIN GroupOneDataTable ON ProjectAll.ProjectID = GroupOneDataTable.projectID;";
        ArrayList<GroupOneData> dataset = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) { // execute the query
            while (rs.next()) {// for each row found in the database
                GroupOneData u = getProductFromRowTotalCost(rs);
                dataset.add(u); // add the data found to an ArrayList to be reused
            }
            return dataset; // return the ArrayList back to the method call
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
