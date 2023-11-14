package com.remedios.eclassrecordteacher.exportdata;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.remedios.eclassrecordteacher.databinding.ActivityDataExportBinding;
import com.remedios.eclassrecordteacher.student.AppDatabase;
import com.remedios.eclassrecordteacher.student.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class XlsExportActivity extends AppCompatActivity implements ExportInterface{
    private ActivityDataExportBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDataExportBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        binding.button.setOnClickListener(view -> {
            ArrayList<User> student = new ArrayList<>();
            student.add(new User("Kurakmo Balesteros", "1020304050", "05-24-46", "08-23-19", "Male", "Bayot", "Mader", "Pader", "Bunawan ADS", "09103151883"));
            student.add(new User("Letecia Ramos", "123456789", "01-10-76", "08-23-19", "Female", "Ampalaya", "Mader", "Pader", "Rosario ADS", "09485448795"));
            student.add(new User("Ann Ramirez", "1002005009", "03-31-54", "08-24-19", "Female", "Pait", "Mader", "Pader", "Trento ADS", "0927458787"));
            student.add(new User("Balsamar Kami", "1234598765", "01-01-87", "08-25-19", "Male", "Lamination", "Roberta Anestisya", "Braulyo", "Bayugan ADS", "09103151883"));



            Gson gson = new Gson();
            var jsonArray = gson.toJsonTree(student).getAsJsonArray();
            String[] titles = new String[]{"Name", "LRN", "Birthdate", "Enrolment","Gender", "Remarks", "Mother Name", "Father Name", "Address", "Contact Number"};
            String[] indexName = new String[]{"studentName", "studentLRN", "birthDate", "enrolledDate", "studentGender", "studentRemarks", "motherName", "fatherName", "studentAddress", "contactNumber" };

            Toast.makeText(getApplicationContext(), "Exported Data", Toast.LENGTH_SHORT).show();

            HashMap<String, String> otherValue = new HashMap<>();
            otherValue.put("Record", "Student Record");
            otherValue.put("Place", "Campus City");
            otherValue.put("City", "Toranto");

              var file = generateXlsFile(this, titles, indexName, jsonArray, new HashMap<>(), "Student Record","students", 0);
               file.getAbsoluteFile() ;

        });


    }





}