package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            // start the transaction
            session.beginTransaction();

            // query students using HQL (Hibernate Query Language)
            // from student is the keyword we supply to query all students from database (instead of using
            // select * from) we use from followed by name of table
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            for (Student student : theStudents) {
                System.out.println(student);
            }

            // query students where lastName = 'Doe'
            // s is alias for Student
            List<Student> theStudents2 = session
                    .createQuery("from Student s where s.lastName='Doe'")
                    .getResultList();

            // display the students
            for(Student student: theStudents2){
                System.out.println(student);
            }

            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
