package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            int studentId = 1;
            // start the transaction
            session.beginTransaction();

            // update the student based on student id
            // first we get the student with the id we provide from the database
            System.out.println("\n Getting student with student id of : " + studentId);

            Student tempStudent = session.get(Student.class, studentId);

            // update the student name to scooby
            // with setting first name its only updated in memory then we do commit to push the changes to database
            // because student is a persisted object in the database committing changes will save it back
            tempStudent.setFirstName("Scooby");

            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
