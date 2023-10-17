package com.hdscode.moviesapi.service;

import com.hdscode.moviesapi.model.Movie;
import com.hdscode.moviesapi.model.Review;
import com.hdscode.moviesapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Review createReview(String reviewBody, String imdbId) {
        Review review = new Review();
        review.setBody(reviewBody);

        reviewRepository.insert(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewsIds").value(review))
                .first();

        return review;

    }

}
