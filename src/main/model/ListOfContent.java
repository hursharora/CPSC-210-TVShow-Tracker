package model;

import model.exceptions.InvalidContentTypeException;
import ui.ListOfContentScannerInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Represents a list of TV Shows
public class ListOfContent extends ListOfContentScannerInput implements Loadable, Saveable {

    public static final String TV_SHOW = "TVShow";
    public static final String MOVIE = "Movie";

    //EFFECTS: Creates empty TV show list
    public ListOfContent() {
        listOfContent = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: TV Show in is added to the list if not already in list
    public void insert(Content in) {
        System.out.println("Added \"" + in.getTitle() + "\" to list.");
        if (!contains(in)) {
            listOfContent.add(in);
        } else {
            System.out.println("TV show already in list");
        }
    }

    //EFFECTS: Returns true if TVShow is in the list, false otherwise
    public boolean contains(Content in) {
        return listOfContent.contains(in);
    }

    //EFFECTS: Returns number of items in TV show list
    public int size() {
        return listOfContent.size();
    }

    //MODIFIES: this
    //EFFECTS: merges this list of shows with given list of shows
    //no repetitions
    public void merge(List<Content> listOfContent) {
        for (Content content:listOfContent) {
            if (!contains(content)) {
                this.listOfContent.add(content);
            }
        }
        System.out.println("Added content to list!");

    }

    public Content getContent(int index) {
        return listOfContent.get(index);
    }

    @Override
    //REQUIRES: TVShow save file and given type must be "Movie" or "TVShow"
    //MODIFIES: this
    //EFFECTS: loads TVShows/movies in save file to List
    public void load(String type) throws IOException {
        Path path;
        if (type.equals(TV_SHOW)) {
            path = Paths.get("data/TVShowsSave");
            List<String> loadedList = Files.readAllLines(path);
            for (String s : loadedList) {
                listOfContent.add(new TVShow(s));
            }
        } else if (type.equals(MOVIE)) {
            path = Paths.get("data/MoviesSave");
            List<String> loadedList = Files.readAllLines(path);
            for (String s : loadedList) {
                listOfContent.add(new Movie(s));
            }
        }
    }

    @Override
    //REQUIRES: save file and given type must be "Movie" or "TVShow"
    //EFFECTS: saves TVShows/movies to save file from List
    public void save(String type) throws IOException, InvalidContentTypeException {
        Path path;
        if (type.equals(TV_SHOW)) {
            path = Paths.get("data/TVShowsSave");
        } else if (type.equals(MOVIE)) {
            path = Paths.get("data/MoviesSave");
        } else if (type.equals("Temp")) {
            path = Paths.get("data/TempSave");
        } else {
            throw new InvalidContentTypeException();
        }
        StringBuilder toWrite = new StringBuilder();
        for (Content content: listOfContent) {
            toWrite.append(content.getTitle());
            toWrite.append(System.lineSeparator());
        }
        Files.write(path, toWrite.toString().getBytes());
    }


}
