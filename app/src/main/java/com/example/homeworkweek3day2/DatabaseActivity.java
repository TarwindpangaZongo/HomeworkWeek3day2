package com.example.homeworkweek3day2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DatabaseActivity extends AppCompatActivity {
    //Constants
    public static final String KEY_SHARED_PREF = "shared_pref";
    public static final String KEY_LAST_ENTERED_NAME = "last_name";
    public static final String KEY_LAST_ENTERED_MAJOR = "last_major";
    public static final String KEY_LAST_ENTERED_MINOR = "last_minor";
    public static final String KEY_LAST_ENTERED_EXPECTED_GRAD_YEAR = "last_Expected_Grad_Year";
    public static final String KEY_LAST_ENTERED_GPA = "last_GPA";
    public static final String KEY_LAST_ENTERED_COMPLETED_HOURS = "last_Completed_Hours";
    public static final String MY_FILE_TXT = "myFile.txt";

    //Declare views
    EditText etName;
    EditText etMajor;
    EditText etMinor;
    EditText etExpectedGradYeare;
    EditText etGPA;
    EditText etCompletedHours;
    EditText etIdNumber;

    //Student Database Declaration
    StudentDatabaseHelper studentDatabaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        studentDatabaseHelper = new StudentDatabaseHelper(this);
        bindViews();

    }

    //

    //  Bind Views to xml elements
    //  @return void
    public void bindViews() {
        etIdNumber = findViewById(R.id.etIdNumber);
        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etMinor = findViewById(R.id.etMinor);
        etExpectedGradYeare = findViewById(R.id.etExpectedGradYeare);
        etGPA = findViewById(R.id.etGPA);
        etCompletedHours = findViewById(R.id.etCompletedHours);
    }


    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnFindById:
                if (etIdNumber.getText() != null){
                    //get our database helper
                    StudentDatabaseHelper studentDatabHelper = new StudentDatabaseHelper(this);
                    //Get id
                    String studentIdToFind = etIdNumber.getText().toString();
                    //Delete item with id form Database
                    studentDatabHelper.getStudentById(Integer.valueOf(studentIdToFind));

                }
                break;
            case R.id.btnUpdateEntry:
                if (etIdNumber.getText() != null && etName.getText()!= null&& etMajor.getText()!= null&& etMinor.getText()!= null
                        && etExpectedGradYeare.getText()!= null&& etGPA.getText()!= null&& etCompletedHours.getText()!= null){
                    //get our database helper
                    StudentDatabaseHelper studentDatabaseHelper = new StudentDatabaseHelper(this);
                    //retrive the student which has the same id as the on which we want to edit
                    int studentId = Integer.parseInt(etIdNumber.getText().toString());
                    Student studentToEdit = studentDatabaseHelper.getStudentById(studentId);
                    //set the new values for the student we want to edit
                    studentToEdit.setStudentName(etName.getText().toString());
                    studentToEdit.setStudentMajor(etMajor.getText().toString());
                    studentToEdit.setStudentMinor(etMinor.getText().toString());
                    studentToEdit.setStudentExpectedGradYear(etExpectedGradYeare.getText().toString());
                    studentToEdit.setStudentGPA(etGPA.getText().toString());
                    studentToEdit.setStudentCompletedHours(etCompletedHours.getText().toString());
                    //update the database with the new value(s)
                    studentDatabaseHelper.updateStudenteInDatabase(studentToEdit);
                }

                break;
            case R.id.btnDeleteEntry:
                if (etIdNumber.getText() != null && etName.getText()!= null&& etMajor.getText()!= null&& etMinor.getText()!= null
                        && etExpectedGradYeare.getText()!= null&& etGPA.getText()!= null&& etCompletedHours.getText()!= null){
                    //get our database helper
                    StudentDatabaseHelper studentDBHelper = new StudentDatabaseHelper(this);
                    //Get id
                    String studentIdToDelete = etIdNumber.getText().toString();
                    //Delete item with id form Database
                    studentDBHelper.deleteFromDatabaseById(new String[]{studentIdToDelete});
                }

                break;

        }
    }

}
