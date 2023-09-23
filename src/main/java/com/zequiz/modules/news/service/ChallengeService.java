package com.zequiz.modules.news.service;

import com.zequiz.modules.news.dto.ChallengeRequest;
import com.zequiz.modules.news.model.Category;
import com.zequiz.modules.news.model.Challenge;
import com.zequiz.modules.news.repository.CategoryRepository;
import com.zequiz.modules.news.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    private ChallengeRepository challengeRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository, CategoryRepository categoryRepository) {
        this.challengeRepository = challengeRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Challenge> getAll(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        return challengeRepository.findAll(paging);
    }

    public List<Challenge> findAllForQuiz() {
        return challengeRepository.findAllForQuiz();
    }
    public List<Challenge> findAllForQuizByCategory(Integer categoryId) {
        return challengeRepository.findAllForQuizByCategory(categoryId);
    }

    public Page<Challenge> getAllByCategoryId(Integer id, Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        return challengeRepository.findAllByCategoryId(id, paging);
    }

    public Challenge create(final ChallengeRequest challengeRequest) {
        final Category category = this.categoryRepository.findById(challengeRequest.getCategoryId()).orElse(null);
        final Challenge challenge = new Challenge(
                challengeRequest.getQuestion(),
                challengeRequest.getAnswer(),
                category,
                challengeRequest.getLevel()
        );
        return challengeRepository.save(challenge);
    }

    public Challenge update(Challenge currentChallenge, ChallengeRequest challengeRequest) {
        Category category = this.categoryRepository.findById(challengeRequest.getCategoryId()).orElse(null);
        currentChallenge.setQuestion(challengeRequest.getQuestion());
        currentChallenge.setAnswer(challengeRequest.getAnswer());
        currentChallenge.setCategory(category);
        currentChallenge.setLevel(challengeRequest.getLevel());
        return challengeRepository.save(currentChallenge);

    }

    public Challenge findChallengeById(Integer challengeId) {
        return challengeRepository.findById(challengeId).orElse(null);
    }

    public void delete(Challenge challenge) {
        challengeRepository.delete(challenge);
    }
}
