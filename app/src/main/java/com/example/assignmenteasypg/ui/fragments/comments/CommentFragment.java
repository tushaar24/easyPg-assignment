package com.example.assignmenteasypg.ui.fragments.comments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmenteasypg.databinding.FragmentDataBinding;

public class CommentFragment extends Fragment {

    private FragmentDataBinding binding = null;
    private CommentViewModel mViewModel;
    private CommentsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataBinding.inflate(inflater, container, false);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finishAffinity();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel =  new ViewModelProvider(this).get(CommentViewModel.class);

        setRecyclerView();

        mViewModel.getComments();

        mViewModel.comments.observe(requireActivity(), comments -> {
            mAdapter.setCommentList(comments);
            binding.progressBar.setVisibility(View.INVISIBLE);
        });
    }

    private void setRecyclerView() {
        mAdapter = new CommentsAdapter();
        binding.rvData.setAdapter(mAdapter);
        binding.rvData.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }
}