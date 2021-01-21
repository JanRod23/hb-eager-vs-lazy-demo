package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// Create the objects
			Instructor tempInstructor = new Instructor("Susan","Public","susan.public@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com",
																		 "Video Games");
				
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
			session.close();
			
			factory.close();
		}
	}

}
