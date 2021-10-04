package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create Session Factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            // create 3 Student objects we want to save to database
            Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
            Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

            // start the transaction
            session.beginTransaction();

            // save the student object to database
            System.out.println("Saving the student object");
            // it will use the primary key and will auto-increment it, so each student will get a new id based on
            // auto increment feature of my sql
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit the transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
