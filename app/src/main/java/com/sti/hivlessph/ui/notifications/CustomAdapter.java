package com.sti.hivlessph.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sti.hivlessph.R;

import java.util.ArrayList;

import model.Notification;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private final ArrayList<Notification> mDataSet = new ArrayList<>();

    public void setItems(ArrayList<Notification> items) {
        mDataSet.clear();
        mDataSet.addAll(items);

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitle;
        private final TextView textContent;
        private String url = "";

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!url.isEmpty()) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        v.getContext().startActivity(i);
                    }
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textTitle = v.findViewById(R.id.text_name);
            textContent = v.findViewById(R.id.text_content);
        }

        public TextView getTextTitle() {
            return textTitle;
        }

        public TextView getTextContent() {
            return textContent;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_layout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        Notification notification = mDataSet.get(position);
        viewHolder.getTextTitle().setText(notification.title);
        viewHolder.getTextContent().setText(notification.content);
        viewHolder.setUrl(notification.url);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}