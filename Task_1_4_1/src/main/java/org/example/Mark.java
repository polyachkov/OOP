package org.example;

public class Mark {
    private int semester;
    private String subjectName;
    private int grade;

    public Mark(int semester, String subjectName, int grade) {
        this.grade = grade;
        this.semester = semester;
        this.subjectName = subjectName;
    }

    public int getSemester() {
        return semester;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getGrade() {
        return grade;
    }
}
