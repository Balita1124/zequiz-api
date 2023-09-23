package com.zequiz.modules.news.repository;

import com.zequiz.modules.news.model.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends PagingAndSortingRepository<Challenge, Integer> {
    Page<Challenge> findAllByCategoryId(Integer id, Pageable page);
    @Query(nativeQuery=true, value="SELECT *  FROM challenges ORDER BY random() LIMIT 10")
    List<Challenge> findAllForQuiz();

    @Query(nativeQuery=true, value="SELECT *  FROM challenges where category_id =:categoryId ORDER BY random() LIMIT 10")
    List<Challenge> findAllForQuizByCategory(Integer categoryId);
}
