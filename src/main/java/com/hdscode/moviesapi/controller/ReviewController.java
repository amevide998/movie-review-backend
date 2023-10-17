package com.hdscode.moviesapi.controller;

import com.hdscode.moviesapi.model.Review;
import com.hdscode.moviesapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    private ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){
        if(payload == null || payload.get("reviewBody") == null || payload.get("imdbId") == null) {
            return new ResponseEntity<Review>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

}
