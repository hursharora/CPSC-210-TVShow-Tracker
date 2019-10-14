package ui;

import model.Content;
import model.ListOfContent;
import model.Movie;
import model.TVShow;
import model.exceptions.InvalidContentTypeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public abstract class ListOfContentScannerInput {

    protected List<Content> listOfContent;

    //REQUIRES: given type must be "Movie" or "TVShow"
    //MODIFIES: this
    //EFFECTS: asks user if they want to input given content type, if
    //inputted will add content to this list
    public void inputContent(String type) {
        boolean entering = true;
        Scanner in = new Scanner(System.in);
        String input;
        while (entering) {
            System.out.println("Enter \"N\" to input a new " + type + " or \"Q\" to save and quit:");
            input = in.nextLine();
            if (input.equals("N")) {
                listOfContent.add(getMovieTVInput(type));
            } else if (input.equals("Q")) {
                entering = false;
                trySaving(type);
            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    private void trySaving(String type) {
        try {
            save(type);
        } catch (InvalidContentTypeException e) {
            System.out.println("Not Saved, Invalid content type!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found, created new file");
        } finally {
            System.out.println("Exceptions handled");
        }
    }


    protected abstract void save(String type) throws IOException, InvalidContentTypeException;

    //REQUIRES: given type must be "Movie" or "TVShow"
    //EFFECTS: Helper method for inputContent, lets user enter Show/Movie
    //name via scanner
    private Content getMovieTVInput(String type) {
        Scanner in = new Scanner(System.in);
        Content returnedContent;
        if (type.equals(ListOfContent.TV_SHOW)) {
            System.out.println("Enter TV show name:");
            returnedContent = new TVShow(in.nextLine());
        } else {
            System.out.println("Enter Movie name:");
            returnedContent = new Movie(in.nextLine());
        }
        return returnedContent;
    }


}
