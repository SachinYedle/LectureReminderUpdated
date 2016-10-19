package com.example.admin1.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin1.greendaodemo.db.DaoMaster;
import com.example.admin1.greendaodemo.db.DaoSession;
import com.example.admin1.greendaodemo.db.student;
import com.example.admin1.greendaodemo.db.studentDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText rollno,name;
    private Button submit,display,update,delete;
    private studentDao studentDaoSQL;
    private student studentObject;
    private TextView textView;

    private DaoSession daoSession;

    private static final String DB_NAME = "studentDB.sqlite";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDaoSQL = setUpDB();

        rollno = (EditText)findViewById(R.id.rollno);
        name = (EditText)findViewById(R.id.name);

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);
        display = (Button)findViewById(R.id.display);
        display.setOnClickListener(this);
        update = (Button)findViewById(R.id.update);
        update.setOnClickListener(this);
        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(this);
        textView = (TextView)findViewById(R.id.displa_data);

    }
    public studentDao setUpDB(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,DB_NAME,null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        daoSession = master.newSession();
        return daoSession.getStudentDao();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                studentObject = new student(null,rollno.getText().toString(),name.getText().toString());
                insertToSQL();
                Toast.makeText(MainActivity.this,"Data Inserted Successfully..",Toast.LENGTH_SHORT).show();
                rollno.setText("");
                name.setText("");
                break;
            case R.id.display:
                displayDataFromSQL();
                break;
            case R.id.delete:
                deleteDataFromSQL();
                break;
            case R.id.update:
                updateStudentData();
                break;
        }
    }

    public void insertToSQL(){
        studentDaoSQL.insert(studentObject);
    }
    public void displayDataFromSQL(){
        List<student> students = studentDaoSQL.loadAll();
        String studentData = "Student Data: ";
        if(students.size() > 0 ){
            for (int i = 0; i < students.size(); i++){
                studentData += "\nId: " + students.get(i).getId() +
                        "\nRoll No: " + students.get(i).getRollno() +
                        "\nName: " + students.get(i).getName();
            }
            textView.setText(studentData);
            return;
        }
        textView.setText("No Data Fount");
    }
    public void deleteDataFromSQL(){

        long id = 8;
        studentDaoSQL.deleteByKey(id);

        String deleteQuery = "DELETE FROM " + studentDaoSQL.TABLENAME + " WHERE " + studentDao.Properties.Rollno.columnName + "=?";
        String rollNo = rollno.getText().toString();
        if(!rollNo.isEmpty()) {
            studentDaoSQL.getDatabase().execSQL(deleteQuery, new Object[]{rollNo});
            Toast.makeText(MainActivity.this,"Record deleted "+rollNo,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,"Roll no is empty..",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateStudentData(){


        String updateQuery = "UPDATE " + studentDaoSQL.TABLENAME + " SET " + studentDao.Properties.Name.columnName +" =? "+
                "where " + studentDao.Properties.Rollno.columnName + " =?";

        String rollNo = rollno.getText().toString();
        String nameStudent = name.getText().toString();
        if(!rollNo.isEmpty()) {
            studentDaoSQL.getDatabase().execSQL(updateQuery,new Object[]{nameStudent,rollNo});
            Toast.makeText(MainActivity.this,"Record Updated "+rollNo,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this,"Roll no not inserted..",Toast.LENGTH_SHORT).show();
        }

    }

}
