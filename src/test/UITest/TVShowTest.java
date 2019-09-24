package UITest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.TVShow;

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
    }

    @Test
    public void testGetTitle() {
        assertEquals(show.getTitle(), TESTSHOW);
    }

    @Test
    public void testSetTitle() {
        show.setTitle(TESTSHOW2);
        assertEquals(show.getTitle(), TESTSHOW2);
    }

}