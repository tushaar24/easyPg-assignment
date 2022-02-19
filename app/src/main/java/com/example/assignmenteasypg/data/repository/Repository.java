package com.example.assignmenteasypg.data.repository;

import static com.example.assignmenteasypg.utils.Constants.BASE_URL;

import com.example.assignmenteasypg.data.model.Comment;
import com.example.assignmenteasypg.data.remote.CommentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private static final CommentService commentApi = retrofit.create(CommentService.class);

    public Call<List<Comment>> getComments() {
        return commentApi.getComments();
    }
}