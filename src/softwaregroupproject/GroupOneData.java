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
public class GroupOneData implements Comparable<GroupOneData>{
    int projectRcn,ProjectID,ID;
    String ProjectAcronym,Role,Name,ShortName,ActivityType,Country,Street,City,Postcode,OrganisationURL,vatNumber,ContactForm;
    float EcContribution;
    boolean EndOfParticipation; // gloabal variables that are used to set values
    String StartDate, EndDate;
    double TotalCost;

    public GroupOneData() { // empty constructor to create a new instance
    }

    public GroupOneData(int projectRcn, int ProjectID, String ProjectAcronym, String Role, int ID, String Name, String ShortName, String ActivityType, boolean EndOfParticipation, float EcContribution, String Country, String Street, String City, String Postcode, String OrganisationURL, String vatNumber, String ContactForm ) {
        //constructor to create a new instance for data to be saved
        this.projectRcn = projectRcn;
        this.ProjectID = ProjectID;
        this.ID = ID;
        this.ProjectAcronym = ProjectAcronym;
        this.Role = Role;
        this.Name = Name;
        this.ShortName = ShortName;
        this.ActivityType = ActivityType; // assigning the parameters to the global variables
        this.Country = Country;
        this.Street = Street;
        this.City = City;
        this.Postcode = Postcode;
        this.OrganisationURL = OrganisationURL;
        this.vatNumber = vatNumber;
        this.ContactForm = ContactForm;
        this.EcContribution = EcContribution;
        this.EndOfParticipation = EndOfParticipation;
    }
    
    public GroupOneData(int projectRcn, int ProjectID, String ProjectAcronym){ //constructor to create a new instance for data to be saved
        this.projectRcn = projectRcn;       // assigning the parameters to the global variables
        this.ProjectID = ProjectID;
        this.ProjectAcronym = ProjectAcronym;
    }
    
    public GroupOneData(int ID, String Name, String ShortName, String ActivityType, String vatNumber, String OrganisationURL){ //constructor to create a new instance for data to be saved
        this.ID = ID;
        this.Name = Name;
        this.ShortName = ShortName;     // assigning the parameters to the global variables
        this.ActivityType = ActivityType;
        this.OrganisationURL = OrganisationURL;
        this.vatNumber = vatNumber;
    }

    public int getProjectRcn() {
        return projectRcn; // get project RCN code
    }

    public void setProjectRcn(int projectRcn) {
        this.projectRcn = projectRcn; // set project RCN code
    }

    public int getProjectID() {
        return ProjectID; // get project ID
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID; // set project ID
    }

    public int getID() {
        return ID; //get ID
    }

    public void setID(int ID) {
        this.ID = ID; // set ID
    }

    public String getProjectAcronym() {
        return ProjectAcronym; // get project acronym
    }

    public void setProjectAcronym(String ProjectAcronym) {
        this.ProjectAcronym = ProjectAcronym; // set project acronym
    }

    public String getRole() {
        return Role; // get role
    }

    public void setRole(String Role) {
        this.Role = Role; // set role
    }

    public String getName() {
        return Name; // get name
    }

    public void setName(String Name) {
        this.Name = Name; // set name
    }

    public String getShortName() {
        return ShortName; // get short name
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName; // set short name
    }

    public String getActivityType() {
        return ActivityType; // get activity type
    }

    public void setActivityType(String ActivityType) {
        this.ActivityType = ActivityType; // set activity type
    }

    public String getCountry() {
        return Country; // get country
    }

    public void setCountry(String Country) {
        this.Country = Country; // get country
    }

    public String getStreet() {
        return Street; // get street
    }

    public void setStreet(String Street) {
        this.Street = Street; // set street
    }

    public String getCity() {
        return City; // get city
    }

    public void setCity(String City) {
        this.City = City; // set city
    }

    public String getPostcode() {
        return Postcode; // get postcode
    }

    public void setPostcode(String Postcode) {
        this.Postcode = Postcode; // set postcode
    }

    public String getOrganisationURL() {
        return OrganisationURL; // get organisation url
    }

    public void setOrganisationURL(String OrganisationURL) {
        this.OrganisationURL = OrganisationURL; // set organistaion url
    }

    public String getVatNumber() {
        return vatNumber; // get VAT Number
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber; // set VAT Number
    }

    public String getContactForm() {
        return ContactForm; // get Contact Form
    }

    public void setContactForm(String ContactForm) {
        this.ContactForm = ContactForm; // Set Contact Form
    }

    public float getEcContribution() {
        return EcContribution; // get EcContribution
    }

    public void setEcContribution(float EcContribution) {
        this.EcContribution = EcContribution; // set EcContribution
    }

    public boolean isEndOfParticipation() {
        return EndOfParticipation; // get End of Participation
    }

    public void setEndOfParticipation(boolean EndOfParticipation) {
        this.EndOfParticipation = EndOfParticipation; // set End of Participation
    }

    public String getStartDate() {
        return StartDate; // get Start Date
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate; // set Start Date
    }

    public String getEndDate() {
        return EndDate; //get End Date
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate; // set End date
    }

    public double getTotalCost() {
        return TotalCost; // get Total cost
    }

    public void setTotalCost(double TotalCost) {
        this.TotalCost = TotalCost; // set Total Cost
    }
    
    @Override
    public int compareTo(GroupOneData o) { // compare the contribution of 2 projects
        int x = 0;

        if (this.getEcContribution() < o.getEcContribution()) {
            x = -1; // less than
        } else if (this.getEcContribution() == o.getEcContribution()) { // both equal
            x = 0;
        } else { // greater than the other
            x = 1; 
        }
        
        return x; // return the value
    }

    @Override
    public String toString() {
        return "GroupOneData{" + "ProjectAcronym=" + ProjectAcronym + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", TotalCost=" + TotalCost + '}';
    }

}
