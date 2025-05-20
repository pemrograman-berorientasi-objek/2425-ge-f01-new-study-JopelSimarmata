package pbo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
    @Id
    @Column(name="nim")
    private String nim;
    @Column(name="nama")
    private String nama;
    @Column(name="prodi")
    private String prodi;

    @ManyToMany(mappedBy="students", cascade=CascadeType.ALL)
    private List<Course> courses;


    
    public Student(){
    }

    public void SetCourse( Course courses) {
        this.courses.add(courses);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Student(String nim, String nama, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

        @Override
    public String toString(){
        return this.nim + "|" + this.nama + "|" + this.prodi;
    }

    


    
}
