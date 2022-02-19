package com.example.assignmenteasypg.data.remote;

import com.example.assignmenteasypg.data.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommentService {
    @GET("/comments")
    Call<List<Comment>> getComments();
}
