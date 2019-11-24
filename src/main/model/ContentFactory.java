package model;

public class ContentFactory {


    //EFFECTS: returns content of given type with given title, null if given type is not 'TVShow' or 'Movie'
    public static Content getContent(String type, String title) {
        if (type.equals("TVShow")) {
            return new TVShow(title);
        } else if (type.equals("Movie")) {
            return new Movie(title);
        }
        return null;
    }
}
