package com.hdscode.moviesapi.service;

import com.hdscode.moviesapi.model.Movie;
import com.hdscode.moviesapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(String imdbId) {
        Movie movie =  movieRepository.findFirstByImdbId(imdbId).orElse(null);
        if(movie != null) {
            return movie;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
    }


}
