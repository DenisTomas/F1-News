package com.denistomas.f1news.data.remote;

import com.denistomas.f1news.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface F1NewsApi {

    @GET("news.json")
    Call<List<News>> getNews();
}
