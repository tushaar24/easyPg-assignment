package com.example.assignmenteasypg.ui.fragments.comments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenteasypg.data.model.Comment;
import com.example.assignmenteasypg.databinding.ItemDataBinding;

import java.util.Collections;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private List<Comment> commentList = Collections.emptyList();

    public static class CommentsViewHolder extends RecyclerView.ViewHolder {

        private final ItemDataBinding binding;

        public CommentsViewHolder(ItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentsViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comment currentComment = commentList.get(position);
        holder.binding.tvComment.setText(currentComment.getBody());
        holder.binding.tvEmail.setText(currentComment.getEmail());
        holder.binding.tvName.setText(currentComment.getName());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public void setCommentList(List<Comment> newCommentList) {
        this.commentList = newCommentList;
        notifyDataSetChanged();
    }

}
