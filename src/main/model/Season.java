package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class Season extends Content {
    private List<Content> offshoots = new ArrayList<>();
    private int seasonNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Season season = (Season) o;
        return seasonNumber == season.seasonNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonNumber);
    }

    public Season(int seasonNumber) {
        this.seasonNumber = seasonNumber;
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

    @Override
    public void toggleWatched() {
        for (Content c: offshoots) {
            c.toggleWatched();
        }
    }

    public void addOffShoot(Content c) {
        offshoots.add(c);
    }

    public List<Content> getOffShoots() {
        return offshoots;
    }
}
