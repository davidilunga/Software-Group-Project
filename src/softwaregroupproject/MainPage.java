/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaregroupproject;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ilunga
 * Graphs were created by group members, view Line Graph, Pie Chart and Scatter graph for the creator of those graphs
 */
public final class MainPage extends javax.swing.JFrame {

    static String UserName, Password, Last_Sign_Out;
    private static UserDatabase users = new UserDatabase();
    private static DatasetDatabase dataset = new DatasetDatabase(); //create access to the user database and the dataset database

    ArrayList<GroupOneData> Project = dataset.getAllProjects();
    ArrayList<GroupOneData> Countries = dataset.getAllCountries(); // global variables that are used later in the project

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        UserName = LoginFrame.getUserName();
        Password = LoginFrame.getPassword(); // creates a new instance of the main page without any parameters
        setBackground(Color.WHITE);
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

        addProjectTable(); // compile and fill the project table
        addOrganisationTable(); // compile and fill the organisation table
        addBarChart(); // compile and fill the bar chart
        addPieChart(); // compile and fill the pie chart
        addLineGraph(); // compile and fill the line graph
        addScatterGraph(); // compile and fill the scatter graph

        Search.addActionListener(new ActionListener() { // add an actionlistener to the search bar
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (Search.getText().equals("")) {
                    addProjectTable();
                    addOrganisationTable();
                } else {
                    searchOrganisation();
                }
            }
        });

        ListSelectionModel selectionModel = OrganisationTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e == null) {
                    addProjectTable();
                } else {
                    int row_index = OrganisationTable.getSelectedRow();
                    int col_index = 1;
                    searchProject(OrganisationTable.getModel().getValueAt(row_index, col_index).toString());
                }
            }
        });
    }

    public MainPage(String userName, String password) { // creates a new instance of the main page using the username and password as parameters
        initComponents();
        UserName = userName;
        MainPage.Password = password;   
        User user = users.get(UserName);
        if (user == null) {
            System.out.println("No user matches those details. \n");
        }
        Welcome.setText("Welcome, " + user.getFirstName() + ".");
        Welcome.setVisible(true);
        if (user.getType()) {
            LoginCheck.setVisible(true);
        } else {
            LoginCheck.setVisible(false);
        }

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

        addProjectTable(); // compile and fill the project table
        addOrganisationTable(); // compile and fill the organisation table
        addBarChart(); // compile and fill the bar chart
        addPieChart(); // compile and fill the pie chart
        addLineGraph(); // compile and fill the line graph
        addScatterGraph(); // compile and fill the scatter graph

        Search.addKeyListener(new KeyListener() { // add a key listener to the search bar
            @Override
            public void keyTyped(KeyEvent e) { // on each press, it will update the organisation table and the project table to everything corresponding
                if (Search.getText().equals("")) { // if the search bar is blank then show the original tables
                    addProjectTable();
                    addOrganisationTable();
                } else {
                    searchOrganisation(); // otherwise show the custom tables
                    searchProject();

                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (Search.getText().equals("")) {
                    addProjectTable();
                    addOrganisationTable();
                } else {
                    searchOrganisation();
                    searchProject();

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Search = (JTextField) e.getSource();
                String text = Search.getText();
                Search.setText(text.toUpperCase());
                searchOrganisation();
                searchProject();
            }
        });

        ListSelectionModel selectionModel = OrganisationTable.getSelectionModel(); // This allows you to do a selection search
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) { // when you click of a row in the organisation table it will filter the project Table
                try {
                    int row_index = OrganisationTable.getSelectedRow(); // this will get the selected row
                    int col_index = 1; // this will retrieve the organisation name
                    String orgName = OrganisationTable.getModel().getValueAt(row_index, col_index).toString(); // will get the organisation name of the row selected
                    searchProject(orgName); // filter the projects using the organisation name
                } catch (ArrayIndexOutOfBoundsException ex) { // will catch an error if it occurs

                }
            }
        });

        ListSelectionModel selectionModelProjects = ProjectsTable.getSelectionModel(); //this will allow to select projects and filter the organisations
        selectionModelProjects.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent ex) {
                try {
                    int row_index = ProjectsTable.getSelectedRow(); // this will get the selected row
                    int col_index = 2; // this will retrieve the project name
                    String projName = ProjectsTable.getModel().getValueAt(row_index, col_index).toString(); // will get the project name of the row selected
                    searchOrg(projName);  // filter the projects using the project name
                } catch (ArrayIndexOutOfBoundsException es) { // will catch an error if it occurs

                }
            }
        });
    }

    private void addProjectTable() { // reset the Project table with each Unique project in the database
        ArrayList<Integer> ProjectSize = new ArrayList<>();
        ProjectSize.clear(); // clear the ArrayList holding ths size of each project
        DefaultTableModel model = (DefaultTableModel) ProjectsTable.getModel(); // create an manipulation tool for table properties
        model.setRowCount(0); // set the row count to 0
        ArrayList<GroupOneData> list = dataset.getAllProjects(); // search the database for all the available projects
        Object rowData[] = new Object[ProjectsTable.getColumnCount()];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < Project.size(); i++) { // assign the values for the number of participants for each project
                int noOfParticipants = dataset.getParticipants(Project.get(i).getProjectAcronym());
                ProjectSize.add(noOfParticipants);
            }

            for (int i = 0; i < list.size(); i++) { // assign the values of each cell in the Table
                rowData[0] = list.get(i).getProjectRcn();
                rowData[1] = list.get(i).getProjectID();
                rowData[2] = list.get(i).getProjectAcronym();
                if (ProjectSize.get(i) < 10) {
                    rowData[3] = "0" + ProjectSize.get(i);
                } else {
                    rowData[3] = ProjectSize.get(i);
                }

                model.addRow(rowData);
            }
        }
    }

    private void addOrganisationTable() { // reset the Organisation table with each Unique organisation in the database
        DefaultTableModel model = (DefaultTableModel) OrganisationTable.getModel(); // create an manipulation tool for table properties
        model.setRowCount(0);
        ArrayList<GroupOneData> list = dataset.getAllOrganisations(); // search the database for all the organisations in the database
        Object rowData[] = new Object[OrganisationTable.getColumnCount()];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");
        } else {
            for (int i = 0; i < list.size(); i++) { // assign the values of each cell in the Table
                rowData[0] = list.get(i).getID();
                rowData[1] = list.get(i).getName();
                rowData[2] = list.get(i).getShortName();
                rowData[3] = list.get(i).getActivityType();
                rowData[4] = list.get(i).getVatNumber();
                rowData[5] = list.get(i).getOrganisationURL();

                model.addRow(rowData);
            }
        }

    }

    public void addBarChart() { // creating a bar Chart
        Scene scene = createBarScene(); // create a new scene for the bar chart
        jfxPanelBarChart.setScene(scene); // set the panel to show the bar chart
    }

    private Scene createBarScene() {
        ArrayList<Integer> ProjectSize = new ArrayList<>();
        ProjectSize.clear(); // clear the ProjectSize ArrayList
        ArrayList<ProjectInfo> projectInfo = new ArrayList<>();

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis); // Create a new Bar Chart Series
        bc.setTitle("Projects with the Largest No. of Participants");
        xAxis.setLabel("Value"); // Set the Axis names
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Projects");

        for (int i = 0; i < Project.size(); i++) {
            int noOfParticipants = dataset.getParticipants(Project.get(i).getProjectAcronym()); // get the number of participants for each project
            ProjectSize.add(noOfParticipants); // add noOfParticipants to teh ProjectSize ArrayList
            ProjectInfo newProject = new ProjectInfo(Project.get(i).getProjectAcronym(), noOfParticipants, "Bar"); //create a new instance of ProjectInfo by adding the project Acronym and the noOfParticipants to the ProjectInfo class using a constructor
            projectInfo.add(newProject); // also, add it to an array
        }

        Collections.sort(projectInfo, new Comparator<ProjectInfo>() { // sort the ArrayList projectInfo by the noOfParticipants (highest to lowest)
            @Override
            public int compare(ProjectInfo o1, ProjectInfo o2) {
                if (o1.getNoOfParticipants() > o2.getNoOfParticipants()) {
                    return -1;
                } else if (o1.getNoOfParticipants() == o2.getNoOfParticipants()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        XYChart.Series series1 = new XYChart.Series(); // Create a new series of an xy Chart to add the data
        series1.setName("Number of Participants");
        for (int i = 0; i < 10; i++) { // for loop - range = 10
            series1.getData().add(new XYChart.Data((projectInfo.get(i).getNoOfParticipants()), projectInfo.get(i).getProjectAcronym() + " [" + projectInfo.get(i).getNoOfParticipants() + "]"));
            // get the data  stored in the projectInfo ArrayList and add it to the bar chart data
        }

        Scene scene = new Scene(bc, 375, 355); // create a new scene for the bar chart to hold all the data added
        bc.getData().addAll(series1); // add the series to the bar chart
        return (scene);  // return the Bar Chart scene
    }

    public void addPieChart() { // Created by Mohammed Sayeed
        Scene scene = createPieScene(); // create a new scene for the pie chart
        jfxPanelPieChart.setScene(scene); // set the panel to show the pie chart
    }

    private Scene createPieScene() {
        ArrayList<ProjectInfo> projectInfo = new ArrayList<>();

        ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
        BorderPane root;
        PieChart pieChart; // create a new pie chart Instance

        for (int i = 0; i < Countries.size(); i++) {
            int noOfCountries = dataset.getCountryParticipants(Countries.get(i).getCountry());// get the number of countries participating in the overall datasheet
            ProjectInfo newProject = new ProjectInfo(Countries.get(i).getCountry(), noOfCountries, "Pie"); //create a new instance of ProjectInfo by adding the project Acronym and the noOfParticipants to the ProjectInfo class using a constructor
            projectInfo.add(newProject);
        }

        Collections.sort(projectInfo, new Comparator<ProjectInfo>() { // sort the ArrayList projectInfo by the noOfParticipants (highest to lowest)
            @Override
            public int compare(ProjectInfo o1, ProjectInfo o2) {
                if (o1.getCountryParticipants() > o2.getCountryParticipants()) {
                    return -1;
                } else if (o1.getCountryParticipants() == o2.getCountryParticipants()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            details.addAll(new PieChart.Data(projectInfo.get(i).getCountry() + " [" + projectInfo.get(i).getCountryParticipants() + "]", +projectInfo.get(i).getCountryParticipants()));
            // get the data  stored in the projectInfo ArrayList and add it to the pie chart data
        }

        root = new BorderPane();
        /* Size of the chart */
        Scene scene = new Scene(root, 375, 355);

        pieChart = new PieChart();
        pieChart.setData(details);
        pieChart.setTitle("Top 10 Countries w/ the most No. of Participants"); // Pie Chart Name
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);

        root.setCenter(pieChart);

        return (scene); // return the Pie Chart Scene
    }

    public void addLineGraph() { // Created Zaid Ahmed
        Scene scene = createLineScene(); // create a new scene for the line chart
        jfxPanelLineChart.setScene(scene); // set the panel to show the line chart
    }

    private Scene createLineScene() {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 375, 355); // create a new scene for the line graph

        CategoryAxis xAxis = new CategoryAxis(); // set the x-axis
        NumberAxis yAxis = new NumberAxis(18000000, 30000000, 1000000); // set y-axis upper and lower limits along with incrementations

        AreaChart chart = new AreaChart(xAxis, yAxis, getChartData()); // create new area chart

        chart.setTitle("Highest Costing Projects");

        Timeline t1 = new Timeline(); // create a new timeline for the area chart
        t1.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                (ActionEvent e) -> {
                    chart.getData().stream().forEach((series) -> {

                    });
                }));
        root.getChildren().add(chart);
        return (scene); // return the line chart scene
    }

    private ObservableList<XYChart.Series<String, Double>> getChartData() {

        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();

        XYChart.Series<String, Double> project1 = new XYChart.Series<>(); // create the line chart series data
        XYChart.Series<String, Double> project2 = new XYChart.Series<>();
        XYChart.Series<String, Double> project3 = new XYChart.Series<>();
        XYChart.Series<String, Double> project4 = new XYChart.Series<>();

        ArrayList<GroupOneData> project = dataset.getTotalCost();
        ArrayList<GroupOneData> usedData = new ArrayList<>();

        for (int i = 0; i < project.size(); i++) {
            if (project.get(i).getTotalCost() > 19000000.0) { // select all where the total cost is greater that 19 million
                usedData.add(project.get(i));
            }
        }

        project1.setName(usedData.get(0).getProjectAcronym());
        project2.setName(usedData.get(1).getProjectAcronym()); //getting the line names
        project3.setName(usedData.get(2).getProjectAcronym());
        project4.setName(usedData.get(3).getProjectAcronym());

        double [] project1Value = {19000000, 19500000, 21340000, 22000000, 23000000, 23700000,24174347}; // setting the intersection points for the 4 series
        double [] project2Value = {22000000, 22750000, 24000000, 25500000, 26400000, 26950000,27300932};
        double [] project3Value = {20500000, 21500000, 23005000, 24600000, 25800000, 26200000,26774582};
        double [] project4Value = {23000000, 23700000, 24500000, 26300000, 27700000, 28800000,29077704};

        for (int i = 2018; i < 2025; i++) {
                project1.getData().add(new XYChart.Data(Integer.toString(i), project1Value[i - 2018])); //setting the values for eachpoint on the Lins graph

                project2.getData().add(new XYChart.Data(Integer.toString(i), project2Value[i - 2018]));

                project3.getData().add(new XYChart.Data(Integer.toString(i), project3Value[i - 2018]));

                project4.getData().add(new XYChart.Data(Integer.toString(i), project4Value[i - 2018]));
        }

        data.addAll(project1, project2, project3, project4); // adding the data to the line chart data

        return data;
    }

    public void addScatterGraph() {
        Scene scene = createScatterScene(); // create a new scene for the Scatter chart
        jfxPanelScatterChart.setScene(scene); // set the panel to show the Scatter chart
    }

    private Scene createScatterScene() {
        final CategoryAxis xAxis = new CategoryAxis(); // set the x-axis
        final NumberAxis yAxis = new NumberAxis(); // set the y-axis
        final ScatterChart<String, Number> sc = new ScatterChart<String, Number>(xAxis, yAxis); // creates a new scatter graph instance
        xAxis.setLabel("Organisation ID"); // setting x and y axis labels
        yAxis.setLabel("EC Contribution");
        sc.setTitle("Top 3 Contribution Overview");

        ArrayList<GroupOneData> contribution = new ArrayList<>();
        contribution = dataset.getEcContribution("E-SHAPE"); //retrieves the contribution where the project Acronym  is equal to E-Shape

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("E-SHAPE");
        for (int i = 0; i < 15; i++) {
            series1.getData().add(new XYChart.Data("" + contribution.get(i).getID() + "", contribution.get(i).getEcContribution()));
            // Add the data that was found to the scatter graph data chart
        }

        contribution = dataset.getEcContribution("EurofleetsPlus");//retrieves the contribution where the project Acronym  is equal to EurofleetsPlus

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("EurofleetsPlus");
        for (int i = 0; i < 15; i++) {
            series2.getData().add(new XYChart.Data("" + contribution.get(i).getID() + "", contribution.get(i).getEcContribution()));
            // Add the data that was found to the scatter graph data chart
        }

        contribution = dataset.getEcContribution("ENVRI-FAIR"); //retrieves the contribution where the project Acronym  is equal to ENVRI-FAIR

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("ENVRI-FAIR");
        for (int i = 0; i < 15; i++) {
            series3.getData().add(new XYChart.Data("" + contribution.get(i).getID() + "", contribution.get(i).getEcContribution()));
            // Add the data that was found to the scatter graph data chart
        }

        sc.getData().addAll(series1, series2, series3);
        Scene scene = new Scene(sc, 500, 400); // create a new scene for the scatter graph
        
        return (scene); // return the scen for the panel to use
    }

    public void searchOrganisation() {
        DefaultTableModel model = (DefaultTableModel) OrganisationTable.getModel();     // allows modification of OrgaanisationTable
        model.setRowCount(0);                                   
        ArrayList<GroupOneData> list = dataset.checkDatabaseContaining(Search.getText());      //checks the information the user enters in the search field from DatabaseContaining dataset.
        Object rowData[] = new Object[OrganisationTable.getColumnCount()];

        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).getID();                   //assigns the data to the ID 
            rowData[1] = list.get(i).getName();                 //assigns the data to the Name
            rowData[2] = list.get(i).getShortName();            //assigns the data to the ShortName 
            rowData[3] = list.get(i).getActivityType();         //assigns the data to the Activity Type
            rowData[4] = list.get(i).getVatNumber();            //assigns the data to the VatNumber
            rowData[5] = list.get(i).getOrganisationURL();      //assigns the data to the OrganisationURL

            model.addRow(rowData);
        }
    }

    public void searchProject() {
        ArrayList<Integer> ProjectSize = new ArrayList<>();     //sets an arraylist for ProjectSize 
        ProjectSize.clear();        
        DefaultTableModel model1 = (DefaultTableModel) ProjectsTable.getModel();        //allows manipulation of ProjectsTable through the 'getModel' method
        model1.setRowCount(0);
        ArrayList<GroupOneData> list1 = dataset.checkProjectsContaining(Search.getText());      //sets the GroupOneData Arraylist and retrieves the information from when the user searches for a particular information form orgName
        Object rowData1[] = new Object[ProjectsTable.getColumnCount()];

        for (int i = 0; i < list1.size(); i++) {
            int noOfParticipants = dataset.getParticipants(list1.get(i).getProjectAcronym()); //retrieves the integer (i) to obtain noOfParticipants counting the projectAcronym
            ProjectSize.add(noOfParticipants);
        }

        for (int i = 0; i < list1.size(); i++) {
            rowData1[0] = list1.get(i).getProjectRcn();     //obtain the ProjectRcn and fills the data in the table
            rowData1[1] = list1.get(i).getProjectID();         //obtain the ProjectRcn and fills the data in the table
            rowData1[2] = list1.get(i).getProjectAcronym();     //obtain the ProjectRcn and fills the data in the table
            if (ProjectSize.get(i) < 10) {
                rowData1[3] = "0" + ProjectSize.get(i);
            } else {
                rowData1[3] = ProjectSize.get(i);
            }

            model1.addRow(rowData1);        //add information into the rowData
        }
    }

    public void searchProject(String orgName) {
        ArrayList<Integer> ProjectSize = new ArrayList<>();     //assigns an ArrayList for ProjectSize
        ProjectSize.clear();
        DefaultTableModel model1 = (DefaultTableModel) ProjectsTable.getModel();       //modifies the ProjectsTable to manipulate data
        model1.setRowCount(0);
        ArrayList<GroupOneData> list1 = dataset.checkProjects(orgName);    //calls dataset form the ArrayList in GroupOneData to display Projects with relevant Organisation Name
        Object rowData1[] = new Object[ProjectsTable.getColumnCount()];

        for (int i = 0; i < list1.size(); i++) {
            int noOfParticipants = dataset.getParticipants(list1.get(i).getProjectAcronym());   //gathers the participants and displays it in a column
            ProjectSize.add(noOfParticipants);
        }

        for (int i = 0; i < list1.size(); i++) {
            rowData1[0] = list1.get(i).getProjectRcn();
            rowData1[1] = list1.get(i).getProjectID();
            rowData1[2] = list1.get(i).getProjectAcronym();
            if (ProjectSize.get(i) < 10) {
                rowData1[3] = "0" + ProjectSize.get(i);
            } else {
                rowData1[3] = ProjectSize.get(i);
            }

            model1.addRow(rowData1);
        }
    }

    public void searchOrg(String projName) {
        DefaultTableModel model = (DefaultTableModel) OrganisationTable.getModel(); //calls the table and allows manipulation of the data entry
        model.setRowCount(0); 
        ArrayList<GroupOneData> list = dataset.checkOrganisations(projName); //calls dataset from Organisations that match the projectName
        Object rowData[] = new Object[OrganisationTable.getColumnCount()];
        if (list == null) {
            JOptionPane.showMessageDialog(this, "There are no logs to show. \n");      //if there is no data to be displayed, this will be shown.
        } else {
            for (int i = 0; i < list.size(); i++) {
                rowData[0] = list.get(i).getID();                       //gets the information of each column and fills the row 
                rowData[1] = list.get(i).getName();
                rowData[2] = list.get(i).getShortName();
                rowData[3] = list.get(i).getActivityType();
                rowData[4] = list.get(i).getVatNumber();
                rowData[5] = list.get(i).getOrganisationURL();

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

        LoginCheck = new javax.swing.JButton();
        Welcome = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        SignOut = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProjectsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        OrganisationTable = new javax.swing.JTable();
        ResetTables = new javax.swing.JButton();
        jfxPanelLineChart = new javafx.embed.swing.JFXPanel();
        jfxPanelPieChart = new javafx.embed.swing.JFXPanel();
        jfxPanelBarChart = new javafx.embed.swing.JFXPanel();
        jfxPanelScatterChart = new javafx.embed.swing.JFXPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Home");
        setBackground(new java.awt.Color(255, 255, 255));

        LoginCheck.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        LoginCheck.setText("Check Access");
        LoginCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginCheckActionPerformed(evt);
            }
        });

        Welcome.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N

        Search.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        Search.setToolTipText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        SignOut.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        SignOut.setText("Sign Out");
        SignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignOutActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ProjectsTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProjectsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project RCN", "Project ID", "Project Acronym", "Number Of Participants"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ProjectsTable);

        OrganisationTable.setAutoCreateRowSorter(true);
        OrganisationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Organisation Name", "Organisation Short Name", "Organisation Activity Type", "Organisation VAT Number", "Organisation URL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OrganisationTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(OrganisationTable);

        ResetTables.setFont(new java.awt.Font("Tw Cen MT", 3, 18)); // NOI18N
        ResetTables.setText("Reset Tables");
        ResetTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetTablesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ResetTables, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ResetTables, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LoginCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jfxPanelBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jfxPanelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfxPanelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jfxPanelScatterChart, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LoginCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Welcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SignOut, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jfxPanelBarChart, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jfxPanelPieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jfxPanelLineChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jfxPanelScatterChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginCheckActionPerformed
        // TODO add your handling code here:
        //this method logs the date the user signs in and verifies the UserName + Password
        AdminDateChecker dateCheck = new AdminDateChecker(UserName, Password);
        dateCheck.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LoginCheckActionPerformed

    private void SignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutActionPerformed
        // TODO add your handling code here:
        // Sign Out Action activity 
        int input = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out?", "Confirm", YES_NO_OPTION); //confirm if user wants to sign out
        if (input == 0) {
            boolean success = LogTimeOut(UserName);     //if the username does not match a user signed then there is no log to create
            if (!success) {
                JOptionPane.showMessageDialog(this, "We are unable log the time you signed out."); 
            } else {
                LoginFrame home = new LoginFrame();
                home.setVisible(true);
                this.setVisible(false);
            }
        } else if (input == 1) {

        }
    }//GEN-LAST:event_SignOutActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void ResetTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetTablesActionPerformed
        // TODO add your handling code here:
        addProjectTable();
        addOrganisationTable();
    }//GEN-LAST:event_ResetTablesActionPerformed

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

   //Sign Out time for the user is logged here
    public static boolean LogTimeOut(String Username) {
        User user = users.get(Username);
        if (user == null) {
            System.out.println("No user matches those details. \n");      //if the username does not match a user signed then there is no log to create
            return false;
        }

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //sets the format to the date the user has signed out
        Last_Sign_Out = format1.format(new Date());
        user.setLast_Sign_Out(Last_Sign_Out);               //register the user's sign out date
        boolean success = users.updateTimeOut(user);
        if (success) {
            System.out.println("Modifictaion SUCCESSFUL Time out");      //is shown in the server (netbeans connection) each time a user's log is updated
            return true;
        } else {
            System.err.println("Modifictaion FAILED Time out");         //is shown in the server (netbeans connection) each time a user's log is updated
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginCheck;
    private javax.swing.JTable OrganisationTable;
    private javax.swing.JTable ProjectsTable;
    private javax.swing.JButton ResetTables;
    private javax.swing.JTextField Search;
    private javax.swing.JButton SignOut;
    private javax.swing.JLabel Welcome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javafx.embed.swing.JFXPanel jfxPanelBarChart;
    private javafx.embed.swing.JFXPanel jfxPanelLineChart;
    private javafx.embed.swing.JFXPanel jfxPanelPieChart;
    private javafx.embed.swing.JFXPanel jfxPanelScatterChart;
    // End of variables declaration//GEN-END:variables
}
