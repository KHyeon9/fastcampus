package org.example;

public class Course {
    private final String subject; // 과목명
    private final int credit; // 학점
    private final String grade; // 성적 (A+, A, B+ .....)

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public double multiplyCreditAndCourseGrade() {
        return credit * getGradeToNumber();
    }

    public int getCredit() {
        return credit;
    }

    public double getGradeToNumber() {
        double grade = switch (this.grade) {
            case "A+" -> 4.5;
            case "A" -> 4;
            case "B+" -> 3.5;
            case "B" -> 3;
            case "C+" -> 2.5;
            case "C" -> 2;
            default -> 0;
        };

        return grade;
    }
}
