package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// Create the objects
			Instructor tempInstructor = new Instructor("Madhu","Patel","madhu@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com",
																		 "Guitar");
				
			// Associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// Start transaction
			session.beginTransaction();
			
			// Save the instructor (also saves instructor_detail because of cascade configs)
			session.save(tempInstructor);
			
			// Commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
