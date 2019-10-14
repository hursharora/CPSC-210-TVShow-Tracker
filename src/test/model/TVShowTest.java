package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TVShowTest {
    private TVShow show;
    private TVShow show2;
    private TVShow show3;
    private static final String TESTSHOW = "Test Title";
    private static final String TESTSHOW2 = "Test Title2";
    private static final String TESTSHOW3 = "Test Title3";

    @BeforeEach
    public void runBefore() {
        show = new TVShow(TESTSHOW);
        show2 = new TVShow(new ListOfContent(), TESTSHOW2, "2011", 9);
        show3  = new TVShow(new ListOfContent(), TESTSHOW3, "2011");
    }

    @Test
    public void testGetTitle() {
        assertEquals(show.getTitle(), TESTSHOW);
        assertEquals(show2.getTitle(), TESTSHOW2);
        assertEquals(show3.getTitle(), TESTSHOW3);
    }

    @Test
    public void testSetTitle() {
        show.setTitle(TESTSHOW2);
        assertEquals(show.getTitle(), TESTSHOW2);
    }

}