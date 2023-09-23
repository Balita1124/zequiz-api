package com.zequiz.modules.news.repository;

import com.zequiz.modules.news.model.News;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, Integer> {
//    Page<News> findAll(Pageable pageable);
}
