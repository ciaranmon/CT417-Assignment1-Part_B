package lectureManagerDriver;

import lecture_manager.*;
import java.util.ArrayList;

public class LectManageSys {
    private final ArrayList<CourseProgramme> courses;
    private final ArrayList<CollegeModule> modules;
    private final ArrayList<Student> students;
    private final ArrayList<Lecturer> lecturers;

    public LectManageSys() {
        courses = new ArrayList<>();
        modules = new ArrayList<>();
        students = new ArrayList<>();
        lecturers = new ArrayList<>();
    }

    public boolean addNewCourse(CourseProgramme newCourse) {
        for(CollegeModule m : newCourse.getModuleList()) {
            addNewModule(m);
        }
        if(!courses.contains(newCourse)) {
            return courses.add(newCourse);     // Only add new course if it isn't already added
        } else {
            return false;
        }
    }
    private void addNewModule(CollegeModule newModule) {
        modules.add(newModule);
    }
    public boolean addNewStudent(Student newStudent) {
        return students.add(newStudent);
    }
    public boolean addNewLecturer(Lecturer newLecturer) {
        return lecturers.add(newLecturer);
    }

    public void printCourses() {
        System.out.println("------ All Courses ------");
        for(CourseProgramme cp : courses) {
            System.out.print(cp);
            System.out.println(cp.getName() + " Modules:");
            for(CollegeModule m : cp.getModuleList()) {
                System.out.print(m);
            }
            System.out.println();
        }
    }

    public void printStudents() {
        System.out.println("------ All Students ------");
        for(Student s : students) {
            System.out.println("Student: " + s.getName());
            System.out.println("\tUsername: " + s.getUsername());
            System.out.print("\t" + s.getName() + "'s " + s.getCourse());
            System.out.println(s.getName() + "'s Modules:");
            for(CollegeModule m : s.getModules()) {
                System.out.print(m);
            }
            System.out.println();
        }
    }

    public void printLecturers() {
        System.out.println("------ All Lecturers ------");
        for(Lecturer l : lecturers) {
            System.out.print(l);
            if(!l.getModulesTeaching().isEmpty()) {
                System.out.println(l.getName() + " teaches:");
                for(CollegeModule m : l.getModulesTeaching()) {
                    System.out.println("\t" + m.getName());
                }
            }
            System.out.println("\n");
        }
    }

    public void printModules() {
        System.out.println("------ All Modules ------");
        for(CollegeModule m : modules) {
            System.out.print(m);
            if(!m.getCourses().isEmpty()) {
                System.out.println("Courses associated with " + m.getName() + ":");
                for(CourseProgramme c : m.getCourses()) {
                    System.out.println("\t" + c.getName());
                }
            }
            if(!m.getStudents().isEmpty()) {
                System.out.println("Students Enrolled in " + m.getName() + ":");
                for(Student s : m.getStudents()) {
                    System.out.println("\t" + s.getName());
                }
            }
            System.out.println();
        }
    }
}
