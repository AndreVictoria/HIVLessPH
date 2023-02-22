package com.sti.hivlessph.ui.chat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sti.hivlessph.R;

import java.util.ArrayList;

import model.Chat;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private final ArrayList<Chat> mDataSet = new ArrayList<>();

    public void setItems(ArrayList<Chat> items) {
        mDataSet.clear();
        mDataSet.addAll(items);

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textSender;
        private final TextView textMessage;
        private final TextView textTimestamp;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textSender = v.findViewById(R.id.text_sender);
            textMessage = v.findViewById(R.id.text_message);
            textTimestamp = v.findViewById(R.id.text_timestamp);
        }

        public TextView getTextSender() {
            return textSender;
        }

        public TextView getTextMessage() {
            return textMessage;
        }

        public TextView getTextTimestamp() {
            return textTimestamp;
        }
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.messages, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        Chat chat = mDataSet.get(position);
        viewHolder.getTextSender().setText(chat.sender);
        viewHolder.getTextMessage().setText(chat.message);
        viewHolder.getTextTimestamp().setText(chat.timestamp);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}