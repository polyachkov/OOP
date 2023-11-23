package org.example;

import java.util.*;

public class CreditBook {
    private List<Integer> semesters = new ArrayList<Integer>();
    private List<String> subjects = new ArrayList<String>();
    public List<Integer> grades = new ArrayList<Integer>();
    private List<Mark> marks = new ArrayList<Mark>();
    private int cvalification;
    private final String studentName;
    private final String studentGroup;


    public CreditBook(String studentName, String studentGroup) {
        this.studentName = studentName;
        this.studentGroup = studentGroup;
    }

    public int addMark(int semester, String subjectName, int grade) {
        Mark finded = null;
        for(Mark test : marks){
            if((test.getSemester() == semester) && (test.getSubjectName().equals(subjectName))){
                finded = test;
                break;
            }
        }
        if (finded != null || grade > 5 || grade < 0) {
            return -1;
        }
        Mark newMark = new Mark(semester, subjectName, grade);
        grades.add(grade);
        subjects.add(subjectName);
        semesters.add(semester);
        marks.add(newMark);
        return 0;
    }

    public int deleteMark(int semester, String subjectName, int grade) {
        Mark finded = null;
        for(Mark test : marks){
            if((test.getSemester() == semester) && (test.getSubjectName().equals(subjectName)) && (test.getGrade() == grade) ){
                finded = test;
                break;
            }
        }
        if (finded == null) {
            return -1;
        }
        marks.remove(finded);
        return 0;

    }

    public float avgMarks() {
        int sum = 0;
        for (Integer grade : grades) {
            sum += grade;
        }
        if (cvalification != 0) {
            sum += cvalification;
            return (float) sum / (grades.size() + 1);
        }
        return (float) sum / grades.size();
    }

    private int getNameIndex(List<String> names, String str) {
        int i = 0;
        for (String name : names) {
            if (name.equals(str)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private List<Integer> getLastMarks() {
        List<String> names = new ArrayList<String>();
        List<Integer> semesters = new ArrayList<Integer>();
        List<Integer> grades = new ArrayList<Integer>();
        for(Mark mark : marks ) {
            int index = getNameIndex(names, mark.getSubjectName());
            if (index == -1) {
                names.add(mark.getSubjectName());
                semesters.add(mark.getSemester());
                grades.add(mark.getGrade());
            } else {
                if (mark.getSemester() > semesters.get(index)) {
                   semesters.set(index, mark.getSemester());
                   grades.set(index, mark.getGrade());
                }
            }
        }
        return grades;
    }

    public boolean isRedDiploma() {
        if (cvalification != 5) {
            return false;
        }

        var lastGrades = getLastMarks();
        if ((float) Collections.frequency(lastGrades, 5) / lastGrades.size() < 0.75) {
            return false;
        }

        for (int grade : grades) {
            if (grade <= 3) {
                return false;
            }
        }

        return true;
    }

    public List<Mark> getMarks() {
        return List.copyOf(this.marks);
    }
}
