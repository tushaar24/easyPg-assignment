package com.example.assignmenteasypg.ui.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.assignmenteasypg.databinding.FragmentSignUpBinding;
import com.example.assignmenteasypg.utils.TextUtils;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding = null;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(requireParentFragment()).navigate(com.example.assignmenteasypg.R.id.action_signUpFragment_to_loginFragment);
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                String emailId = binding.etEmailId.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();
                String confirmPassword = binding.etConfirmPassword.getText().toString().trim();

                if (TextUtils.checkCredentials(emailId, password, confirmPassword)) {
                    signUpUser(emailId, password);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    setErrorInTextField(emailId, password, confirmPassword);
                }
            }
        });
    }

    private void setErrorInTextField(String emailId, String password, String confirmPassword) {
        if (emailId.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
            binding.tilEmailId.setError("Required");
            binding.tilPassword.setError("Required");
            binding.tilConfirmPassword.setError("Required");
        } else {
            if (emailId.isEmpty()) {
                binding.tilEmailId.setError("Required");
            }
            if (password.isEmpty()) {
                binding.tilPassword.setError("Required");
            }
            if (confirmPassword.isEmpty()) {
                binding.tilConfirmPassword.setError("Required");
            }
        }
        if(!confirmPassword.equals(password)){
            binding.tilConfirmPassword.setError("Password doesn't match");
        }

        if(confirmPassword.length() < 6){
            binding.tilPassword.setError("Minimum password length should be 6");
        }
    }

    private void signUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        NavHostFragment.findNavController(requireParentFragment()).navigate(com.example.assignmenteasypg.R.id.action_signUpFragment_to_dataFragment);
                        binding.progressBar.setVisibility(View.GONE);
                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(requireContext(), "signUp failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}