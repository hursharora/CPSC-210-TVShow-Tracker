package ui;

import model.ListOfContent;
import model.exceptions.InvalidContentTypeException;
import model.exceptions.ShowNotFoundException;
import network.ShowInfoGetter;
import network.TVDbAuthorization;
import network.TVShowRequester;

import java.io.IOException;

public class Main {



    public static void main(String[] args) throws IOException, ShowNotFoundException {
        //System.out.println(ShowInfoGetter.getShowTVDbID("Rick and Morty"));

        ListOfContent listOfTVShows = new ListOfContent();
        try {
            listOfTVShows.load(ListOfContent.TV_SHOW);
        } catch (IOException | InvalidContentTypeException e) {
            e.printStackTrace();
            System.out.println("Shows not loaded");
        }
        ListOfContent listOfMovies = new ListOfContent();
        try {
            listOfMovies.load(ListOfContent.MOVIE);
        } catch (IOException | InvalidContentTypeException e) {
            System.out.println("Movies not loaded");
            e.printStackTrace();
        }

//        TVShow firstTVShow = new TVShow(3, 30, "Stranger Things", "9/21/2016", 5);
//        TVShow firstTVShow = new TVShow("Stranger Things");
//        Movie firstMovie = new Movie("Interstellar", "2014");
//
//        listOfTVShows.insert(firstTVShow);
//        listOfMovies.insert(firstMovie);

        listOfTVShows.inputContent(ListOfContent.TV_SHOW);
        listOfMovies.inputContent(ListOfContent.MOVIE);



    }

}



