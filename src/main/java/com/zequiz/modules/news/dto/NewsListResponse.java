package com.zequiz.modules.news.dto;

import com.zequiz.modules.news.model.News;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class NewsListResponse {

    private final Page<News> newsPage;

    public NewsListResponse(Page<News> newsPage) {
        this.newsPage = newsPage;
    }

    //    private final List<News> newsList;
//    private final int pageSize;
//    private final int pageNumber;
//    private final long offset;
//
//    public NewsListResponse(List<News> newsList, int pageSize, int pageNumber, long offset) {
//        this.newsList = newsList;
//        this.pageSize = pageSize;
//        this.pageNumber = pageNumber;
//        this.offset = offset;
//    }
}
