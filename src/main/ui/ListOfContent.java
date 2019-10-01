package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Represents a list of TV Shows
public class ListOfContent implements Loadable, Saveable {

    public static final String TV_SHOW = "TVShow";
    public static final String MOVIE = "Movie";
    private List<Content> listOfContent;

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
            if (!this.listOfContent.contains(content)) {
                this.listOfContent.add(content);
            }
        }
        System.out.println("Added content to list!");

    }

    //REQUIRES: given type must be "Movie" or "TVShow"
    //MODIFIES: this
    //EFFECTS: asks user if they want to input a TVShow, if
    //inputted will return TVShow, null otherwise
    public void inputContent(String type) throws IOException {
        boolean entering = true;
        Scanner in = new Scanner(System.in);
//        List<Content> returnedList = new ArrayList<>();
        String input;
        while (entering) {
            System.out.println("Enter \"N\" to input a new " + type + " or \"Q\" to save and quit:");
            input = in.nextLine();
            if (input.equals("N")) {
                listOfContent.add(getMovieTVInput(type));
            } else if (input.equals("Q")) {
                entering = false;
                save(type);
            } else {
                System.out.println("Invalid Input");
            }
        }

    }

    //REQUIRES: given type must be "Movie" or "TVShow"
    //EFFECTS: Helper method for inputContent, lets user enter Show/Movie
    //name via scanner
    private Content getMovieTVInput(String type) {
        Scanner in = new Scanner(System.in);
        Content returnedContent;
        if (type.equals(TV_SHOW)) {
            System.out.println("Enter TV show name:");
            returnedContent = new TVShow(in.nextLine());
        } else {
            System.out.println("Enter Movie name:");
            returnedContent = new Movie(in.nextLine());
        }
        return returnedContent;
    }

    @Override
    //REQUIRES: TVshow save file and given type must be "Movie" or "TVShow"
    //MODIFIES: this
    //EFFECTS: loads Tvshows/movies in save file to List
    public void load(String type) throws IOException {
        Path path;
        if (type.equals(TV_SHOW)) {
            path = Paths.get("data/TVShowsSave");
            List<String> loadedList = Files.readAllLines(path);
            for (String s : loadedList) {
                listOfContent.add(new TVShow(s));
            }
        } else {
            path = Paths.get("data/MoviesSave");
            List<String> loadedList = Files.readAllLines(path);
            for (String s : loadedList) {
                listOfContent.add(new Movie(s));
            }
        }
    }

    @Override
    //REQUIRES: TVshow save file and given type must be "Movie" or "TVShow"
    //EFFECTS: saves Tvshows/movies to save file from List
    public void save(String type) throws IOException {
        Path path;
        if (type.equals(TV_SHOW)) {
            path = Paths.get("data/TVShowsSave");
        } else {
            path = Paths.get("data/MoviesSave");
        }
        StringBuilder toWrite = new StringBuilder();
        for (Content content: listOfContent) {
            toWrite.append(content.getTitle());
            toWrite.append(System.lineSeparator());
        }
        Files.write(path, toWrite.toString().getBytes());
    }


}
