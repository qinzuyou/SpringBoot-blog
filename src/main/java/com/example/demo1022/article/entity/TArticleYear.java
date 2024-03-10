package com.example.demo1022.article.entity;


import lombok.Data;

import java.util.List;

@Data
public class TArticleYear {

    public int StatSYear;

    public List<TArticleMonth> MonthList;

    public TArticleYear() {
    }

    public TArticleYear(int statSYear, List<TArticleMonth> monthList) {
        StatSYear = statSYear;
        MonthList = monthList;
    }
}
