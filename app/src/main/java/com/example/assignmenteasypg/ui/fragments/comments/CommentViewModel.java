package com.example.assignmenteasypg.ui.fragments.comments;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignmenteasypg.data.model.Comment;
import com.example.assignmenteasypg.data.repository.Repository;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends ViewModel {

    private final Repository repository = new Repository();

    MutableLiveData<List<Comment>> comments = new MutableLiveData<>();

    public void getComments(){
        repository.getComments().enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {
                if(response.body() != null){
                    List<Comment> commentsList = response.body();
                    comments.postValue(commentsList);
                }else{
                    comments.postValue(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
