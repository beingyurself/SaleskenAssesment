package com.salesken.studentsReportingSystem;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class StudentDAO {
private static Scanner sc;
private static Session ss;
Student stud;
	static
	{
		sc=new Scanner(System.in);
		Configuration cf= new Configuration();
			cf.configure("hibernate.cfg.xml");
		SessionFactory sf =cf.buildSessionFactory();
			ss=sf.openSession();
	}
	
	//Inserted Student
	void insertStudent()
	{
		System.out.println("Enter the Student rollno , name and semester (1 or 2)");
		 
		int rl=sc.nextInt();
		String n=sc.next();
		int sem=sc.nextInt();
		if(sem==1)
		{	
	        Semester1 sm=new Semester1();
			sm.setName(n);
		    sm.setRollno(rl);
		    System.out.println("Enter marks of English");
		    int eng=sc.nextInt();
		    if(eng>=0&&eng<=100)
		    sm.setEnglish(eng);
		    else
		    {
		    	System.err.println("Invalid mark");
		         return;
		    }
		    System.out.println("Enter marks of Maths");
		    int m=sc.nextInt();
		    if(m>=0&&m<=100)
		    sm.setMaths(m);
		    else
		    {
		    	System.err.println("Invalid mark");
		         return;
		    }    
		    System.out.println("Enter marks of Science");
		    int s=sc.nextInt();
		    if(s>=0&&s<=100)
		    sm.setScience(s);
		    else {
		    	System.err.println("Invalid mark");
		    	return;
		       }
		    stud=sm;
		}
		else if(sem==2) {
			Semester2 sm=new Semester2();
		sm.setName(n);
	    sm.setRollno(rl);
	    System.out.println("Enter marks of English");
	    int eng=sc.nextInt();
	    if(eng>=0&&eng<=100)
	    sm.setEnglish(eng);
	    else
	    {
	    	System.err.println("Invalid mark");
	         return;
	    }
	    System.out.println("Enter marks of Maths");
	    int m=sc.nextInt();
	    if(m>=0&&m<=100)
	    sm.setMaths(m);
	    else
	    {
	    	System.err.println("Invalid mark");
	         return;
	    }
	    System.out.println("Enter marks of Science");
	    int s=sc.nextInt();
	    if(s>=0&&s<=100)
	    sm.setScience(s);
	    else
	    {
	    	System.err.println("Invalid mark");
	         return;
	    }
	    stud=sm;
		}
		else
		{
			System.err.println("Invalid semester ");
			return;
		}	
		try
		{
			Transaction tr =ss.beginTransaction();
			ss.save(stud);
			tr.commit();
		}catch (Exception e) {
			System.out.println("Student detail is already Exits in DataBase");
			return;
		}
		System.out.println("Sucessfully Inserted...");
	}
	
	
	// update marks or add
	public void addMark() {
		System.out.println("Enter student rollno ");
		int rl=sc.nextInt();
		System.out.println("Enter semester 1 or 2");
		int sem=sc.nextInt();
		try {
		if(sem==1)
		{
			
			Semester1 sm=(Semester1)ss.get(Semester1.class, rl);
			Transaction tr =ss.beginTransaction();
			System.out.println("Enter subject "+"\n"+" 1 for English"+"\n"+" 2 for Maths"+"\n"+" 3 for Science");
			int sub =sc.nextInt();
			if(sub==1)
			{
				
				System.out.println("Enter marks");
				int e=sc.nextInt();
				if(e>=0&&e<=100)
				sm.setEnglish(e);
				else
					System.err.println("Invalid marks");
			}
			else if(sub==2)
			{
				
				System.out.println("Enter marks");
				int m=sc.nextInt();
				if(m>=0&&m<=100)
				sm.setMaths(m);
				else
					System.err.println("Invalid marks");
			}
			else if(sub==3)
			{
				
				System.out.println("Enter marks");
				int s=sc.nextInt();
				if(s>=0&&s<=100)
				sm.setScience(s);
				else
					System.err.println("Invalid marks");
			}
			else {
				System.err.println("Invalid subject");
			}
			}
		
			else if(sem==2)
			{
				
				Semester2 sm=(Semester2)ss.get(Semester2.class, rl);
				Transaction tr =ss.beginTransaction();
				System.out.println("Enter subject "+"\n"+" 1 for English"+"\n"+" 2 for Maths"+"\n"+" 3 for Science");
				int sub =sc.nextInt();
				if(sub==1)
				{
					
					System.out.println("Enter marks");
					int e=sc.nextInt();
					if(e>=0&&e<=100)
					sm.setEnglish(e);
					else
						System.err.println("Invalid marks");
				}
				else if(sub==2)
				{
					
					System.out.println("Enter marks");
					int m=sc.nextInt();
					if(m>=0&&m<=100)
					sm.setMaths(m);
					else
						System.err.println("Invalid marks");
				}
				else if(sub==3)
				{
					
					System.out.println("Enter marks");
					int s=sc.nextInt();
					if(s>=0&&s<=100)
					sm.setScience(s);
					else
						System.err.println("Invalid marks");
				}
				else {
					System.err.println("Invalid subject");
				}
			}
			else
			{
				System.err.println("Invalid semester ");
				return;
			}	
			
	
		}catch (Exception e) {
			System.err.println("this Student is not exist to Database");
			return;
		}
		
		
		System.out.println("SuccessFully Mark added");
	}
		
		
	
	//Average percentage of semester 2
	public void AvgPercentage() {
					
					int sum=0; 
					int count_student=0;
					Query qr=ss.createQuery("from Semester2");
					List ls=qr.list();
					for(Object obj:ls)
					{
						Semester2 s=(Semester2)obj;
					int sb =s.getEnglish()+s.getMaths()+s.getScience();
					sum=sum+sb/3;
					count_student++;
					}
					
					System.out.println("Average percentage : "+sum/count_student);
				}
	
	
	         //average marks of a subject
				public void AvgMarks() {
					
					int sum=0; 
					int count_sub=0;
					String qry="";
					System.out.println("Enter semester 1 or 2");
					int sem=sc.nextInt();
					if(sem==1)
					{
						qry="from Semester1";
					System.out.println("Enter subject "+"\n"+" 1 for English"+"\n"+" 2 for Maths"+"\n"+" 3 for Science");
					int sub=sc.nextInt();
					if(sub==1)
					{
						Query qr=ss.createQuery(qry);
						List ls=qr.list();
						for(Object obj:ls)
						{
				
							Semester2 s=(Semester2)obj;
						sum =sum+s.getEnglish();
						count_sub++;
					    }
					}
					else if(sub==2)
						{
							Query qr=ss.createQuery(qry);
							List ls=qr.list();
							for(Object obj:ls)
							{
								Semester2 s=(Semester2)obj;
							sum =sum+s.getMaths();
							count_sub++;
						    }
					    }
					else if(sub==3)
					{
						Query qr=ss.createQuery(qry);
						List ls=qr.list();
						for(Object obj:ls)
						{
							Semester2 s=(Semester2)obj;
						sum =sum+s.getEnglish();
						count_sub++;
					    }
						
				   }
					else
						System.err.println("Invalid subject");
			    }
					else if(sem==2)
					{
						qry="from Semester2";
					System.out.println("Enter subject "+"\n"+" 1 for English"+"\n"+" 2 for Maths"+"\n"+" 3 for Science");
					int sub=sc.nextInt();
					if(sub==1)
					{
						Query qr=ss.createQuery(qry);
						List ls=qr.list();
						for(Object obj:ls)
						{
				
							Semester2 s=(Semester2)obj;
						sum =sum+s.getEnglish();
						count_sub++;
					    }
					}
					else if(sub==2)
						{
							Query qr=ss.createQuery(qry);
							List ls=qr.list();
							for(Object obj:ls)
							{
								Semester2 s=(Semester2)obj;
							sum =sum+s.getMaths();
							count_sub++;
						    }
					    }
					else if(sub==3)
					{
						Query qr=ss.createQuery(qry);
						List ls=qr.list();
						for(Object obj:ls)
						{
							Semester2 s=(Semester2)obj;
						sum =sum+s.getEnglish();
						count_sub++;
					    }
						
				   }
					else {
						System.err.println("Invalid subject");
						return;
					}
			  }	
					else {
						System.err.println("Invalid semester");
                       return;
					    }
					System.out.println(sum/count_sub);
				}
				
				
				
						//Display Student
					void readStudent()
					{
						Student s1=new Student();
						Student s2=new Student();
						
						int fsum=0;
						int ssum=0; 
						int count_student=0;
						Query qr=ss.createQuery("from Semester2");
						List ls=qr.list();
						for(Object obj:ls)
						{
							Semester2 s=(Semester2)obj;
						int sb =(s.getEnglish()+s.getMaths()+s.getScience())/3;
					    if(sb>fsum)
					    {
					    	fsum=sb;
					    	s1=s;
						}
					    if(sb<fsum&&sb>ssum)
					    {
					    	ssum=sb;
					    	s2=s;
					    }
						}
						Query qr1=ss.createQuery("from Semester1");
						List ls1=qr1.list();
						for(Object obj:ls1)
						{
							Semester1 s=(Semester1)obj;
						int sb =(s.getEnglish()+s.getMaths()+s.getScience())/3;
					    if(sb>fsum)
					    {
					    	fsum=sb;
					    	s1=s;
						}
					    if(sb<fsum&&sb>ssum)
					    {
					    	ssum=sb;
					    	s2=s;
					    }
						}
				
				    System.out.println("Top 2 studends :"+"\n"+s1.getRollno()+" , "+s1.getName()+" , "+fsum 
				                             +"\n"+s2.getRollno()+" , "+s2.getName()+" , "+ssum);
				}
				}