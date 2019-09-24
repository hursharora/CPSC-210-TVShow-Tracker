package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        ListOfTVShow listOfTVShow = new ListOfTVShow();
        TVShow firstTVShow = new TVShow(3, 30, "Stranger Things", "9/21/2016", 5);
        listOfTVShow.insert(firstTVShow);
        TVShow inputtedShow = listOfTVShow.inputAShow();
        if (!(inputtedShow == null)) {
            listOfTVShow.insert(inputtedShow);
        }


    }

}



