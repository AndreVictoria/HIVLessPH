package com.sti.hivlessph.ui.chat;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sti.hivlessph.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import model.Chat;

public class ChatActivity extends AppCompatActivity {

    public static String loginUid = "";
    private ActivityChatBinding binding;

    private ChatViewModel chatViewModel;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private EditText editMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long currentTimeMillis = System.currentTimeMillis();

        firebaseDatabase = FirebaseDatabase.getInstance("https://hivlessph-b5f51-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference("sessions/" + currentTimeMillis);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        sendMessage("System", "Start new chat session. Feel free to send your inquiry while waiting for a counselor to join the session.");

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btn = binding.buttonSend;
        editMessage = binding.editMessage;

        btn.setOnClickListener(view -> {

            String message = String.valueOf(editMessage.getText()).trim();

            if(!message.isEmpty()) {

                sendMessage("You", message);

                editMessage.setText("");

                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
            }
        });

        final RecyclerView recycler = binding.messages;
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ChatAdapter adapter = new ChatAdapter();

        chatViewModel.getItems().observe(this, items -> adapter.setItems(items));

        recycler.setAdapter(adapter);

        getData();
    }

    private void sendMessage(String sender, String message ) {
        long unixTime = System.currentTimeMillis();
        DatabaseReference ref = databaseReference.child("messages").child(String.valueOf(unixTime));
        ref.child("sender").setValue(sender);
        ref.child("message").setValue(message);
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("MM/dd/yyyy h:mma", cal).toString();
        return date;
    }

    private void getData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> statistics = snapshot.child("messages").getChildren();

                ArrayList<Chat> messages = new ArrayList<>();

                for (DataSnapshot data : statistics) {
                    Chat chat = data.getValue(Chat.class);
                    chat.timestamp = getDate(Long.parseLong(data.getKey()));
                    messages.add(chat);

                }

                chatViewModel.setItems(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}