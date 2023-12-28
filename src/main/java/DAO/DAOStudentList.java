package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.mysql.cj.log.Log;

import model.StudentList;
import util.DBConnection;

public class InvalidIntegerInputException extends RuntimeException {
	public InvalidIntegerInputException(String message){
		super(message);
	}
}

public class DAOStudentList {	
	static Logger log = Logger.getLogger(DAOStudentList.class);
	
	public static void scanningNoOfRecordsToBeInserted() {
		
		Scanner sc= null;
		System.out.println("Enter the no. of records you want to store in the DB?");
		log.info("No. of records to be stored in the SQL Database scanning..");
			int noOfRecords=0;
				try {
					sc = new Scanner(System.in);	
					noOfRecords = sc.nextInt();
						if (noOfRecords<0) {
								throw new InvalidIntegerInputException("Input must be a positive integer!");
						}
					} catch(InvalidIntegerInputException e) {
						log.error("Error Details ::" + e.getStackTrace());
					} catch (Exception e) {
						log.error("An unexpected error ::" + e.getMessage());
					}
		log.info( noOfRecords + " records will be added to the Database.");
		log.info("Student Details scanning..");
		for(int i=0; i<noOfRecords; i++) {
			System.out.println("Enter Student Name.");
			String input0 = sc.next();
			log.info("Name scanned: " + input0);
	
			System.out.println("Enter Student Marks.");
			Double input1 = sc.nextDouble();
			log.info("Marks scanned: " + input1);
			
			System.out.println("Enter Student RollNo.");
			int input2= sc.nextInt();
			log.info("RollNo scanned: " + input1);
			
			StudentList stdlist = new StudentList (input0, input1, input2);
				
			//1. store students records into database
			DAOStudentList.insertIntoDB(stdlist, i);
		}
		sc.close();
	}
	
	
	
	public static void insertIntoDB(StudentList stdlist, int i) {
		
		log.info("record insertion into database -> insertIntoDB invoked");
			Connection conn = null;
			PreparedStatement ps = null;			
			String SQL = "insert into studentlist (studentname, marks, rollno)" + "values (?,?,?)";
				try {
					conn = DBConnection.connectionToDatabase();
					ps = conn.prepareStatement(SQL);
					ps.setString(1, stdlist.getStdName());
					ps.setDouble(2, stdlist.getStdMarks());
					ps.setInt(3, stdlist.getStdRollno());
					int count = ps.executeUpdate();
						if (count == 1) {
							log.info(i+1 + " student record insertion completed.");
						}
					
					} catch (SQLException e) {
							log.error(e.getStackTrace());
					} finally {
						try {
							ps.close();
							} catch (SQLException e) {
								log.error(e.getStackTrace());
							}
						
						try {
							if (conn != null) {
								conn.close();
							}
							} catch (SQLException e) {
								log.error(e.getStackTrace());
							}
					}
		}
	
	
	
	
	public static List<StudentList> fetchAndStore(){
		
		log.info("Fetching all students records from the SQL Database -> fetchAndStore invoked");
		
		List<StudentList> std = new ArrayList<StudentList>();
		Connection conn = null;
		PreparedStatement ps = null;			
			String SQL = "select * from studentlist";
				try {
					conn = DBConnection.connectionToDatabase();
					ps = conn.prepareStatement(SQL);
					 ResultSet rs = ps.executeQuery();
					 	while(rs.next()) {
					 		StudentList student = new StudentList();
					 		
					 		student.setStdName(rs.getString(1));
					 		student.setStdMarks(rs.getDouble(2));
					 		student.setStdRollno(rs.getInt(3));
					 		
					 		std.add(student);
					 	}
					 	
					 log.info("Fetching records from the SQL Database finished!");
						
					} catch(SQLException e){
						log.error(e.getStackTrace());					
					} finally {
						try {
							ps.close();
							} catch (SQLException e) {
								log.error(e.getStackTrace());
							}
						
						try {
							if (conn != null) {
								conn.close();
							}
							} catch (SQLException e) {
								log.error(e.getStackTrace());
							}
					}
		
		return std;	
	}
	
	
	
	public static List<StudentList> filterStudents(List<StudentList> std) {
		
		log.info("retaining the students records whose marks > 90) -> filterStudents invoked");
		List<StudentList> newStdList = new ArrayList<StudentList>();
			for(int i=0; i<std.size(); i++) {		
					if (std.get(i).getStdMarks() > 90) {
						newStdList.add(std.get(i));
					}
				}
		
		log.info("Filtering records finished--");
		return newStdList;
	}

	
	
	public static void displayListOfStudents(List<StudentList> newstdlist) {
		log.info("Records from the ArrayList -> displayListOfStudents invoked");
		for(int i=0; i<newstdlist.size(); i++) {		
			
			
			log.info(newstdlist.get(i).getStdName() + " | ");
			log.info(newstdlist.get(i).getStdMarks() + " | ");
			log.info(newstdlist.get(i).getStdRollno());		
			System.out.println();
			}
		log.info("Displaying records finished--");
	}
		
}
	
	
	


