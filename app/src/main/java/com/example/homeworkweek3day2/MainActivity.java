package com.example.homeworkweek3day2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Constants
    public static final int REQUEST_CODE_FOR_MAIN = 427;
    public static final String KEY_SHARED_PREF = "shared_pref";
    public static final String KEY_LAST_ENTERED_NAME = "last_name";
    public static final String KEY_LAST_ENTERED_MAJOR = "last_major";
    public static final String KEY_LAST_ENTERED_MINOR = "last_minor";
    public static final String KEY_LAST_ENTERED_EXPECTED_GRAD_YEAR = "last_Expected_Grad_Year";
    public static final String KEY_LAST_ENTERED_GPA = "last_GPA";
    public static final String KEY_LAST_ENTERED_COMPLETED_HOURS = "last_Completed_Hours";
    public static final String MY_FILE_TXT = "myFile.txt";
    static final int READ_BLOCK_SIZE = 100;


    //Declare views
    TextView tvNameDisplay;
    TextView tvMajorDisplay;
    TextView tvMinorDisplay;
    TextView tvExpectedGradYeareDisplay;
    TextView tvGPADisplay;
    TextView tvCompletedHoursisplay;

    //Shared Preferences Object
    SharedPreferences sharedPreferences;
    //Student Database Declaration
    StudentDatabaseHelper studentDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);
        //instantiate the Student Database
        studentDatabaseHelper = new StudentDatabaseHelper(this);
        bindViews();
    }

    private void bindViews() {
        tvNameDisplay = findViewById(R.id.tvName);
        tvMajorDisplay = findViewById(R.id.tvMajor);
        tvMinorDisplay = findViewById(R.id.tvMinor);
        tvExpectedGradYeareDisplay = findViewById(R.id.tvExpectedGradYeare);
        tvGPADisplay = findViewById(R.id.tvGPA);
        tvCompletedHoursisplay = findViewById(R.id.tvCompletedHours);
    }

    //
    // Populate TextViews
    // @params Student Student info to populate
    // @return void
    //
    public void populateTextViews(@NonNull Student studentInfoToPopulate) {
        tvNameDisplay.setText(studentInfoToPopulate.getStudentName());
        tvMajorDisplay.setText(studentInfoToPopulate.getStudentMajor());
        tvMinorDisplay.setText(studentInfoToPopulate.getStudentMinor());
        tvExpectedGradYeareDisplay.setText(studentInfoToPopulate.getStudentExpectedGradYear());
        tvGPADisplay.setText(studentInfoToPopulate.getStudentGPA());
        tvCompletedHoursisplay.setText(studentInfoToPopulate.getStudentCompletedHours());
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnStartForResult:
                Intent explicitIntentEntry = new Intent(this, DataEntryActivity.class);
                startActivityForResult(explicitIntentEntry, REQUEST_CODE_FOR_MAIN);
                break;

            case R.id.btnStartForDatabase:
                Intent explicitIntentDatabase = new Intent(this, DatabaseActivity.class);
                startActivityForResult(explicitIntentDatabase, REQUEST_CODE_FOR_MAIN);
                break;

            case R.id.btnReadFromFile:
                //Read from file
                readFromFile();
                break;

            case R.id.btnWriteToFile:
                //Write to file
                writeToFile();
                break;


        }
    }

    //
    // Save to Shared Pref.
    // @param Student student object which we will save info to shared pref. from
    // @return void
    //
    public void saveMakeModelToSharedPref(@NonNull Student student) {
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(KEY_LAST_ENTERED_NAME, student.getStudentName());
        sharedPrefEditor.putString(KEY_LAST_ENTERED_MAJOR, student.getStudentMajor());
        sharedPrefEditor.putString(KEY_LAST_ENTERED_MINOR, student.getStudentMinor());
        sharedPrefEditor.putString(KEY_LAST_ENTERED_EXPECTED_GRAD_YEAR, student.getStudentExpectedGradYear());
        sharedPrefEditor.putString(KEY_LAST_ENTERED_GPA, student.getStudentGPA());
        sharedPrefEditor.putString(KEY_LAST_ENTERED_COMPLETED_HOURS, student.getStudentCompletedHours());

        sharedPrefEditor.commit();
    }

    //
    // Log shared pref values and save new Student
    // @param Student New Student to save
    //
    public void saveAndLogStudentInSharedPref(@NonNull Student student) {
        //Get old values saved in pref
        String name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");
        String major = sharedPreferences.getString(KEY_LAST_ENTERED_MAJOR, "NO VALUE ENTERED");
        String minor = sharedPreferences.getString(KEY_LAST_ENTERED_MINOR, "NO VALUE ENTERED");
        String year = sharedPreferences.getString(KEY_LAST_ENTERED_EXPECTED_GRAD_YEAR, "NO VALUE ENTERED");
        String GPA = sharedPreferences.getString(KEY_LAST_ENTERED_GPA, "NO VALUE ENTERED");
        String hours = sharedPreferences.getString(KEY_LAST_ENTERED_COMPLETED_HOURS, "NO VALUE ENTERED");

        //Log the old values
        Log.d(
                "TAG",
                "saveAndLogStudentInSharedPref: IN SHARED PREF: mane = " + name + " | major = " + major
                        + " | minor = " + minor + " | grad_year = " + year + " | GPA = " + GPA + " | completed_years = " + hours);

        //Save new values to shared pref
        saveMakeModelToSharedPref(student);

        //get new values
        name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");
        major = sharedPreferences.getString(KEY_LAST_ENTERED_MAJOR, "NO VALUE ENTERED");
        minor = sharedPreferences.getString(KEY_LAST_ENTERED_MINOR, "NO VALUE ENTERED");
        year = sharedPreferences.getString(KEY_LAST_ENTERED_EXPECTED_GRAD_YEAR, "NO VALUE ENTERED");
        GPA = sharedPreferences.getString(KEY_LAST_ENTERED_GPA, "NO VALUE ENTERED");
        hours = sharedPreferences.getString(KEY_LAST_ENTERED_COMPLETED_HOURS, "NO VALUE ENTERED");

        //log the new values
        Log.d(
                "TAG",
                "saveAndLogStudentInSharedPref: IN SHARED PREF: mane = " + name + " | major = " + major
                        + " | minor = " + minor + " | grad_year = " + year + " | GPA = " + GPA + " | completed_years = " + hours);
    }

    public void saveStudentToDBandSeeLog(@NonNull Student student){
        //Save student to database
        studentDatabaseHelper.insertStudentIntoDatabase(student);
        //get all current students in database and log them
        ArrayList<Student> studentList = studentDatabaseHelper.getAllStudentsFromDatabase();
        for(Student currentStudent : studentList) {
            Log.d("TAG", currentStudent.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // null check the intent sent back with result
        if(data != null) {
            //Get bundle from Intent
            Bundle resultBundle = data.getExtras();
            if(resultBundle != null){
                //Get student from bundle
                Student resultStudent = resultBundle.getParcelable(DataEntryActivity.KEY_STUDENT_RESULT);
                if(resultStudent != null) {
                    saveAndLogStudentInSharedPref(resultStudent);//save and log make model in shared pref
                    saveStudentToDBandSeeLog(resultStudent);//save Student to db and print list of all Students in db
                    populateTextViews(resultStudent); //populate the views
                }
            }
        }
    }

    //Write to INTERNAL storage
    public void writeToFile() {
        try {

            //Open up file to edit
            FileOutputStream fileOutputStream= openFileOutput(MY_FILE_TXT, MODE_PRIVATE);
            //Add to the file the text we want to save
            fileOutputStream.write("SOME TEXT I AM SAVING".getBytes());
            //close the file
            Log.d("TAG", "writeToFile: " +fileOutputStream);
            fileOutputStream.close();
            Log.d("TAG", "writeToFile: TEXT WRITTEN TO FILE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Read from INTERNAL storage
    public void readFromFile() {
        //Open file to read
        try {

            //Open file to read
            FileInputStream fileInputStream = openFileInput(MY_FILE_TXT);
            //Assign reader to file stream
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            char[] readBuffer = new char[READ_BLOCK_SIZE]; //blockes to read at a time
            String stringReadFromFile = ""; //hold all the text read from file
            int currentRead; //current value being read

            // while the current item being read is greater that a valueof 0, read the next value
            while((currentRead = inputStreamReader.read(readBuffer)) > 0) {
                //Convert the read value to a char(String)
                String readstring=String.copyValueOf(readBuffer,0,currentRead);
                //add char to read string
                stringReadFromFile+= readstring;
                Log.d("TAG", "readFromFile: " + stringReadFromFile);
            }
            fileInputStream.close();

            //Display results in logcat
            Log.d("TAG", "readFromFile: " + stringReadFromFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
