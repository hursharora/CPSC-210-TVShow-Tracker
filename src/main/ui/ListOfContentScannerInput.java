package ui;

import model.Content;

import java.util.List;

public abstract class ListOfContentScannerInput implements Iterable<Content> {

    protected List<Content> listOfContent;

    //REQUIRES: given type must be "Movie" or "TVShow"
    //MODIFIES: this
    //EFFECTS: asks user if they want to input given content type, if
    //inputted will add content to this list
//    public void inputContent(String type) {
//        boolean entering = true;
//        Scanner in = new Scanner(System.in);
//        String input;
//        while (entering) {
//            System.out.println("Enter \"N\" to input a new " + type + " or \"Q\" to save and quit:");
//            input = in.nextLine();
//            if (input.equals("N")) {
//                insert(getMovieTVInput(type));
//            } else if (input.equals("Q")) {
//                entering = false;
//                trySaving(type);
//            } else {
//                System.out.println("Invalid Input");
//            }
//        }
//    }

//    private void trySaving(String type) {
//        try {
//            save(type);
//        } catch (InvalidContentTypeException e) {
//            System.out.println("Not Saved, Invalid content type!");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("File not found, created new file");
//        } finally {
//            System.out.println("Exceptions handled");
//        }
//    }


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


    //REQUIRES: given type must be "Movie" or "TVShow"
    //EFFECTS: Helper method for inputContent, lets user enter Show/Movie
    //name via scanner
//    private Content getMovieTVInput(String type) {
//        System.out.println("Enter " + type + " name: ");
//        Scanner in = new Scanner(System.in);
//        return ContentFactory.getContent(type, in.nextLine());
//    }


}
