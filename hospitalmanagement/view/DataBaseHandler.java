/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagement.view;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tanaka
 */
public final class DataBaseHandler {

    private static DataBaseHandler handle = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;//execute statements e.g insert

    private DataBaseHandler() {
        createConnection();
        setupMedicineTable();
        setupDoctorsTable();
        setupPatientsTable();
    }

    public static DataBaseHandler getInstance() {// to use same database for each table created
        if (handle == (null)) {
            handle = new DataBaseHandler();
        }
        return handle;
    }

    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void setupMedicineTable() {//create med table
        String TABLE_NAME = "MEDICINE";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists ");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "      id varchar(3) primary key,\n"
                        + "      medname varchar(50),\n"
                        + "      groupname varchar(50),\n"
                        + "      expirydate date,\n"
                        + "      quantity varchar(3)"
                        + " )");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage() + "...setupDatabase");
        } finally {

        }
    }

    void setupDoctorsTable() {//create doc table
        String TABLE_NAME = "DOCTORS";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists ");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "      docid varchar(6) primary key,\n"
                        + "      docname varchar(50),\n"
                        + "      dateofbirth date,\n"
                        + "      phone_number varchar(20),\n"
                        + "      email varchar(70),\n"
                        + "      home_address varchar(70)"
                        + " )");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage() + "...setupDatabase");
        } finally {

        }
    }

    void setupPatientsTable() {//create patient table
        String TABLE_NAME = "PATIENTS";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists ");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "      id varchar(6) primary key,\n"
                        + "      name varchar(50),\n"
                        + "      dateofbirth date,\n"
                        + "      gender varchar(6),\n"
                        + "      blood_group varchar(2),\n"
                        + "      phone_number varchar(20),\n"
                        + "      docname varchar(6),\n"
                        + "      medname varchar(3),\n"
                        + "      FOREIGN KEY(docname) REFERENCES DOCTORS(docid),\n"
                        + "      FOREIGN KEY(medname) REFERENCES MEDICINE(id)"
                        + " )");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage() + "...setupDatabase");
        } finally {

        }
    }

    public boolean execAction(String qw) {//for function use e.g inset data 
        try {
            stmt = conn.createStatement();
            stmt.execute(qw);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }

    public ResultSet execQuery(String qu) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(qu);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }

    public boolean deletemed(Medicine medicine) {
        try {
            String DeleteStatement = "DELETE FROM MEDICINE WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(DeleteStatement);
            stmt.setString(1, medicine.getId());
            int res = stmt.executeUpdate();
            System.out.println(res);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateMed(Medicine medicine) {
        try {
            String update = "UPDATE MEDICINE SET MEDNAME=?, GROUPNAME=?, EXPIRYDATE=?, QUANTITY=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getGroup());
            stmt.setString(3, medicine.getExpirydate());
            stmt.setString(4, medicine.getQuantity());
            stmt.setString(5, medicine.getId());
            int res = stmt.executeUpdate();
            return (res > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteDoc(Doctor doc) {
        try {
            String DeleteStatement = "DELETE FROM DOCTORS WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(DeleteStatement);
            stmt.setString(1, doc.getId());
            int res = stmt.executeUpdate();
            System.out.println(res);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateDoc(Doctor doc) {
        try {
            String update = "UPDATE DOCTORS SET DOCNAME=?, DATEOFBIRTH=?, PHONE_NUMBER=?, EMAIL=?, HOME_ADDRESS=? WHERE DOCID=?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setString(1, doc.getName());
            stmt.setString(2, doc.getDob());
            stmt.setString(3, doc.getContact());
            stmt.setString(4, doc.getEmail());
            stmt.setString(5, doc.getHomeaddress());
            stmt.setString(6, doc.getId());
            int res = stmt.executeUpdate();
            return (res > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean deletePat(Patient pat) {
        try {
            String DeleteStatement = "DELETE FROM PATIENTS WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(DeleteStatement);
            stmt.setString(1, pat.getId());
            int res = stmt.executeUpdate();
            System.out.println(res);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updatePat(Patient pat) {
        try {
            String update = "UPDATE PATIENTS SET NAME=?, DATEOFBIRTH=?, GENDER=?, BLOOD_GROUP=?, PHONE_NUMBER=?, DOCNAME=?, MEDNAME=? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setString(1, pat.getName());
            stmt.setString(2, pat.getDob());
            stmt.setString(3, pat.getGender());
            stmt.setString(4, pat.getBloodgroup());
            stmt.setString(5, pat.getPhonenumber());
            stmt.setString(6, pat.getDocid());
            stmt.setString(7, pat.getMedid());
            stmt.setString(8, pat.getId());
            int res = stmt.executeUpdate();
            return (res > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
