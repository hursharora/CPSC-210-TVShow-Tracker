package ui;

import model.ListOfContent;

import java.io.IOException;

public class Main {



    public static void main(String[] args) {

        ListOfContent listOfTVShows = new ListOfContent();
        try {
            listOfTVShows.load(ListOfContent.TV_SHOW);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Shows not loaded");
        }
        ListOfContent listOfMovies = new ListOfContent();
        try {
            listOfMovies.load(ListOfContent.MOVIE);
        } catch (IOException e) {
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
        listOfTVShows.inputContent(ListOfContent.MOVIE);



    }

}



