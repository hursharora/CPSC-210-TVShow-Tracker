package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    void testMovieConstructor() {
        Movie testMovie = new Movie("UP", "2012", 10);
        assertEquals("UP", testMovie.getTitle());
    }

}
