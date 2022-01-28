package com.example.mediplus.vaccineslottracker.services;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private EditText dateView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        getDateView().setText(day+"-"+(month+1)+"-"+year);
    }

    public EditText getDateView() {
        return dateView;
    }

    public void setDateView(EditText dateView) {
        this.dateView = dateView;
    }
}