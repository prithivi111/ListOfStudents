package service;

import java.util.List;
import org.apache.log4j.Logger;
import DAO.DAOStudentList;
import model.StudentList;

public class Service {
	
	static Logger log = Logger.getLogger(Service.class.getName());
	
	public static void main(String[] args) {
		
		log.info("Inside the main method..");
				
		//User input of no of records
		DAOStudentList.scanningNoOfRecordsToBeInserted();
				
		log.info("Back inside the main method.");			
		//Qno.2 and Qno.3 fetch students records and store inside ArrayList
		List<StudentList> std = null;
			try {
			   std = DAOStudentList.fetchAndStore();
			   log.info("Back inside the main method.");
				} 
			catch (Exception e) {
				log.error(e.getStackTrace());
				}
						
		//Qno.4 filter students having marks greater than 90
		List<StudentList> newstdlist = null;
			try {
				newstdlist = DAOStudentList.filterStudents(std);
				log.info("Back inside the main method.");
			}catch(Exception e) {
				log.error(e.getStackTrace());
				}
				 
		
		//Qno.5 Display list of students who has marks greater than 90
			DAOStudentList.displayListOfStudents(newstdlist);
			log.info("Inside main method again....");
			log.info("Terminate!");
	}
}
