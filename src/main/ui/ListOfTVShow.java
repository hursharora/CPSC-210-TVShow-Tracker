package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Represents a list of TV Shows
public class ListOfTVShow {

    private List<TVShow> listOfShow;

    //EFFECTS: Creates empty TV show list
    public ListOfTVShow() {
        listOfShow = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: TV Show in is added to the list if not already in list
    public void insert(TVShow in) {
        System.out.println("Added \"" + in.getTitle() + "\" to list of shows.");
        if (!contains(in)) {
            listOfShow.add(in);
        }
    }

    //EFFECTS: Returns true if TVShow is in the list, false otherwise
    public boolean contains(TVShow in) {
        return listOfShow.contains(in);
    }

    //EFFECTS: Returns number of items in TV show list
    public int size() {
        return listOfShow.size();
    }

    //EFFECTS: asks user if they want to input a TVShow, if
    //inputted will return TVShow, null otherwise
    public TVShow inputAShow() {
        boolean entering = true;
        Scanner in = new Scanner(System.in);
        while (entering) {
            String input;
            System.out.println("Enter \"N\" to input a new TV Show or \"Q\" to quit:");
            input = in.nextLine();
            if (input.equals("N")) {
                System.out.println("Enter TV show name:");
                return new TVShow(in.nextLine());
            } else if (input.equals("Q")) {
                entering = false;
            } else {
                System.out.println("Invalid Input");
            }
        }
        return null;

    }
}
