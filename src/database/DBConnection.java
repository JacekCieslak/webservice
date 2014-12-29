package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.specializationObjects;
import webService.Constants;
 
public class DBConnection {
    /**
     * Method to create DB Connection
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } catch (Exception e) {
        	System.out.println("createConnection /n"+e);
            throw e;
        } finally {
            return con;
        }
    }
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public static boolean checkLogin(String uname, String pwd) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "' AND status ='1'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }
    
    
    
    /**
     * Method to insert uname and pwd in DB
     * 
     * @param name
     * @param uname
     * @param pwd
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser(String name, String surname, String uname, String pwd, String idspe) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        System.out.println("try inserstUser" );
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	System.out.println("Poroblem z po³¹czeniem: "+ e);
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into user(name, surname, username, password, id_specialization, status) values('"+name+ "',"+"'"
                    + surname + "','" + uname + "','" + pwd + "','" + idspe + "',false)";
            System.out.println(query);
            int records = stmt.executeUpdate(query);
            System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
        	System.out.println(sqle);
            sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }
    
    public static ArrayList<specializationObjects> getSpejalizations() throws SQLException, Exception
    { 
    	ArrayList<specializationObjects> specializationData = new ArrayList<specializationObjects>();
		 Connection dbConn = null;
    	try
    	{
    		 try {
                 	dbConn = DBConnection.createConnection();
	         } catch (Exception e) {
	                 // TODO Auto-generated catch block
	             System.out.println("Poroblem z po³¹czeniem: "+ e);
	             e.printStackTrace();
	         }
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT * FROM specialization ORDER BY name ";
	    	 ResultSet rs = stmt.executeQuery(query);
	         while (rs.next())
	         {
	        	 specializationObjects specializationObject = new specializationObjects();
	        	 specializationObject.setId(rs.getString(1));
	        	 specializationObject.setName(rs.getString(2));
	        	 specializationObject.setGroup(rs.getString(3));
	        	 specializationData.add(specializationObject);
	         }
	        
	     } catch (SQLException sqle) {
	        System.out.println(sqle);
	        sqle.printStackTrace();
	        throw sqle;
	     } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e);
	        if (dbConn != null) {
	        	dbConn.close();
	        }
	        throw e;
	     } finally {
	    	 if (dbConn != null) {
	    		 dbConn.close();
	    	 }
	     }
    	return specializationData;
    }
    
}
