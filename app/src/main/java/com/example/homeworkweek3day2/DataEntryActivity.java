package com.example.homeworkweek3day2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DataEntryActivity extends AppCompatActivity {
    //Constants
    public static final String KEY_STUDENT_RESULT = "student_result";
    public static final int RESULT_CODE = 656;

    //Declare views
    EditText etNameDisplay;
    EditText etMajorDisplay;
    EditText etMinorDisplay;
    EditText etExpectedGradYeareDisplay;
    EditText etGPADisplay;
    EditText etCompletedHoursisplay;

    //The Intent that started this activity
    Intent sentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        sentIntent = getIntent(); //gets intent that started the activity
        bindViews();
    }

    //
    //  Bind Views to xml elements
    //  @return void
    public void bindViews() {
        etNameDisplay = findViewById(R.id.etName);
        etMajorDisplay = findViewById(R.id.etMajor);
        etMinorDisplay = findViewById(R.id.etMinor);
        etExpectedGradYeareDisplay = findViewById(R.id.etExpectedGradYeare);
        etGPADisplay = findViewById(R.id.etGPA);
        etCompletedHoursisplay = findViewById(R.id.etCompletedHours);
    }

    //
    // Create Student Object
    // @return Student The new Student object
    //
    public Student generateStudentObjectFromInput() {
        Student returnStudent = new Student();  //the Student we will return from method

        //Set-up Student object
        returnStudent.setStudentName(
                etNameDisplay.getText() != null ? etNameDisplay.getText().toString() : "");
        returnStudent.setStudentMajor(
                etMajorDisplay.getText() != null ? etMajorDisplay.getText().toString() : "");
        returnStudent.setStudentMinor(
                etMinorDisplay.getText() != null ? etMinorDisplay.getText().toString() : "");
        returnStudent.setStudentExpectedGradYear(
                etExpectedGradYeareDisplay.getText() != null ? etExpectedGradYeareDisplay.getText().toString() : "");
        returnStudent.setStudentGPA(
                etGPADisplay.getText() != null ? etGPADisplay.getText().toString() : "");
        returnStudent.setStudentCompletedHours(
                etCompletedHoursisplay.getText() != null ? etCompletedHoursisplay.getText().toString() : "");

        return returnStudent;
    }

    public void onClick(View view) {
        Student studentResult = generateStudentObjectFromInput();
        Bundle bundleOfTheStudentResult = new Bundle();
        bundleOfTheStudentResult.putParcelable(KEY_STUDENT_RESULT, studentResult); //put Student object in bundle
        sentIntent.putExtras(bundleOfTheStudentResult); //attach the result bundle to intent
        setResult(RESULT_CODE, sentIntent); //send back bundle with result to activity which called it for result
        finish(); //Make sure the activity is flagged to be destroyed
    }
}
