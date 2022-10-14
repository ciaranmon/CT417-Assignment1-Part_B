package lectureManagerDriver;

import lecture_manager.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LectManageSysTest {
    // Creating Lecture Management System object (driver)
    LectManageSys mgmtSystem = new LectManageSys();

    // Creating Course objects
    CourseProgramme ece = new CourseProgramme("Electronic & Computer Engineering",
            "2019-09-01", "2023-05-04");
    CourseProgramme csNit = new CourseProgramme("Computer Science & Information Technology",
            "2022-09-05", "2026-05-08");
    CourseProgramme commerce = new CourseProgramme("Commerce ", "2020-09-07", "2024-04-26");

    // Creating Modules objects
    CollegeModule softwareEng = new CollegeModule("Software Engineering III", "CT417");     // +*
    CollegeModule soc1 = new CollegeModule("System on Chip Design I", "EE451");             //  *
    CollegeModule soc2 = new CollegeModule("System on Chip Design II", "EE452");            //  * 4 ECE modules
    CollegeModule embeddedSys = new CollegeModule("Embedded Systems", "EE347");             //  *
    CollegeModule maths = new CollegeModule("Pure Maths", "MA410");                         // + 2 CS&IT modules
    CollegeModule accounting = new CollegeModule("Accounting", "AY322");                    //   - 1 commerce module

    // Creating Lecturers for each module
    Lecturer michael = new Lecturer("Michael", "1990-08-24", 1029384756L);
    Lecturer fearghal = new Lecturer("Fearghal", "1989-07-23", 5019283746L);
    Lecturer peter = new Lecturer("Peter", "1988-06-22", 5647382910L);
    Lecturer yuxiang = new Lecturer("Yuxiang", "1987-05-23", 6574839201L);
    Lecturer john = new Lecturer("John", "1990-04-22", 1627384950L);

    // Creating Student objects
    Student ciaran = new Student("Ciaran", "2000-09-11", 123456789L, ece);
    Student eve = new Student("Eve", "2000-11-28", 987654321L, ece);
    Student aaron = new Student("Aaron", "2001-02-12", 214365879L, csNit);
    Student dara = new Student("Dara", "2000-08-23", 907856341L, commerce);


    @Test
    public void addingAllObjects() {
        // Adding modules to each of the courses
        ece.addModule(softwareEng);
        ece.addModule(soc1);
        ece.addModule(soc2);
        ece.addModule(embeddedSys);
        csNit.addModule(softwareEng);
        csNit.addModule(maths);
        commerce.addModule(accounting);

        // Giving each module a lecturer
        softwareEng.setLecturer(michael);
        soc1.setLecturer(fearghal);
        soc2.setLecturer(fearghal);
        embeddedSys.setLecturer(peter);
        maths.setLecturer(yuxiang);
        accounting.setLecturer(john);

        // Enrolling each student in a course
        ciaran.setCourse(ece);
        eve.setCourse(ece);
        aaron.setCourse(csNit);
        dara.setCourse(commerce);


        // Adding each object to the driver object
        // Courses
        Assertions.assertTrue(mgmtSystem.addNewCourse(ece));
        Assertions.assertTrue(mgmtSystem.addNewCourse(csNit));
        Assertions.assertTrue(mgmtSystem.addNewCourse(commerce));
        // Lecturers
        Assertions.assertTrue(mgmtSystem.addNewLecturer(michael));
        Assertions.assertTrue(mgmtSystem.addNewLecturer(fearghal));
        Assertions.assertTrue(mgmtSystem.addNewLecturer(peter));
        Assertions.assertTrue(mgmtSystem.addNewLecturer(yuxiang));
        Assertions.assertTrue(mgmtSystem.addNewLecturer(john));
        // Students
        Assertions.assertTrue(mgmtSystem.addNewStudent(ciaran));
        Assertions.assertTrue(mgmtSystem.addNewStudent(eve));
        Assertions.assertTrue(mgmtSystem.addNewStudent(aaron));
        Assertions.assertTrue(mgmtSystem.addNewStudent(dara));


        // Checking composition relationships between classes
        // Students
        Assertions.assertEquals(ece, ciaran.getCourse());
        Assertions.assertEquals(ece, eve.getCourse());
        Assertions.assertEquals(csNit, aaron.getCourse());
        Assertions.assertEquals(commerce, dara.getCourse());

        Assertions.assertEquals(ece.getModuleList(), ciaran.getModules());
        Assertions.assertEquals(ece.getModuleList(), eve.getModules());
        Assertions.assertEquals(csNit.getModuleList(), aaron.getModules());
        Assertions.assertEquals(commerce.getModuleList(), dara.getModules());

        Assertions.assertEquals(dara.getUsername(), "Dara22");
        Assertions.assertEquals(aaron.getId(), 214365879L);

        // Courses
        Assertions.assertTrue(ece.getModuleList().contains(soc1));
        Assertions.assertTrue(csNit.getModuleList().contains(softwareEng));
        Assertions.assertTrue(commerce.getModuleList().contains(accounting));

        Assertions.assertTrue(ece.getStudentsEnrolled().contains(ciaran));
        Assertions.assertTrue(csNit.getStudentsEnrolled().contains(aaron));
        Assertions.assertTrue(commerce.getStudentsEnrolled().contains(dara));

        // Lecturers
        Assertions.assertTrue(michael.getModulesTeaching().contains(softwareEng));
        Assertions.assertTrue(fearghal.getModulesTeaching().contains(soc2));
        Assertions.assertTrue(yuxiang.getModulesTeaching().contains(maths));
        Assertions.assertTrue(john.getModulesTeaching().contains(accounting));

        // Modules
        Assertions.assertEquals(michael, softwareEng.getLecturer());
        Assertions.assertEquals(fearghal, soc1.getLecturer());
        Assertions.assertEquals(fearghal, soc2.getLecturer());
        Assertions.assertEquals(yuxiang, maths.getLecturer());
        Assertions.assertEquals(john, accounting.getLecturer());


        // Printing all objects
        mgmtSystem.printCourses();
        mgmtSystem.printStudents();
        mgmtSystem.printLecturers();
        mgmtSystem.printModules();
    }
}
