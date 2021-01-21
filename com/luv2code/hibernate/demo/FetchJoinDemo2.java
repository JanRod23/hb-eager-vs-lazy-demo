package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class FetchJoinDemo2 {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			// Start transaction
			session.beginTransaction();
			
			// Option 2: Hibernate query with HQL
			int theID = 1;
			
			Query<Instructor> query = session.createQuery("SELECT i from Instructor i " 
												+ "JOIN FETCH i.courses WHERE i.id=:theInstructorId",
												Instructor.class);
			
			// Set parameter on query
			query.setParameter("theInstructorId", theID);
			
			// Execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			// Get courses for the specified instructor
			System.out.println("Instructor: " + tempInstructor);
			
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
