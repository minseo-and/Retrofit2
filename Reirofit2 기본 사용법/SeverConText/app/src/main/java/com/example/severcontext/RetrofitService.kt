package com.example.severcontext

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {
    // @GET( EndPoint-μμμμΉ(URI) )
    @GET("posts/{post}")
    fun getPosts(@Path("post") post: String?): Call<PostResult?>?

}