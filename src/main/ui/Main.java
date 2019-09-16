package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<TVShow> listOfShows = new ArrayList<>();

    public static void main(String[] args) {

        TVShow firstTVShow = new TVShow(3, 30, "Stranger Things", "9/21/2016", 5);
        listOfShows.add(firstTVShow);
        System.out.println(listOfShows.get(0).getTitle());
        getInput();

    }

    private static void getInput() {
        boolean entering = true;
        Scanner in = new Scanner(System.in);
        while (entering) {
            String input;
            System.out.println("Enter \"N\" to input a new TV Show or \"Q\" to quit:");
            input = in.nextLine();
            if (input.equals("N")) {
                System.out.println("Enter TV show name:");
                listOfShows.add(new TVShow(in.nextLine()));
            } else if (input.equals("Q")) {
                entering = false;
            } else {
                System.out.println("Invalid Input");
            }
        }

    }
}
