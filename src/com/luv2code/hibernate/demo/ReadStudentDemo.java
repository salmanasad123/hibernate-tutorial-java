package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating a new Student object");
            // create the Student object we want to save to database
            Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

            // start the transaction
            session.beginTransaction();

            // save the student object to database
            System.out.println("Saving the student object");
            System.out.println(tempStudent);
            session.save(tempStudent);

            // commit the transaction
            session.getTransaction().commit();

            // My new Code
            // Retrieve the data from database using the ID
            System.out.println("Saved Student Generated Id = " + tempStudent.getId());

            // get a new session and start the transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve the student based on the id: primary key
            System.out.println("\n Getting student with id :" + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println(myStudent);

            // commit the transaction
            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
