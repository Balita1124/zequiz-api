package com.zequiz.modules.news.controller;
import com.zequiz.modules.news.dto.ChallengeRequest;
import com.zequiz.modules.news.model.Challenge;
import com.zequiz.modules.news.service.ChallengeService;
import com.zequiz.shared.utils.ErrorSection;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping(value = "challenges", name = "Find All challenges")
    public ResponseEntity<Page<Challenge>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "question") String sort
    ) {
        return new ResponseEntity<>(challengeService.getAll(page, size, sort), HttpStatus.OK);
    }

    @GetMapping(value = "quizs", name = "Find All challenges for quiz")
    public ResponseEntity<List<Challenge>> getAllForQuiz() {
        return new ResponseEntity<>(challengeService.findAllForQuiz(), HttpStatus.OK);
    }

    @GetMapping(value = "quizs/{categoryId}", name = "Find All challenges for quiz")
    public ResponseEntity<List<Challenge>> getAllForQuiz(@PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity<>(challengeService.findAllForQuizByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping(value = "categories/{categoryId}/challenges", name = "Find challenges")
    public ResponseEntity<Page<Challenge>> getAllByCategoryId(
            @PathVariable("categoryId") Integer categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "question") String sort
            ) {
        return new ResponseEntity<>(challengeService.getAllByCategoryId(categoryId, page, size, sort), HttpStatus.OK);
    }

    @GetMapping(value = "challenges/{id}", name = "Find All challenges")
    public ResponseEntity<Challenge> getChallenge(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(challengeService.findChallengeById(id), HttpStatus.OK);
    }

    @PostMapping(value = "challenges", name = "Create challenge")
    public ResponseEntity<?> createChallenge(@RequestBody @Valid final ChallengeRequest challengeRequest, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final ErrorSection es = new ErrorSection(challengeRequest, bindingResult.getAllErrors());
            return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                challengeService.create(challengeRequest),
                HttpStatus.CREATED
        );
    }

//    @PutMapping(value = "challenges/{challengeId}", name = "Update challenge")
//    public ApiResponse updateChallenge(@PathVariable(value = "challengeId") Integer challengeId, @RequestBody @Valid ChallengeRequest challengeRequest, BindingResult bindingResult) {
//
//        Challenge currentChallenge = challengeService.findChallengeById(challengeId);
//        if (currentChallenge == null) {
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "Challenge with id = " + challengeId + " not found",
//                    new ErrorSection(challengeRequest, null)
//            );
//        }
//        if (bindingResult.hasErrors()) {
//            ErrorSection es = new ErrorSection(challengeRequest, bindingResult.getAllErrors());
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "Challenge not updated",
//                    es
//            );
//        }
//        Challenge challenge = challengeService.update(currentChallenge, challengeRequest);
//        return new ApiResponse(
//                true,
//                HttpStatus.OK,
//                "Challenge Updated successfully",
//                challenge
//        );
//    }
//
//    @DeleteMapping(value = "challenges/{challengeId}", name = "Delete challenge")
//    public ApiResponse deleteChallenge(@PathVariable(value = "challengeId") Integer challengeId) {
//        Challenge currentChallenge = challengeService.findChallengeById(challengeId);
//        if (currentChallenge == null) {
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "Challenge with id = " + challengeId + " not found",
//                    null
//            );
//        }
//        challengeService.delete(currentChallenge);
//        return new ApiResponse(
//                true,
//                HttpStatus.OK,
//                "Challenge Deleted successfully",
//                null
//        );
//    }
}
