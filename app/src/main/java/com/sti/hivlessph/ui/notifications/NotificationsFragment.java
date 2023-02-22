package com.sti.hivlessph.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sti.hivlessph.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;

import model.Notification;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel notificationsViewModel;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance("https://hivlessph-b5f51-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference("notifications");

        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recycler = binding.recycler;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        CustomAdapter adapter = new CustomAdapter();

        notificationsViewModel.getItems().observe(getViewLifecycleOwner(), items -> adapter.setItems(items));

        recycler.setAdapter(adapter);

        getData();

        return root;
    }

    private void getData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> statistics = snapshot.getChildren();

                ArrayList<Notification> notifications = new ArrayList<>();

                for (DataSnapshot data : statistics) {
                    notifications.add(data.getValue(Notification.class));
                }

                notificationsViewModel.setItems(notifications);
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