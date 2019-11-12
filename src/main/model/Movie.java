package model;

import java.util.Observable;

public class Movie extends Content {

    public Movie(String title) {
        this.title = title;
        System.out.println("Created new Movie called: " + title);
    }

    public Movie(String title, String releaseYear, int rating) {
        this.rating = rating;
        this.title = title;
        this.release = releaseYear;
        System.out.println("Created new Movie called: " + title);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
