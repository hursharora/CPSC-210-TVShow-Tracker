package model;

import java.util.ArrayList;
import java.util.List;

public class Genre {
    private String genre;
    private List<Content> listOfContent = new ArrayList<>();

    public Genre(String genre) {
        this.genre = genre;
    }

    public void addContent(Content c) {
        if (!listOfContent.contains(c)) {
            listOfContent.add(c);
            c.addGenre(this);
        }

    }

    public void removeContent(Content c) {
        if (listOfContent.contains(c)) {
            listOfContent.remove(c);
            c.removeGenre(this);
        }
    }

    public List<Content> getContent() {
        return listOfContent;
    }

    public String getGenre() {
        return genre;
    }

}
