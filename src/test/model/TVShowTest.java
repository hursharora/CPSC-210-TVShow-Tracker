package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TVShowTest {
    private TVShow show;
    private TVShow show2;
    private TVShow show3;
    private TVShow show4;
    private static final String TESTSHOW = "Test Title";
    private static final String TESTSHOW2 = "Test Title2";
    private static final String TESTSHOW3 = "Test Title3";

    @BeforeEach
    void runBefore() {
        show = new TVShow(TESTSHOW);
        show2 = new TVShow(TESTSHOW2, "2011", 9);
        show3  = new TVShow(TESTSHOW3, "2011");
        show4 = new TVShow("Rick and Morty");
    }

    @Test
    void testGetTitle() {
        assertEquals(show.getTitle(), TESTSHOW);
        assertEquals(show2.getTitle(), TESTSHOW2);
        assertEquals(show3.getTitle(), TESTSHOW3);
    }

    @Test
    void testSetTitle() {
        show.setTitle(TESTSHOW2);
        assertEquals(show.getTitle(), TESTSHOW2);
    }


    @Test
    void testRateUpperEdgeBound() {
        show.rate(10);
        assertEquals(10, show.getRating());
    }

    @Test
    void testRateLowerEdgeBound() {
        show.rate(0);
        assertEquals(0, show.getRating());
    }

    @Test
    void testRatingOutOfBound() {
        show.rate(11);
        assertEquals(-1, show.getRating());

    }

    @Test
    void testRatingOutOfLowerBound() {
        show.rate(-2);
        assertEquals(-1, show.getRating());
    }

    @Test
    void getAddAndRemoveGenre() {
        Genre testGenre = new Genre("Action");
        show.addGenre(testGenre);
        assertEquals("Test Title", testGenre.getContent().get(0).title);
        assertEquals("Action", show.getGenres().get(0).getGenre());
        testGenre.removeContent(show);
        assertEquals(0, show.getGenres().size());
        assertEquals(0, testGenre.getContent().size());
    }

    @Test
    void testToggleWatched() {
        show3.toggleWatched();
        assertTrue(show3.isWatched());
    }



}