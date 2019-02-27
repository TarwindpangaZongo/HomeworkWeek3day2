package com.example.homeworkweek3day2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_COMPLETED_HOURS;
import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_EXPECTED_GRAD_YEAR;
import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_GPA;
import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_ID;
import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_MAJOR;
import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_MINOR;
import static com.example.homeworkweek3day2.StudentDatabaseContract.COLUMN_NAME;
import static com.example.homeworkweek3day2.StudentDatabaseContract.DATABASE_NAME;
import static com.example.homeworkweek3day2.StudentDatabaseContract.DATABASE_VERSION;
import static com.example.homeworkweek3day2.StudentDatabaseContract.TABLE_NAME;
import static com.example.homeworkweek3day2.StudentDatabaseContract.getWhereClauseById;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    public StudentDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(StudentDatabaseContract.createQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }

    //Insert a Student into the database
    public long insertStudentIntoDatabase(@NonNull Student student) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_NAME, student.getStudentName());
        contentValues.put(COLUMN_MAJOR, student.getStudentMajor());
        contentValues.put(COLUMN_MINOR, student.getStudentMinor());
        contentValues.put(COLUMN_EXPECTED_GRAD_YEAR, student.getStudentExpectedGradYear());
        contentValues.put(COLUMN_GPA, student.getStudentGPA());
        contentValues.put(COLUMN_COMPLETED_HOURS, student.getStudentCompletedHours());

        //insert the student into the table using contentValues
        return writeableDatabase.insert(TABLE_NAME, null, contentValues);
    }

    //Get All Students from Database and return an ArrayList
    public ArrayList<Student> getAllStudentsFromDatabase() {
        ArrayList<Student> returnStudentList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Get results from query and hold in cursor(iterable object for database operations
        Cursor cursor = readableDatabase.rawQuery(StudentDatabaseContract.getAllStudentsQuery(), null);
        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(COLUMN_MINOR));
                String year = cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED_GRAD_YEAR));
                String GPA = cursor.getString(cursor.getColumnIndex(COLUMN_GPA));
                String hours = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_HOURS));

                //add to list
                returnStudentList.add(new Student(name, major, minor, year, GPA, hours, id));
            } while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnStudentList;
    }

    //Get One entry from database
    public Student getStudentById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Student to return
        Student returnStudent = new Student();
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(StudentDatabaseContract.getOneStudentById(id), null);
        if(cursor.moveToFirst()){
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String major = cursor.getString(cursor.getColumnIndex(COLUMN_MAJOR));
            String minor = cursor.getString(cursor.getColumnIndex(COLUMN_MINOR));
            String year = cursor.getString(cursor.getColumnIndex(COLUMN_EXPECTED_GRAD_YEAR));
            String GPA = cursor.getString(cursor.getColumnIndex(COLUMN_GPA));
            String hours = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_HOURS));
            //set return Student
            returnStudent = new Student(name, major, minor, year, GPA, hours, idFromDB);
        }
        cursor.close();
        return returnStudent;
    }

    // update an item in the database
    public long updateStudenteInDatabase (@NonNull Student newStudentInfo){
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_NAME, newStudentInfo.getStudentName());
        contentValues.put(COLUMN_MAJOR, newStudentInfo.getStudentMajor());
        contentValues.put(COLUMN_MINOR, newStudentInfo.getStudentMinor());
        contentValues.put(COLUMN_EXPECTED_GRAD_YEAR, newStudentInfo.getStudentExpectedGradYear());
        contentValues.put(COLUMN_GPA, newStudentInfo.getStudentGPA());
        contentValues.put(COLUMN_COMPLETED_HOURS, newStudentInfo.getStudentCompletedHours());

        String whereClause =String.format(Locale.US,"WHERE %s = \"%s\"" + COLUMN_ID, newStudentInfo.getStudentId());

        //insert the student into the table using contentValues
        return writeableDatabase.update(TABLE_NAME, contentValues, whereClause,new String[]{String.valueOf(newStudentInfo.getStudentId())});
    }

    //delete entry(ies) from the database
    public  long deleteFromDatabaseById(String[] id){
        SQLiteDatabase sqliteDatabas = this.getWritableDatabase();

        return sqliteDatabas.delete(TABLE_NAME,getWhereClauseById()+id[0], null);
    }
}

