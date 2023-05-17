package org.example;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;


    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }

    /**
     * 핵심 사항
     * 이수한 과목을 전달하여 평균 학점 계산을 요청 --> 학점 계산기 --> (학점수×교과목 평점)의 합계 --> 과목(코스) => 일급 컬랙션을 통해서
     *                                                     --> 수강신청 총학점 수        --> 과목(코스) => 일급 컬랙션을 통해서
     */
    public double calculateGrade() {
        // (학점수×교과목 평점)의 합계
        double totalMultipliedCreditCourseGrade =  courses.multiplyCreditAndCourseGrade();

        int totalCompleteCredit = courses.calculateTotalComplatedCredit();
        // 수강신청 총학점 수

        return totalMultipliedCreditCourseGrade / totalCompleteCredit;
    }
}
