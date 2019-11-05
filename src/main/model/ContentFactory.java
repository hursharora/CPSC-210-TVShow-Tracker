package model;

public class ContentFactory {

    public static Content getContent(String type, String title) {
        if (type.equals("TVShow")) {
            return new TVShow(title);
        } else if (type.equals("Movie")) {
            return new Movie(title);
        }
        return null;
    }
}
