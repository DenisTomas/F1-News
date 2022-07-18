package com.denistomas.f1news.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denistomas.f1news.data.remote.F1NewsApi;
import com.denistomas.f1news.domain.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final F1NewsApi api;

    public NewsViewModel() {

        //mock list
//        this.news = new MutableLiveData<>();
//
//        List<News> news = new ArrayList<>();
//        news.add(new News("testee 1111", "teste 11", "hhh", "555"));
//
//        this.news.setValue(news);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://denistomas.github.io/F1-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(F1NewsApi.class);
        findNews();
    }

    private void findNews() {
        System.out.println("entrou no FindNews");
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                } else {
                    //TODO implementar else
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO implementar onFailure
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}
