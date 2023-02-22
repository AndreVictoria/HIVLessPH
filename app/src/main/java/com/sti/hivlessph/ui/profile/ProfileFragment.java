package com.sti.hivlessph.ui.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sti.hivlessph.MainActivity;
import com.sti.hivlessph.databinding.FragmentDashboardBinding;

import java.util.Calendar;

import model.Profile;

public class ProfileFragment extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private FragmentDashboardBinding binding;// creating a variable for
    private ProfileViewModel profileViewModel;

    private EditText editFirstName;
    private EditText editMiddleName;
    private EditText editLastName;
    private EditText editDateOfBirth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance("https://hivlessph-b5f51-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference("profiles/" + MainActivity.loginUid);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editFirstName = binding.editFirstName;
        editMiddleName = binding.editMiddleName;
        editLastName = binding.editLastName;
        editDateOfBirth = binding.editDateOfBirth;

        profileViewModel.getFirstName().observe(getViewLifecycleOwner(), editFirstName::setText);
        profileViewModel.getMiddleName().observe(getViewLifecycleOwner(), editMiddleName::setText);
        profileViewModel.getLastName().observe(getViewLifecycleOwner(), editLastName::setText);
        profileViewModel.getDateOfBirth().observe(getViewLifecycleOwner(), editDateOfBirth::setText);

        editDateOfBirth.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year1, monthOfYear, dayOfMonth) -> {
                profileViewModel.setDateOfBirth(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
            }, year, month, day);
            datePickerDialog.show();
        });

        getData();

        binding.buttonSave.setOnClickListener(view -> {
            Profile profile = new Profile();
            profile.firstName = String.valueOf(editFirstName.getText());
            profile.middleName = String.valueOf(editMiddleName.getText());
            profile.lastName = String.valueOf(editLastName.getText());
            profile.dateOfBirth = String.valueOf(editDateOfBirth.getText());

            databaseReference.setValue(profile);

            Toast.makeText(getContext(), "Profile saved.", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    private void getData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Profile profile = snapshot.getValue(Profile.class);

                if (profile == null) return;

                profileViewModel.setFirstName(profile.firstName);
                profileViewModel.setMiddleName(profile.middleName);
                profileViewModel.setLastName(profile.lastName);
                profileViewModel.setDateOfBirth(profile.dateOfBirth);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}