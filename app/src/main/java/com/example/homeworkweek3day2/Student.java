package com.example.homeworkweek3day2;

import android.os.Parcel;
import android.os.Parcelable;

public class Student  implements Parcelable {
    private String studentName;
    private String studentMajor;
    private String studentMinor;
    private String studentExpectedGradYear;
    private String studentGPA;
    private String studentCompletedHours;
    private int studentId;


    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentMinor='" + studentMinor + '\'' +
                ", studentExpectedGradYear='" + studentExpectedGradYear + '\'' +
                ", studentGPA='" + studentGPA + '\'' +
                ", studentCompletedHours='" + studentCompletedHours + '\'' +
                ", studentId=" + studentId +
                '}';
    }

    protected Student(Parcel in) {
        studentName = in.readString();
        studentMajor = in.readString();
        studentMinor = in.readString();
        studentExpectedGradYear = in.readString();
        studentGPA = in.readString();
        studentCompletedHours = in.readString();
        studentId = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Student() {
    }

    public Student(String studentName, String studentMajor, String studentMinor, String studentExpectedGradYear, String studentGPA, String studentCompletedHours, int studentId) {
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentMinor = studentMinor;
        this.studentExpectedGradYear = studentExpectedGradYear;
        this.studentGPA = studentGPA;
        this.studentCompletedHours = studentCompletedHours;
        this.studentId = studentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentName);
        dest.writeString(studentMajor);
        dest.writeString(studentMinor);
        dest.writeString(studentExpectedGradYear);
        dest.writeString(studentGPA);
        dest.writeString(studentCompletedHours);
        dest.writeInt(studentId);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentMinor() {
        return studentMinor;
    }

    public void setStudentMinor(String studentMinor) {
        this.studentMinor = studentMinor;
    }

    public String getStudentExpectedGradYear() {
        return studentExpectedGradYear;
    }

    public void setStudentExpectedGradYear(String studentExpectedGradYear) {
        this.studentExpectedGradYear = studentExpectedGradYear;
    }

    public String getStudentGPA() {
        return studentGPA;
    }

    public void setStudentGPA(String studentGPA) {
        this.studentGPA = studentGPA;
    }

    public String getStudentCompletedHours() {
        return studentCompletedHours;
    }

    public void setStudentCompletedHours(String studentCompletedHours) {
        this.studentCompletedHours = studentCompletedHours;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
