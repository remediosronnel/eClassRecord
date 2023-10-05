package com.remedios.eclassrecordteacher.classes;

import static com.remedios.eclassrecordteacher.R.*;
import static com.remedios.eclassrecordteacher.R.id.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.remedios.eclassrecordteacher.fragment.ClassesFragment;

import java.util.List;


public class AddClass extends Fragment {

    EditText className, classTeacher, startDate, endDate;
    Button btnSave, btnCancel;


    UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(layout.fragment_add_class, container, false);





        return root;
        }


    }



