package ui;

import java.io.IOException;
import java.util.List;

public class Main {



    public static void main(String[] args) throws IOException {

        ListOfContent listOfTVShows = new ListOfContent();
        listOfTVShows.load(ListOfContent.TV_SHOW);
        ListOfContent listOfMovies = new ListOfContent();
        listOfMovies.load(ListOfContent.MOVIE);

//        TVShow firstTVShow = new TVShow(3, 30, "Stranger Things", "9/21/2016", 5);
//        TVShow firstTVShow = new TVShow("Stranger Things");
//        Movie firstMovie = new Movie("Interstellar", "2014");
//
//        listOfTVShows.insert(firstTVShow);
//        listOfMovies.insert(firstMovie);

        listOfTVShows.inputContent("TVShow");


    }

}



