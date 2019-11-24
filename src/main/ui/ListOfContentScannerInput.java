package ui;

import model.Content;

import java.util.List;

public abstract class ListOfContentScannerInput implements Iterable<Content> {

    protected List<Content> listOfContent;



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




}
