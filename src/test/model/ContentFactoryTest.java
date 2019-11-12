package model;

import org.junit.jupiter.api.Test;

public class ContentFactoryTest {

    @Test
    void testConstructor() {
        ContentFactory contentFactory = new ContentFactory();
    }

    @Test
    void testMakeShowAndMovie() {
        Content testShow = ContentFactory.getContent("TVShow", "TestShow");
        Content testMovie = ContentFactory.getContent("Movie", "TestMovie");
    }

}
