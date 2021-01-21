package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// Start transaction
			session.beginTransaction();
			
			// Get the instructor detail object
			int theID = 2;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theID);
			
			// Print the associated instructor
			System.out.println("Instructor: " + tempInstructorDetail.getInstructor());
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// Handles connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
