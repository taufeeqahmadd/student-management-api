package com.kiet.studentmanagement.controller;

import com.kiet.studentmanagement.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    // Empty in-memory list
    private final List<Student> students = new ArrayList<>();


    // Q3(A) - GET ALL STUDENTS
    // GET /students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }


    // Q3(B) - GET STUDENT BY ID
    // GET /students/{studentId}
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(
            @PathVariable int studentId) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {
                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity
                .status(404)
                .body(Map.of(
                        "message", "Student not found"
                ));
    }


    // Q4 - ADD NEW STUDENT
    // POST /students
    @PostMapping
    public ResponseEntity<Student> addStudent(
            @RequestBody Student student) {

        students.add(student);

        return ResponseEntity
                .status(201)
                .body(student);
    }


    // Q5(A) - UPDATE STUDENT
    // PUT /students/{studentId}
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int studentId,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                student.setStudentId(studentId);
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                student.setBranch(updatedStudent.getBranch());
                student.setYear(updatedStudent.getYear());
                student.setCgpa(updatedStudent.getCgpa());

                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity
                .status(404)
                .body(Map.of(
                        "message", "Student not found"
                ));
    }


    // Q5(B) - DELETE STUDENT
    // DELETE /students/{studentId}
    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable int studentId) {

        boolean removed = students.removeIf(
                student -> student.getStudentId() == studentId
        );

        if (removed) {
            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Student deleted successfully"
                    )
            );
        }

        return ResponseEntity
                .status(404)
                .body(Map.of(
                        "message", "Student not found"
                ));
    }


    // Q6(A) - SEARCH BY BRANCH AND YEAR
    // GET /students/search?branch=CSE&year=2
    @GetMapping("/search")
    public List<Student> searchStudents(
            @RequestParam String branch,
            @RequestParam int year) {

        return students.stream()
                .filter(student ->
                        student.getBranch().equalsIgnoreCase(branch)
                                && student.getYear() == year
                )
                .collect(Collectors.toList());
    }


    // Q6(B) - FIND STUDENTS BY CGPA
    // GET /students/topper?cgpa=9.0
    @GetMapping("/topper")
    public List<Student> getToppers(
            @RequestParam double cgpa) {

        return students.stream()
                .filter(student ->
                        student.getCgpa() >= cgpa
                )
                .collect(Collectors.toList());
    }
}




//package com.kiet.studentmanagement.controller;
//
//
//
//import com.kiet.studentmanagement.model.Student;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//        import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/students")
//public class StudentController {
//
//    // In-memory List
//    private final List<Student> students = new ArrayList<>();
//
//
//    // Constructor - Add initial 5 students
//    public StudentController() {
//
//        students.add(new Student(
//                101,
//                "Rahul Sharma",
//                "rahul@kiet.edu",
//                "CSE(AI & ML)",
//                2,
//                8.65
//        ));
//
//        students.add(new Student(
//                102,
//                "Priya Singh",
//                "priya@kiet.edu",
//                "CSE",
//                2,
//                9.20
//        ));
//
//        students.add(new Student(
//                103,
//                "Arjun Verma",
//                "arjun@kiet.edu",
//                "IT",
//                3,
//                8.90
//        ));
//
//        students.add(new Student(
//                104,
//                "Neha Gupta",
//                "neha@kiet.edu",
//                "CSE",
//                2,
//                9.40
//        ));
//
//        students.add(new Student(
//                105,
//                "Rohan Kumar",
//                "rohan@kiet.edu",
//                "ECE",
//                1,
//                8.10
//        ));
//    }
//
//
//    // =========================================
//    // Q3(A) - GET ALL STUDENTS
//    // GET /students
//    // =========================================
//
//    @GetMapping
//    public List<Student> getAllStudents() {
//        return students;
//    }
//
//
//    // =========================================
//    // Q3(B) - GET STUDENT BY ID
//    // GET /students/{studentId}
//    // =========================================
//
//    @GetMapping("/{studentId}")
//    public ResponseEntity<?> getStudentById(
//            @PathVariable int studentId) {
//
//        for (Student student : students) {
//
//            if (student.getStudentId() == studentId) {
//                return ResponseEntity.ok(student);
//            }
//        }
//
//        return ResponseEntity
//                .status(404)
//                .body(Map.of(
//                        "message",
//                        "Student not found"
//                ));
//    }
//
//
//    // =========================================
//    // Q4 - ADD NEW STUDENT
//    // POST /students
//    // =========================================
//
//    @PostMapping
//    public ResponseEntity<Student> addStudent(
//            @RequestBody Student student) {
//
//        students.add(student);
//
//        return ResponseEntity
//                .status(201)
//                .body(student);
//    }
//
//
//    // =========================================
//    // Q5(A) - UPDATE STUDENT
//    // PUT /students/{studentId}
//    // =========================================
//
//    @PutMapping("/{studentId}")
//    public ResponseEntity<?> updateStudent(
//            @PathVariable int studentId,
//            @RequestBody Student updatedStudent) {
//
//        for (Student student : students) {
//
//            if (student.getStudentId() == studentId) {
//
//                student.setStudentId(studentId);
//                student.setName(updatedStudent.getName());
//                student.setEmail(updatedStudent.getEmail());
//                student.setBranch(updatedStudent.getBranch());
//                student.setYear(updatedStudent.getYear());
//                student.setCgpa(updatedStudent.getCgpa());
//
//                return ResponseEntity.ok(student);
//            }
//        }
//
//        return ResponseEntity
//                .status(404)
//                .body(Map.of(
//                        "message",
//                        "Student not found"
//                ));
//    }
//
//
//    // =========================================
//    // Q5(B) - DELETE STUDENT
//    // DELETE /students/{studentId}
//    // =========================================
//
//    @DeleteMapping("/{studentId}")
//    public ResponseEntity<?> deleteStudent(
//            @PathVariable int studentId) {
//
//        boolean removed = students.removeIf(
//                student -> student.getStudentId() == studentId
//        );
//
//        if (removed) {
//
//            return ResponseEntity.ok(
//                    Map.of(
//                            "message",
//                            "Student deleted successfully"
//                    )
//            );
//        }
//
//        return ResponseEntity
//                .status(404)
//                .body(Map.of(
//                        "message",
//                        "Student not found"
//                ));
//    }
//
//
//    // =========================================
//    // Q6(A) - SEARCH BY BRANCH AND YEAR
//    // GET /students/search?branch=CSE&year=2
//    // =========================================
//
//    @GetMapping("/search")
//    public List<Student> searchStudents(
//            @RequestParam String branch,
//            @RequestParam int year) {
//
//        return students.stream()
//                .filter(student ->
//                        student.getBranch().equalsIgnoreCase(branch)
//                                && student.getYear() == year
//                )
//                .collect(Collectors.toList());
//    }
//
//
//    // =========================================
//    // Q6(B) - FIND STUDENTS BY CGPA
//    // GET /students/topper?cgpa=9.0
//    // =========================================
//
//    @GetMapping("/topper")
//    public List<Student> getToppers(
//            @RequestParam double cgpa) {
//
//        return students.stream()
//                .filter(student ->
//                        student.getCgpa() >= cgpa
//                )
//                .collect(Collectors.toList());
//    }
//}