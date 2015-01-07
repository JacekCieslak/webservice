package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.GroupedCourse;
import webService.Constants;
 
public class DBConnection {
   
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
  
    //User
    public static boolean checkLogin(String uname, String pwd) throws Exception {
       
                
            String query = "SELECT * FROM user WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "' AND status ='1'";
            
            return executeIsAvaliable(query);
         
    }
    
    
    
   
    public static boolean insertUser(String name, String surname, String uname, String pwd,String courseName, String groupId) throws SQLException, Exception {
        boolean insertStatus = false;
        try {
            String query = "INSERT into user(name, surname, username, password, id_course, status) values('"+name+ "',"+"'"
                    + surname + "','" + uname + "','" + pwd + "',"
            		+ "(SELECT id_course FROM course WHERE name='"+courseName+"' AND group_id='"+groupId+"')" + 
            				",false)";
           insertStatus = executeInsertQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw e;
        } 
        return insertStatus;
    }
    
    public static ArrayList<GroupedCourse> getCourses() throws Exception
    { 
    	ArrayList<GroupedCourse> courseData = new ArrayList<GroupedCourse>();
    	Connection dbConn = null;
    	try
    	{
    		dbConn = DBConnection.createConnection();
	        
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT name, count(name) FROM `specialization` GROUP BY name ";
	    	 
	    	 ResultSet rs = stmt.executeQuery(query);
	    	
	         while (rs.next())
	         {
	        	 GroupedCourse courseObject = new GroupedCourse();
	        	 courseObject.setName(rs.getString(1));
	        	 courseObject.setGroup(rs.getString(2));
	        	 courseData.add(courseObject);
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
    	return courseData;
    }
    
    //Admin

    public static boolean checkLoginAdmin(String uname, String pwd) throws Exception {
       
            String query = "SELECT * FROM admin WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "'";
            
           return executeIsAvaliable(query);
    }
    
    public static ArrayList<GroupedCourse> getGroupedCourse() throws Exception
    { 
    	ArrayList<GroupedCourse> coursesData = new ArrayList<GroupedCourse>();
    	Connection dbConn = null;
    	try
    	{
    		dbConn = DBConnection.createConnection();
	        
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT name, count(name) FROM `course` GROUP BY name ";
	    	 ResultSet rs = stmt.executeQuery(query);
	    	 int licznik = 1;
	         while (rs.next())
	         {
	        	 
	        	 GroupedCourse groupedCourseObject = new GroupedCourse();
	        	 groupedCourseObject.setId(licznik);
	        	 groupedCourseObject.setName(rs.getString(1));
	        	 groupedCourseObject.setGroup(rs.getString(2));
	        	 coursesData.add(groupedCourseObject);
	        	 licznik++;
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
    	return coursesData;
    }
    
    public static ArrayList<GroupedCourse> getCoursesAdmin() throws SQLException, Exception
    { 
    	ArrayList<GroupedCourse> specializationData = new ArrayList<GroupedCourse>();
    	Connection dbConn = null;
    	try
    	{
    		dbConn = DBConnection.createConnection();
	    	 Statement stmt = dbConn.createStatement();
	    	 String query = "SELECT id_course, name, group_id FROM `course`ORDER BY name, group_id ";
	    	 System.out.print(query);
	 		 ResultSet rs = stmt.executeQuery(query);

	         while (rs.next())
	         {
	        	 GroupedCourse specializationObject = new GroupedCourse();
	        	 specializationObject.setId(Integer.parseInt(rs.getString(1)));
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
    
    public static boolean insertGroup(String name, String group) throws SQLException, Exception {
        boolean insertStatus = false;
        try {
            String query = "INSERT INTO course(name, group_id) VALUES ('"+name+"','"+group+"')";
           insertStatus = executeInsertQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw e;
        } 
        return insertStatus;
    }
    
    public static boolean updateCourse(String oldname, String newname) throws SQLException, Exception {
        boolean insertStatus = false;
        try {
            String query = "UPDATE course SET name='"+newname+"' WHERE name="+oldname+")";
           insertStatus = executeInsertQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw e;
        } 
        return insertStatus;
    }
    
    
    public static boolean getGroup(int id, String name) throws Exception{
    	boolean status = false;
       
        try {
            String query = "SELECT id_course FROM course WHERE UPPER(name)=UPPER('"+name+"') AND group_id='"+id+"'";	
           status = executeIsAvaliable(query);
           System.out.print(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw e;
        }
        return status;
    }
    
    
    
    public static boolean getCourse(String name) throws Exception{
    	boolean status = false;
       
        try {
            String query = "SELECT name FROM course WHERE UPPER(name)=UPPER('"+name+"')";	
           status = executeIsAvaliable(query);
           System.out.print(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw e;
        }
        return status;
    }
    public static boolean getCourseGroups(String name) throws Exception{
    	boolean status = false;
       
        try {
            String query = "SELECT name FROM course WHERE UPPER(name)=UPPER('"+name+"')";
           status = executeIsAvaliable(query);
           System.out.print(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            throw e;
        }
        return status;
    }
    
    public static boolean updateCourse(int id, String name, String group) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
             dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query = "UPDATE course SET name='"+name+"', group_id ='"+group+"' WHERE id_course = '"+id+"'";
            int records = stmt.executeUpdate(query);
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
    
    public static boolean updateCoursesName(String name) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
             dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query = "UPDATE course SET name='"+name+"' WHERE UPPER(name) = UPPER('"+name+"')";
            System.out.print(query);
            int records = stmt.executeUpdate(query);
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
    
    
    
    public static boolean deleteCourse(int id) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
             dbConn = DBConnection.createConnection();
            
            Statement stmt = dbConn.createStatement();
            String query = "DELETE FROM `course` WHERE id_course='"+id+"'";
            int records = stmt.executeUpdate(query);
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
    
    
    private static boolean executeInsertQuery(String query) throws SQLException, Exception{
    	boolean status = false;
        Connection dbConn = null;
     //   System.out.println("try inserstUser" );
        try {
               
        	dbConn = DBConnection.createConnection();
            Statement stmt = dbConn.createStatement();
           
            int records = stmt.executeUpdate(query);
            System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                status = true;
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
        return status;
    }
    

    
    private static boolean executeIsAvaliable(String query) throws SQLException, Exception {
        boolean isUserAvailable = false;
        
       	Connection dbConn = null;
        	try
        	{
        		dbConn = DBConnection.createConnection();
    	    	 Statement stmt = dbConn.createStatement();
    	    	    	    	 
    	 		 ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                isUserAvailable = true;
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
        return isUserAvailable;
    }
}
