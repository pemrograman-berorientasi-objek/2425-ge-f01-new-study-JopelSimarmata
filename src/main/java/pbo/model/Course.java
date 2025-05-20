package pbo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name="kode")
    private String kode;
    @Column(name="nama")
    private String nama;
    @Column(name="semester")
    private String semester;
    @Column(name="kredit")
    private String kredit;

    @ManyToMany(targetEntity=Student.class, cascade=CascadeType.ALL)
    @JoinTable(name = "enrollments", joinColumns= @JoinColumn(name="course_id" , referencedColumnName="kode"), inverseJoinColumns=@JoinColumn(name="student_nim",referencedColumnName="nim"))
    private Set<Student> students;



    public Course(){
    }

    public void setStudent(Student students){
        this.students.add(students);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Course(String kode, String nama, String semester, String kredit){
        this.kode = kode;
        this.nama = nama; 
        this.semester = semester;
        this.kredit = kredit;   
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getKode(){
        return kode;
    }

    public void setKode(String kode){
        this.kode = kode;
    }

    public String getSemester(){
        return semester;
    }

    public void setSemester(String semester){
        this.semester = semester;
    }

    public String getKredit(){
        return kredit;
    }

    public void setKredit(String kredit){
        this.kredit = kredit;
    }

    @Override
    public String toString(){
        return this.kode + "|" + this.nama + "|" + this.semester + "|" + this.kredit;  
    }


    

    


    
    
}
