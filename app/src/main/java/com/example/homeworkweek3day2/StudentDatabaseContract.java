package com.example.homeworkweek3day2;

import android.util.Log;

import java.util.Locale;

public class StudentDatabaseContract {
    //Database name and default version
    public static final String DATABASE_NAME = "Student_db";
    public static final int DATABASE_VERSION = 1;
    //Database table name
    public static final String TABLE_NAME = "Student";
    //Columns in database names
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MAJOR = "major";
    public static final String COLUMN_MINOR = "minor";
    public static final String COLUMN_EXPECTED_GRAD_YEAR = "year";
    public static final String COLUMN_GPA = "gpa";
    public static final String COLUMN_COMPLETED_HOURS = "hours";
    public static final String COLUMN_ID = "id";

    //
    // Create the create table query for the database
    //
    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        //Query to create Table
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique primary key
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        //Add rest of the columns
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_MAJOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_MINOR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_EXPECTED_GRAD_YEAR);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_GPA);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_COMPLETED_HOURS);
        queryBuilder.append(" TEXT )");

        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }

    public static String getAllStudentsQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneStudentById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_ID, id);
    }

    public static String getWhereClauseById (){
        return String.format(Locale.US,"WHERE %S =",COLUMN_ID);
    }
}
