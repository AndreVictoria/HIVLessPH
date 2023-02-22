// Generated by view binder compiler. Do not edit!
package com.sti.hivlessph.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.sti.hivlessph.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonSend;

  @NonNull
  public final ConstraintLayout container;

  @NonNull
  public final EditText editMessage;

  @NonNull
  public final RecyclerView messages;

  private ActivityChatBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonSend,
      @NonNull ConstraintLayout container, @NonNull EditText editMessage,
      @NonNull RecyclerView messages) {
    this.rootView = rootView;
    this.buttonSend = buttonSend;
    this.container = container;
    this.editMessage = editMessage;
    this.messages = messages;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_send;
      Button buttonSend = ViewBindings.findChildViewById(rootView, id);
      if (buttonSend == null) {
        break missingId;
      }

      ConstraintLayout container = (ConstraintLayout) rootView;

      id = R.id.edit_message;
      EditText editMessage = ViewBindings.findChildViewById(rootView, id);
      if (editMessage == null) {
        break missingId;
      }

      id = R.id.messages;
      RecyclerView messages = ViewBindings.findChildViewById(rootView, id);
      if (messages == null) {
        break missingId;
      }

      return new ActivityChatBinding((ConstraintLayout) rootView, buttonSend, container,
          editMessage, messages);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}