package model;

import model.exceptions.InvalidContentTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfContentTest {

    private ListOfContent listOfTVShows;
    private ListOfContent listOfMovies;
    private ListOfContent testList;

    private TVShow show;
    private TVShow show2;
    private TVShow show3;
    private Movie movie;
    private Movie movie2;
    private static final String TEST_TITLE = "Test Title";
    private static final String TEST_TITLE_2 = "Test Title2";
    private static final String TEST_TITLE_3 = "Test Title3";

    @BeforeEach
    public void runBefore() {
        listOfMovies = new ListOfContent();
        listOfTVShows = new ListOfContent();
        testList = new ListOfContent();
        show = new TVShow(TEST_TITLE);
        show2 = new TVShow(TEST_TITLE_2);
        show3 = new TVShow(TEST_TITLE_3);

        movie = new Movie(TEST_TITLE);
        movie2 = new Movie(TEST_TITLE_2);

    }

    @Test
    public void testInsertOne() {
        listOfTVShows.insert(show);

        assertTrue(listOfTVShows.contains(show));
    }

    @Test
    public void testInsertDuplicate() {
        listOfTVShows.insert(show);
        listOfTVShows.insert(show);

        assertTrue(listOfTVShows.contains(show));
        assertEquals(listOfTVShows.size(), 1);
    }

    @Test
    public void testInsertSome() {
        listOfTVShows.insert(show);
        listOfTVShows.insert(show2);
        listOfTVShows.insert(show3);

        assertTrue(listOfTVShows.contains(show));
        assertTrue(listOfTVShows.contains(show2));
        assertTrue(listOfTVShows.contains(show3));
        assertEquals(listOfTVShows.size(), 3);

    }


    @Test
    public void testSaveLoadTV() throws IOException, InvalidContentTypeException {
        listOfTVShows.insert(show);
        listOfTVShows.insert(show2);

        listOfTVShows.save(ListOfContent.TV_SHOW);
        testList.load(ListOfContent.TV_SHOW);
        assertEquals(listOfTVShows.size(), testList.size());
    }


    @Test
    public void testSaveExpectNoInvalidContentException() {
        try {
            listOfMovies.save(ListOfContent.MOVIE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidContentTypeException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveExpectInvalidContentException() {
        try {
            listOfMovies.save("Test");
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (InvalidContentTypeException e) {
            e.printStackTrace();
        }
    }

}
