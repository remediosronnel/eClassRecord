package com.remedios.eclassrecordteacher.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.remedios.eclassrecordteacher.R;
import com.remedios.eclassrecordteacher.fragment.ClassesFragment;


public class AddClass extends Fragment {

    EditText className, classTeacher, startDate, endDate;
    Button btnSave, btnCancel;
    private UserDatabase userDatabase;
    private UserDao userDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        userDatabase = UserDatabase.getInstance(getContext());
        userDao = userDatabase.getDao();

        className = className.findViewById(R.id.class_name1);
        classTeacher = classTeacher.findViewById(R.id.class_teacher1);
        startDate = startDate.findViewById(R.id.class_start_date);
        endDate = endDate.findViewById(R.id.class_end_date);
        btnSave = btnSave.findViewById(R.id.btn_save1);
        btnCancel = btnCancel.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String className1 = className.getText().toString();
                    String classTeacher1 = classTeacher.getText().toString();
                    String dateStart = startDate.getText().toString();
                    String dateEnd = endDate.getText().toString();

                    Users users = new Users(1,className1, classTeacher1, dateStart, dateEnd, 1);
                    userDao.insert(users);
                    className.setText("");
                    classTeacher.setText("");
                    startDate.setText("");
                    endDate.setText("");
                    Toast.makeText(getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }
            });


        btnCancel.setOnClickListener( new View.OnClickListener()
                {
                Fragment fragmentClass = new ClassesFragment();
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction transaction = requireFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragmentClass);
                        transaction.commit();
                        transaction.addToBackStack(null);

                    }
                }
        );

        return inflater.inflate(R.layout.fragment_add_class, container, false);
        }

    }



