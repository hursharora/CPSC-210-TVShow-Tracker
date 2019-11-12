package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeasonTest {

    @Test
    void testSeasonEquals() {
        Season testSeason = new Season(1);
        Season testSeason2 = new Season(1);
        assertTrue(testSeason.equals(testSeason));
        assertFalse(testSeason.equals(null));
        assertTrue(testSeason.equals(testSeason2));

    }

    @Test
    void toggleWatched() {
        Season testSeason = new Season(1);
        Season testSeason2 = new Season(1);
        testSeason.addOffShoot(testSeason2);
        testSeason.getOffShoots();
        testSeason.toggleWatched();
    }

}
