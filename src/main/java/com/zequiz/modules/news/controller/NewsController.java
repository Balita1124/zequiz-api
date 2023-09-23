package com.zequiz.modules.news.controller;

import com.zequiz.modules.news.dto.NewsListResponse;
import com.zequiz.modules.news.dto.NewsRequest;
import com.zequiz.modules.news.service.NewsService;
import com.zequiz.shared.utils.ErrorSection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "newss", name = "Find All news")
    public ResponseEntity<NewsListResponse> getAllNewss(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "title") String sort
    ) {
        return new ResponseEntity<>(new NewsListResponse(newsService.getAll(page, size, sort)),HttpStatus.OK);
    }

    @PostMapping(value = "newss", name = "Create news")
    public ResponseEntity<?> createNews(@RequestBody @Valid final NewsRequest newsRequest, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorSection es = new ErrorSection(newsRequest, bindingResult.getAllErrors());
            return new ResponseEntity<>(es, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                newsService.create(newsRequest),
                HttpStatus.CREATED
        );
    }

//    @PutMapping(value = "newss/{newsId}", name = "Update news")
//    public ApiResponse updateNews(@PathVariable(value = "newsId") Integer newsId, @RequestBody @Valid NewsRequest newsRequest, BindingResult bindingResult) {
//
//        News currentNews = newsService.findNewsById(newsId);
//        if (currentNews == null) {
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "News with id = " + newsId + " not found",
//                    new ErrorSection(newsRequest, null)
//            );
//        }
//        if (bindingResult.hasErrors()) {
//            ErrorSection es = new ErrorSection(newsRequest, bindingResult.getAllErrors());
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "News not updated",
//                    es
//            );
//        }
//        News news = newsService.update(currentNews, newsRequest);
//        return new ApiResponse(
//                true,
//                HttpStatus.OK,
//                "News Updated successfully",
//                news
//        );
//    }
//
//    @DeleteMapping(value = "newss/{newsId}", name = "Delete news")
//    public ApiResponse deleteNews(@PathVariable(value = "newsId") Integer newsId) {
//        News currentNews = newsService.findNewsById(newsId);
//        if (currentNews == null) {
//            return new ApiResponse(
//                    false,
//                    HttpStatus.OK,
//                    "News with id = " + newsId + " not found",
//                    null
//            );
//        }
//        newsService.delete(currentNews);
//        return new ApiResponse(
//                true,
//                HttpStatus.OK,
//                "News Deleted successfully",
//                null
//        );
//    }
}
