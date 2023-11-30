package org.example;

/**
 * text.
 */
public class Mark {
    private int semester;
    private String subjectName;
    private int grade;

    /**
     * text.
     */
    public Mark(int semester, String subjectName, int grade) {
        this.grade = grade;
        this.semester = semester;
        this.subjectName = subjectName;
    }

    /**
     * text.
     */
    public int getSemester() {
        return semester;
    }

    /**
     * text.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * text.
     */
    public int getGrade() {
        return grade;
    }
}
