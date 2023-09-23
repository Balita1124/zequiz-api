package com.zequiz.modules.news.service;

import com.zequiz.modules.news.dto.NewsRequest;
import com.zequiz.modules.news.model.News;
import com.zequiz.modules.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Page<News> getAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        return newsRepository.findAll(paging);
    }

    public News create(NewsRequest newsRequest) {
        News news = new News(newsRequest.getTitle(), newsRequest.getDescription());
        return newsRepository.save(news);
    }

    public News update(News currentNews, NewsRequest newsRequest) {
        currentNews.setTitle(newsRequest.getTitle());
        currentNews.setDescription(newsRequest.getDescription());
        return newsRepository.save(currentNews);

    }

    public News findNewsById(Integer newsId) {
        return newsRepository.findById(newsId).orElse(null);
    }

    public void delete(News news) {
        newsRepository.delete(news);
    }
}
