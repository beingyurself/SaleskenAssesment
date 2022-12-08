package com.salesken.studentsReportingSystem;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class App 
{
    public static void main( String[] args )
    {
    	
    	Scanner sc = new Scanner(System.in);
    	StudentDAO stdo=new StudentDAO();
    	while(true)
    	{
    		System.out.println("1.Add Student"+"\n"+ "2.Add marks of Student"+"\n"+
    	"3.Average percentage of whole class in recent semester"+"\n"+"4.Average marks of students in a subject"+
    				"\n"+"5.Top 2 Student"+"\n"+"others for exist");
    		int choice=sc.nextInt();
    		switch (choice) {
			case 1:
				stdo.insertStudent();
				break;
			case 2:
				stdo.addMark();
				break;
			case 3:
				stdo.AvgPercentage();
				break;
			case 4:
				stdo.AvgMarks();
				break;
			case 5:
				stdo.readStudent();;
				break;
			default:
				System.out.println("Thankyou......");
				break;
			}
    	}
    
    }
}
