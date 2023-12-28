package model;

/*1. create student pojo class (marks need to be there )
2.  store into the database;
3. fetch all student records and store inside the arraylist;
4. from the arraylist need to filter out the student having marks greater than 90%
5. and prepare a final list of students who are achieving more than 90%

*/
public class StudentList {	
	
	private String stdName;
	private double stdMarks;
	private int stdRollno;
	
 
public StudentList(String stdName, double stdMarks, int stdRollno) {
		super();
		this.stdName = stdName;
		this.stdMarks = stdMarks;
		this.stdRollno = stdRollno;
	}


public StudentList() {
}


public String getStdName() {
	return stdName;
}


public void setStdName(String stdName) {
	this.stdName = stdName;
}


public double getStdMarks() {
	return stdMarks;
}


public void setStdMarks(double stdMarks) {
	this.stdMarks = stdMarks;
}


public int getStdRollno() {
	return stdRollno;
}


public void setStdRollno(int stdRollno) {
	this.stdRollno = stdRollno;
}


}

