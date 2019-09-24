package UITest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ListOfTVShow;
import ui.TVShow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListOfTVShowTest {

    private ListOfTVShow listOfTVShow;

    private TVShow show;
    private TVShow show2;
    private TVShow show3;
    private static final String TESTSHOW = "Test Title";
    private static final String TESTSHOW2 = "Test Title2";
    private static final String TESTSHOW3 = "Test Title3";

    @BeforeEach
    public void runBefore() {
        listOfTVShow = new ListOfTVShow();
        show = new TVShow(TESTSHOW);
        show2 = new TVShow(TESTSHOW2);
        show3 = new TVShow(TESTSHOW3);
    }

    @Test
    public void testInsertOne() {
        listOfTVShow.insert(show);

        assertTrue(listOfTVShow.contains(show));
    }

    @Test
    public void testInsertDuplicate() {
        listOfTVShow.insert(show);
        listOfTVShow.insert(show);

        assertTrue(listOfTVShow.contains(show));
        assertEquals(listOfTVShow.size(), 1);
    }

    @Test
    public void testInsertSome() {
        listOfTVShow.insert(show);
        listOfTVShow.insert(show2);
        listOfTVShow.insert(show3);

        assertTrue(listOfTVShow.contains(show));
        assertTrue(listOfTVShow.contains(show2));
        assertTrue(listOfTVShow.contains(show3));
        assertEquals(listOfTVShow.size(), 3);

    }



}
