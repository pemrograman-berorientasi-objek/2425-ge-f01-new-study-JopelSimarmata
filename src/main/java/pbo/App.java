package pbo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pbo.model.Course;
import pbo.model.Student;


/**
 * Main class
 *
 */



public class App {

  private static EntityManager em;
  private static EntityManagerFactory emf;

  public static ArrayList<Student> Students = new ArrayList<Student>();
  public static ArrayList<Course> Courses = new ArrayList<Course>();

  public static void main(String[] args) {
    

    emf = Persistence.createEntityManagerFactory("study_plan_pu");
    em = emf.createEntityManager();
    deleteDatabase();

    Scanner scn = new Scanner(System.in);

    // your codes

    while(true){
      String command = scn.nextLine();

      if(command.equalsIgnoreCase("---")){
        break;
      }

      String[] data = command.split("#");

      switch(data[0]){
        case "student-add":
          Boolean cek = false;
          for (Student student : Students) {
            if (student.getNim().equals(data[1])) {
                cek = true;
                break;
            }
          }
          
          if(cek == false){
            Student newstudent = new Student(data[1], data[2], data[3]);
            Students.add(newstudent);
            em.getTransaction().begin();
            em.persist(newstudent);
            em.getTransaction().commit();
          }
          break;
        case "course-add":
          Boolean cek2 = false;
          for (Course course : Courses) {
              if (course.getKode().equals(data[1])) {
                  cek2 = true;
                  break;
              }
          }
          
          if(cek2==false){
            Course newcourse = new Course(data[1], data[2], data[3], data[4]);
            Courses.add(newcourse);
            em.getTransaction().begin();
            em.persist(newcourse);
            em.getTransaction().commit();
          }
          break;
        case "student-show-all":
          ShowStudent();
          break;
        case "course-show-all":
          ShowCourse();
          break;
        case "enroll":
          enroll(data[1],data[2]);
          break;
        case "student-show":
          studentShow(data[1]);





      }
    }
  }
      public static void ShowStudent() {
        em.getTransaction().begin();
        String query = "SELECT s FROM Student s ORDER BY s.nim ASC";
        List<Student> students = em.createQuery(query, Student.class)
                      .getResultList();

        for (Student s : students) {
          System.out.println(s.toString());
        }
        em.getTransaction().commit();
      }

      public static void ShowCourse() {
        em.getTransaction().begin();
        String query = "SELECT c FROM Course c ORDER BY c.kode ASC";
        List<Course> students = em.createQuery(query, Course.class)
                      .getResultList();

        for (Course s : students) {
          System.out.println(s.toString());
        }
        em.getTransaction().commit();
      }

      private static void deleteDatabase(){
          em.getTransaction().begin();
          String jpql_student = "DELETE FROM Student s";
          String jpql_course = "DELETE FROM Course c";

          int affected = em.createQuery(jpql_student).executeUpdate();
          affected += em.createQuery(jpql_course).executeUpdate();
          em.flush();
          em.getTransaction().commit();

      }

      private static void findStudent(String nim){
        em.getTransaction().begin();
        String jpql = "SELECT s FROM Student s WHERE s.nim = :nim";
        Student student = em.createQuery(jpql, Student.class).setParameter("nim", nim).getSingleResult();
        em.getTransaction().commit();
      }

      private static void enroll(String nim, String kode){


        em.getTransaction().begin();
          String queryCourse = "SELECT c FROM Course c WHERE c.kode = :kode";
          String queryStudent = "SELECT s FROM Student s WHERE s.nim = :nim";
          Student student = em.createQuery(queryStudent, Student.class).setParameter("nim", nim).getSingleResult();
          Course course = em.createQuery(queryCourse,Course.class).setParameter("kode", kode).getSingleResult();
          student.SetCourse(course);
          course.setStudent(student);
          em.getTransaction().commit();
        
      }

      private static void studentShow(String nim){
        em.getTransaction().begin();
        String query = "SELECT s FROM Student s WHERE s.nim = :nim";
        List<Student> students = em.createQuery(query, Student.class).setParameter("nim", nim).getResultList();
        
        for(Student s : students){
          System.out.println(s);
          List<Course> coursess = s.getCourses();
          coursess.sort(Comparator.comparing(Course::getKode));
          for (Course c : coursess){
            System.out.println(c);
          }
        }
        em.getTransaction().commit();


      }
}
