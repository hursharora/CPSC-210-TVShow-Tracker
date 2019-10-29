package model;

import java.util.Objects;

public class Season extends Content {

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
}
