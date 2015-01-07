package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dataObjects.GroupedCourse;
import dataObjects.GroupedCourse;

public class CourseAdministratorDB {
	
	public static boolean checkLoginAdmin(String uname, String pwd) throws Exception {
	       
        String query = "SELECT * FROM admin WHERE username = '" + uname
                + "' AND password=" + "'" + pwd + "'";
        
       return executeIsAvaliable(query);
}
public static ArrayList<GroupedCourse> getCoursesAdmin() throws SQLException, Exception
{ 
	ArrayList<GroupedCourse> courseData = new ArrayList<GroupedCourse>();
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
        	 GroupedCourse courseObject = new GroupedCourse();
        	 courseObject.setId(Integer.parseInt(rs.getString(1)));
        	 courseObject.setName(rs.getString(2));
        	 courseObject.setGroup(rs.getString(3));
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

public static boolean insertGroup(String name, int group) throws SQLException, Exception {
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
        String query = "UPDATE course SET name='"+newname+"' WHERE name='"+oldname+"'";
        System.out.print(query);
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

public static boolean insertCourse(String name, int group) throws SQLException, Exception {
    boolean insertStatus = false;
    Connection dbConn = null;
    try {
         dbConn = DBConnection.createConnection();
        
        Statement stmt = dbConn.createStatement();
        String query = "INSERT INTO course (name, group_id) values ('"+name+"', '"+group+"')";
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
        	 groupedCourseObject.setId(Integer.parseInt(rs.getString(2)));
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

public static boolean deleteGroup(int id) throws SQLException, Exception {
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



public static boolean deleteUsers(String name) throws SQLException, Exception {
    boolean insertStatus = false;
    Connection dbConn = null;
    try {
         dbConn = DBConnection.createConnection();
        
        Statement stmt = dbConn.createStatement();
        String query = "DELETE FROM USER WHERE id_course = ANY(SELECT id_course FROM course where name='"+name+"')";
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

public static boolean deleteCourse(String name) throws SQLException, Exception {
    boolean insertStatus = false;
    Connection dbConn = null;
    try {
         dbConn = DBConnection.createConnection();
        
        Statement stmt = dbConn.createStatement();
        String query = "DELETE FROM `course` WHERE name='"+name+"'";
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
