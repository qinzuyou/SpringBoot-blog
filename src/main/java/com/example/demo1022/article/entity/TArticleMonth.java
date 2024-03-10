package com.example.demo1022.article.entity;

import lombok.Data;

import java.util.List;

@Data
public class TArticleMonth {

    private int StatSMonth;

    private List<TArticleDto> ArticleList;

    public TArticleMonth() {
    }

    public TArticleMonth(int statSMonth, List<TArticleDto> articleList) {
        StatSMonth = statSMonth;
        ArticleList = articleList;
    }
}
