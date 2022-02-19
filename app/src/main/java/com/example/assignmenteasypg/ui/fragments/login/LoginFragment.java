package com.example.assignmenteasypg.ui.fragments.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.assignmenteasypg.R;
import com.example.assignmenteasypg.databinding.FragmentLoginBinding;
import com.example.assignmenteasypg.utils.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
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

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        binding.btnLogin.setOnClickListener(view1 -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            String emailId = binding.etEmailId.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            if (TextUtils.checkCredentials(emailId, password)) {
                signInUser(emailId, password);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                setErrorInTextField(emailId, password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            NavHostFragment.findNavController(requireParentFragment()).navigate(R.id.action_loginFragment_to_dataFragment);
        }
    }

    private void setErrorInTextField(String emailId, String password) {
        if (emailId.isEmpty() && password.isEmpty()) {
            binding.tilEmailId.setError("Required");
            binding.tilPassword.setError("Required");
        } else {
            if (emailId.isEmpty()) {
                binding.tilEmailId.setError("Required");
            } else {
                binding.tilPassword.setError("Required");
            }
        }
    }

    private void signInUser(String emailId, String password) {
        Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(emailId, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        binding.progressBar.setVisibility(View.GONE);
                        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_dataFragment);
                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(requireActivity(), "Error Login", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}