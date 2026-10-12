/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package general;

/**
 *
 * @author Ryan
 */
import java.sql.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import database.DBConn;
import java.text.SimpleDateFormat;
import java.lang.Object;
import java.time.LocalDate;


/*
validate in attendance if information entered existed
create logic for update and delete selected and clear buttons
add exit program buton
fix about us and add back button
connect to database 
*/


public class MainFrame extends javax.swing.JFrame {
    int currentState;

    // Student individual components
    private JTextField inputStudentId;
    private JTextField inputFirstName;
    private JTextField inputMiddleInitial;
    private JTextField inputLastName;
    private JSpinner inputAge;
    private JComboBox inputGender;
    private JTextField inputAddress;
    private JTextField inputCourse;
    private JSpinner inputYearLevel;

    private JLabel promptStudentId;
    private JLabel promptFirstName;
    private JLabel promptMiddleInitial;
    private JLabel promptLastName;
    private JLabel promptAge;
    private JLabel promptGender;
    private JLabel promptAddress;
    private JLabel promptCourse;
    private JLabel promptYearsLevel;

    // Seminar individual components
    private JTextField inputSeminarId;
    private JTextField inputTitle;
    private JTextField inputVenue;
    private JTextField inputDateTo;
    private JTextField inputDateFrom;

    private JLabel promptSeminarId;
    private JLabel promptTitle;
    private JLabel promptVenue;
    private JLabel promptDateTo;
    private JLabel promptDateFrom;

    // Attendance individual components
    private JTextField inputAttendanceId;
    private JTextField inputStudentName;
    private JTextField inputSeminarTitle;
    private JSpinner inputSemester;
    private JSpinner inputSchoolYear;
    private JTextField inputFee;

    private JLabel promptAttendanceId;
    private JLabel promptStudentName;
    private JLabel promptSeminarTitle;
    private JLabel promptSemester;
    private JLabel promptSchoolYear;
    private JLabel promptFee;

    // Tables
    private DefaultTableModel studentTable;
    private DefaultTableModel seminarTable;
    private DefaultTableModel attendanceTable;
    
    private JScrollPane outputStudent;
    private JScrollPane outputSeminar;
    private JScrollPane outputAttendance;
    
    private void intializeTables(){
        String[] studentColumns = {
            "Student ID",
            "First Name",
            "M.I.",
            "Last Name",
            "Age",
            "Gender",
            "Address",
            "Course",
            "Year Level"
        };
        
        String[] seminarColumns = {
            "Seminar ID",
            "Title",
            "Venue",
            "Date To",
            "Date From"
        };
        
        String[] attendanceColumns = {
            "Attendance ID",
            "Student Name",
            "Seminar Title",
            "Semester",
            "School Year",
            "Fee"
        };
        
        studentTable = new DefaultTableModel(studentColumns, 0);
        seminarTable = new DefaultTableModel(seminarColumns, 0);
        attendanceTable = new DefaultTableModel(attendanceColumns, 0);
        
        outputStudent = new JScrollPane(new JTable(studentTable));
        outputSeminar = new JScrollPane(new JTable(seminarTable));
        outputAttendance = new JScrollPane(new JTable(attendanceTable));
        
        displayTable();
        
        outputPanel.setLayout(new BorderLayout());
        outputPanel.add(outputStudent, BorderLayout.CENTER);
    }
    
    private void initializeInputFieldsForStudent(){
        inputStudentId = new JTextField();
        inputFirstName = new JTextField();
        inputMiddleInitial = new JTextField();
        inputLastName = new JTextField();
        inputAge = new JSpinner();
        inputGender = new JComboBox();
        inputAddress = new JTextField();
        inputCourse = new JTextField();
        inputYearLevel = new JSpinner();
        
        promptStudentId = new JLabel();
        promptFirstName = new JLabel();
        promptMiddleInitial = new JLabel();
        promptLastName = new JLabel();
        promptAge = new JLabel();
        promptGender = new JLabel();
        promptAddress = new JLabel();
        promptCourse = new JLabel();
        promptYearsLevel = new JLabel();
        
        promptStudentId.setText("Student ID");
        promptFirstName.setText("First Name");
        promptMiddleInitial.setText("Middle Initial");
        promptLastName.setText("Last Name");
        promptAge.setText("Age");
        promptGender.setText("Gender");
        promptAddress.setText("Address");
        promptCourse.setText("Course");
        promptYearsLevel.setText("Year Level");
        
        inputGender.addItem("Male");
        inputGender.addItem("Female");
        
        promptStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
        promptFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        promptMiddleInitial.setHorizontalAlignment(SwingConstants.RIGHT);
        promptLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        promptAge.setHorizontalAlignment(SwingConstants.RIGHT);
        promptGender.setHorizontalAlignment(SwingConstants.RIGHT);
        promptAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        promptCourse.setHorizontalAlignment(SwingConstants.RIGHT);
        promptYearsLevel.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    private void initializeInputFieldsForSeminar(){
        inputSeminarId = new JTextField();
        inputTitle = new JTextField();
        inputVenue = new JTextField();
        inputDateTo = new JTextField();
        inputDateFrom = new JTextField();

        promptSeminarId = new JLabel();
        promptTitle = new JLabel();
        promptVenue = new JLabel();
        promptDateTo = new JLabel();
        promptDateFrom = new JLabel();

        promptSeminarId.setText("Seminar Id");
        promptTitle.setText("Title");
        promptVenue.setText("Venue");
        promptDateTo.setText("Date To");
        promptDateFrom.setText("Date From");
        
        promptSeminarId.setHorizontalAlignment(SwingConstants.RIGHT);
        promptTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        promptVenue.setHorizontalAlignment(SwingConstants.RIGHT);
        promptDateTo.setHorizontalAlignment(SwingConstants.RIGHT);
        promptDateFrom.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    private void initializeInputFieldsForAttendance(){
        inputAttendanceId = new JTextField();
        inputStudentName = new JTextField();
        inputSeminarTitle = new JTextField();
        inputSemester = new JSpinner();
        inputSchoolYear = new JSpinner();
        inputFee = new JTextField();

        promptAttendanceId = new JLabel();
        promptStudentName = new JLabel();
        promptSeminarTitle = new JLabel();
        promptSemester = new JLabel();
        promptSchoolYear = new JLabel();
        promptFee = new JLabel();
        
        promptAttendanceId.setText("Attendance Id");
        promptStudentName.setText("Student Name");
        promptSeminarTitle.setText("Seminar Title");
        promptSemester.setText("Semester");
        promptSchoolYear.setText("School Year");
        promptFee.setText("Fee");
        
        promptAttendanceId.setHorizontalAlignment(SwingConstants.RIGHT);
        promptStudentName.setHorizontalAlignment(SwingConstants.RIGHT);
        promptSeminarTitle.setHorizontalAlignment(SwingConstants.RIGHT);
        promptSemester.setHorizontalAlignment(SwingConstants.RIGHT);
        promptSchoolYear.setHorizontalAlignment(SwingConstants.RIGHT);
        promptFee.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    private void createInputFieldForStudent(){            
        Dimension currentSize = mainPanel.getSize();
        mainPanel.setPreferredSize(currentSize);
        
        inputPanel.removeAll();
        
        inputPanel.add(promptStudentId);
        inputPanel.add(inputStudentId);
        inputPanel.add(promptFirstName);
        inputPanel.add(inputFirstName);
        inputPanel.add(promptMiddleInitial);
        inputPanel.add(inputMiddleInitial);
        inputPanel.add(promptLastName);
        inputPanel.add(inputLastName);
        inputPanel.add(promptAge);
        inputPanel.add(inputAge);
        inputPanel.add(promptGender);
        inputPanel.add(inputGender);
        inputPanel.add(promptAddress);
        inputPanel.add(inputAddress);
        inputPanel.add(promptCourse);
        inputPanel.add(inputCourse);
        inputPanel.add(promptYearsLevel);
        inputPanel.add(inputYearLevel);
        
        inputPanel.revalidate();
        inputPanel.repaint();
    }
    
    private void createInputFieldForSeminar(){            
        Dimension currentSize = mainPanel.getSize();
        mainPanel.setPreferredSize(currentSize);
        
        inputPanel.removeAll();
        
        inputPanel.add(promptSeminarId);
        inputPanel.add(inputSeminarId);
        inputPanel.add(promptTitle);
        inputPanel.add(inputTitle);
        inputPanel.add(promptVenue);
        inputPanel.add(inputVenue);
        inputPanel.add(promptDateTo);
        inputPanel.add(inputDateTo);
        inputPanel.add(promptDateFrom);
        inputPanel.add(inputDateFrom);
        
        inputPanel.revalidate();
        inputPanel.repaint();
    }
    
    private void createInputFieldForAttendance(){            
        Dimension currentSize = mainPanel.getSize();
        mainPanel.setPreferredSize(currentSize);
        
        inputPanel.removeAll();
        
        inputPanel.add(promptAttendanceId);
        inputPanel.add(inputAttendanceId);
        inputPanel.add(promptStudentName);
        inputPanel.add(inputStudentName);
        inputPanel.add(promptSeminarTitle);
        inputPanel.add(inputSeminarTitle);
        inputPanel.add(promptSemester);
        inputPanel.add(inputSemester);
        inputPanel.add(promptSchoolYear);
        inputPanel.add(inputSchoolYear);
        inputPanel.add(promptFee);
        inputPanel.add(inputFee);
        
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    
    private void addEntryForStudent(){     
        outputPanel.removeAll();
       
        String studentId = inputStudentId.getText();
        String firstName = inputFirstName.getText();
        String middleInitial = inputMiddleInitial.getText();
        String lastName = inputLastName.getText();
        Object age = inputAge.getValue();
        Object gender = inputGender.getSelectedItem();
        String address = inputAddress.getText();
        String course = inputCourse.getText();
        Object yearLevel = inputYearLevel.getValue();
        
        Object[] rowData = {
            studentId, 
            firstName, 
            middleInitial, 
            lastName, 
            age, 
            gender, 
            address, 
            course, 
            yearLevel
        };
        
        if(!isValid(rowData, false)){
            return;
        }
        
        String query = "INSERT INTO student (student_id, first_name, middle_initial, last_name, age, gender, address, course, year_level) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        boolean added = addToDatabase(rowData, query);
        
        if(!added){
            displayTable();
            return;
        }
        
        try{
            Connection con = DBConn.getConnection();
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");
            
            while(rs.next()){
                Object[] row = {
                    rs.getInt("student_id"),
                    rs.getString("first_name"),
                    rs.getString("middle_initial"),
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("address"),
                    rs.getString("course"),
                    rs.getInt("year_level")
                };
                
                studentTable.addRow(row);
            }
            
            con.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(this, "Connection failed! " + e.getMessage());
        }
        
        displayTable();
        clearFields();
    }
    
    private void addEntryForSeminar(){       
        outputPanel.removeAll();
        
        String seminarId = inputSeminarId.getText();
        String title = inputTitle.getText();
        String venue = inputVenue.getText();
        String dateTo = inputDateTo.getText();
        String dateFrom = inputDateFrom.getText();
        
        Object[] rowData = {
            seminarId, 
            title, 
            venue, 
            dateTo, 
            dateFrom
        };
        
        if(!isValid(rowData, true)){
            return;
        }
        
        String query = "INSERT INTO seminar (seminar_id, title, venue, date_to, date_from) VALUES (?, ?, ?, ?, ?)";
        
        boolean added = addToDatabase(rowData, query);
        
        if(!added){
            displayTable();
            return;
        }
        
        try{
            Connection con = DBConn.getConnection();
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM seminar");
            
            while(rs.next()){
                Object[] row = {
                    rs.getInt("seminar_id"),
                    rs.getString("title"),
                    rs.getString("venue"),
                    rs.getString("date_to"),
                    rs.getString("date_from")
                };
                
                seminarTable.addRow(row);
            }
            
            con.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(this, "Connection failed! " + e.getMessage());
        }
        
        displayTable();
        clearFields();
    }
    
    private void addEntryForAttendance(){      
        outputPanel.removeAll();
        
        String attendanceId = inputAttendanceId.getText();
        String studentName = inputStudentName.getText();
        String seminarTitle = inputSeminarTitle.getText();
        Object semester = inputSemester.getValue();
        Object schoolYear = inputSchoolYear.getValue();
        String fee = inputFee.getText();
        
        Object[] rowData = {
            attendanceId, 
            studentName, 
            seminarTitle, 
            semester, 
            schoolYear, 
            fee
        };
        
        if(!isValid(rowData, false)){
            return;
        }
        
        String query = "INSERT INTO attendance (attendance_id, student_name, seminar_title, semester, school_year, fee) VALUES (?, ?, ?, ?, ?, ?)";
        
        boolean added = addToDatabase(rowData, query);
        
        if(!added){
            displayTable();
            return;
        }
        
        try{
            Connection con = DBConn.getConnection();
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM attendance");
            
            while(rs.next()){
                Object[] row = {
                    rs.getInt("attendance_id"),
                    rs.getString("student_name"),
                    rs.getString("seminar_title"),
                    rs.getInt("semester"),
                    rs.getInt("school_year"),
                    rs.getDouble("fee")
                };
                
                attendanceTable.addRow(row);
            }
            
            con.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(this, "Connection failed! " + e.getMessage());
        }
        
        displayTable();
        clearFields();
    }
    
    private boolean isValid(Object[] inputs, boolean hasDate){
        for(int i=0; i<inputs.length; i++){
            if(inputs[i].equals("")){
                JOptionPane.showMessageDialog(null, "Please complete all the fields before addding entry.");
                return false;
            }
        }
        
        if(hasDate){
            try{
                LocalDate.parse(inputs[3].toString());
                LocalDate.parse(inputs[4].toString());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Please enter date fields in ISO 8601 (yyyy-mm-dd).");
                return false;
            }
        }
        
        try{
            Integer.parseInt(inputs[0].toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ID must be numeric.");
            return false;
        }
        return true;
    }
    
    private void displayTable(){      
        outputPanel.removeAll();
        
        try{
            Connection con = DBConn.getConnection();
            Statement stmt = con.createStatement();
            
            switch(currentState){
                case 1:
                    studentTable.setRowCount(0);
                    
                    ResultSet rsStudent = stmt.executeQuery("SELECT * FROM student");
            
                    while(rsStudent.next()){
                        Object[] row = {
                            rsStudent.getInt("student_id"),
                            rsStudent.getString("first_name"),
                            rsStudent.getString("middle_initial"),
                            rsStudent.getString("last_name"),
                            rsStudent.getInt("age"),
                            rsStudent.getString("gender"),
                            rsStudent.getString("address"),
                            rsStudent.getString("course"),
                            rsStudent.getInt("year_level")
                        };

                        studentTable.addRow(row);
                    }
                    
                    outputPanel.add(outputStudent);
                    break;
                case 2:
                    seminarTable.setRowCount(0);
                    
                    ResultSet rsSeminar = stmt.executeQuery("SELECT * FROM seminar");
            
                    while(rsSeminar.next()){
                        Object[] row = {
                            rsSeminar.getInt("seminar_id"),
                            rsSeminar.getString("title"),
                            rsSeminar.getString("venue"),
                            rsSeminar.getString("date_to"),
                            rsSeminar.getString("date_from")
                        };

                        seminarTable.addRow(row);
                    }
                    
                    outputPanel.add(outputSeminar);
                    break;
                case 3:
                    attendanceTable.setRowCount(0);
                    
                    ResultSet rsAttendance = stmt.executeQuery("SELECT * FROM attendance");
            
                    while(rsAttendance.next()){
                        Object[] row = {
                            rsAttendance.getInt("attendance_id"),
                            rsAttendance.getString("student_name"),
                            rsAttendance.getString("seminar_title"),
                            rsAttendance.getInt("semester"),
                            rsAttendance.getInt("school_year"),
                            rsAttendance.getDouble("fee")
                        };

                        attendanceTable.addRow(row);
                    }
                    
                    outputPanel.add(outputAttendance);
                    break;
            }
            
            con.close();
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(this, "Connection failed! " + e.getMessage());
        }
        
        outputPanel.revalidate();
        outputPanel.repaint();
    }
    
    private void clearFields(){
        switch(currentState){
            case 1:
                inputStudentId.setText("");
                inputFirstName.setText("");
                inputMiddleInitial.setText("");
                inputLastName.setText("");
                inputAge.setValue(0);
                inputGender.setSelectedIndex(0);
                inputAddress.setText("");
                inputCourse.setText("");
                inputYearLevel.setValue(0);
                break;
            case 2:
                inputSeminarId.setText("");
                inputTitle.setText("");
                inputVenue.setText("");
                inputDateTo.setText("");
                inputDateFrom.setText("");
                break;
            case 3:
                inputAttendanceId.setText("");
                inputStudentName.setText("");
                inputSeminarTitle.setText("");
                inputSemester.setValue(0);
                inputSchoolYear.setValue(0);
                inputFee.setText("");
                break;
        }
    }
    
    private boolean addToDatabase(Object[] params, String query){
        try{
            Connection con = DBConn.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            
            for(int i=0; i<params.length; i++){
                pstmt.setObject(i + 1, params[i]);
            }

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record added successfully!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {      
        currentState = 1;
        
        initComponents();
        
        intializeTables();
        initializeInputFieldsForStudent();
        initializeInputFieldsForSeminar();
        initializeInputFieldsForAttendance();
        
        createInputFieldForStudent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        aboutUsButton = new javax.swing.JButton();
        sidePanel = new javax.swing.JPanel();
        studentButton = new javax.swing.JButton();
        seminarButton = new javax.swing.JButton();
        attendanceButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        outputPanel = new javax.swing.JPanel();
        inputPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Header Panel"));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setText("Student-Seminar Recording System");

        aboutUsButton.setText("About Us");
        aboutUsButton.addActionListener(this::aboutUsButtonActionPerformed);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(aboutUsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                    .addComponent(aboutUsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        sidePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Side Panel"));

        studentButton.setText("Student");
        studentButton.addActionListener(this::studentButtonActionPerformed);

        seminarButton.setText("Seminar");
        seminarButton.addActionListener(this::seminarButtonActionPerformed);

        attendanceButton.setText("Attendance");
        attendanceButton.addActionListener(this::attendanceButtonActionPerformed);

        addButton.setText("Add");
        addButton.addActionListener(this::addButtonActionPerformed);

        updateButton.setText("Update Selected");

        deleteButton.setText("Delete Selected");

        clearButton.setText("Clear");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Manage Tables");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Operate");

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seminarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(attendanceButton, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attendanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Panel"));
        outputPanel.setLayout(new java.awt.BorderLayout());

        inputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Panel"));
        inputPanel.setLayout(new java.awt.GridLayout(3, 6, 5, 5));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aboutUsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutUsButtonActionPerformed
        // TODO add your handling code here:
        // create about us frame object
        AboutUsFrame about = new AboutUsFrame();
        about.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutUsButtonActionPerformed

    private void studentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentButtonActionPerformed
        // TODO add your handling code here:
        currentState = 1;
        createInputFieldForStudent();
        displayTable();
    }//GEN-LAST:event_studentButtonActionPerformed

    private void seminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seminarButtonActionPerformed
        // TODO add your handling code here:
        currentState = 2;
        createInputFieldForSeminar();
        displayTable();
    }//GEN-LAST:event_seminarButtonActionPerformed

    private void attendanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceButtonActionPerformed
        // TODO add your handling code here:
        currentState = 3;
        createInputFieldForAttendance();
        displayTable();
    }//GEN-LAST:event_attendanceButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        switch(currentState){
            case 1:
                addEntryForStudent();
                break;
            case 2:
                addEntryForSeminar();
                break;
            case 3:
                addEntryForAttendance();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Please add to tables first");
        }
    }//GEN-LAST:event_addButtonActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutUsButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton attendanceButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JButton seminarButton;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JButton studentButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
