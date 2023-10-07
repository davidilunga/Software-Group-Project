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
public class ProjectInfo implements Comparable<ProjectInfo> {

    private String ProjectAcronym;
    private int noOfParticipants;
    private String Country;  // global variables used to assign variables
    private int CountryParticipants;
    private String Organisation;
    private float Contribution;

    public ProjectInfo(String ProjectAcronym, int noOfParticipants, String type) {
        if (type.equals("Bar")) {
            this.ProjectAcronym = ProjectAcronym;
            this.noOfParticipants = noOfParticipants;  // constructor for the two different types of grpahs
        } else if (type.equals("Pie")) {
            Country = ProjectAcronym;
            CountryParticipants = noOfParticipants;
        }
    }

    public ProjectInfo(String ProjectAcronym, String Organisation, float Contribution) {
        this.ProjectAcronym = ProjectAcronym;
        this.Organisation = Organisation;   // constructor for the project info
        this.Contribution = Contribution;
    }

    public String getOrganisation() {
        return Organisation;  // get the organisation
    }

    public void setOrganisation(String Organisation) {
        this.Organisation = Organisation;  // set the organisation
    }

    public float getContribution() {
        return Contribution; // get the contribution
    }

    public void setContribution(float Contribution) {
        this.Contribution = Contribution;  // set the contribution
    }

    public String getProjectAcronym() {
        return ProjectAcronym; // get the project Acronym
    }

    public void setProjectAcronym(String ProjectAcronym) {
        this.ProjectAcronym = ProjectAcronym;  // set the project Acronym
    }

    public int getNoOfParticipants() {
        return noOfParticipants;  // get the number of participants
    }

    public void setNoOfParticipants(int noOfParticipants) {
        this.noOfParticipants = noOfParticipants; // set the number of participants

    }

    public String getCountry() {
        return Country;  // get the country
    }

    public void setCountry(String Country) {
        this.Country = Country;   // set the country
    }

    public int getCountryParticipants() {
        return CountryParticipants;  // get the number countrys participating
    }

    public void setCountryParticipants(int CountryParticipants) {
        this.CountryParticipants = CountryParticipants; // set the number countrys participating
    }

    @Override
    public int compareTo(ProjectInfo p) { // compare the number of participants
        int x = 0;

        if (this.getNoOfParticipants() < p.getNoOfParticipants()) {
            x = -1; // less than
        } else if (this.getNoOfParticipants() == p.getNoOfParticipants()) { // both equal
            x = 0;
        } else { // greater than the other
            x = 1;
        }

        return x; // return the value
    }
}
