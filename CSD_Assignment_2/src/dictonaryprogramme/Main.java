package dictonaryprogramme;

import data.Dictionary;
import ui.Menu;
import tool.GetDirectory;

public class Main {

    public static void main(String[] args) {
        String filePath = GetDirectory.DIR + "\\dictionary.txt";
        Menu menu = new Menu();
        menu.addNewOption(String.format("|%-52s|", "1. Add A Word"));
        menu.addNewOption(String.format("|%-52s|", "2. Delete A Word"));
        menu.addNewOption(String.format("|%-52s|", "3. Search A Word"));
        menu.addNewOption(String.format("|%-52s|", "4. Print Path"));
        menu.addNewOption(String.format("|%-52s|", "5. Show Word List"));
        menu.addNewOption(String.format("|%-52s|", "6. Quit"));

        Dictionary d = new Dictionary();
        d.readFile(filePath);
        int choice;
        do {
            System.out.println("+----------------------------------------------------+");
            System.out.println("|           Dictionary Management Program            |");
            System.out.println("+----------------------------------------------------+");
            menu.printMenu();
            System.out.println("+----------------------------------------------------+");
            System.out.println("");
            choice = menu.getChoice();
            System.out.println("");
            switch (choice) {
                case 1:
                    d.addANewWord();
                    d.writeFile(filePath);
                    break;
                case 2:
                    d.deleteAWord();
                    d.writeFile(filePath);
                    break;
                case 3:
                    d.search();
                    break;
                case 4:
                    d.printTrack();
                    break;
                case 5:
                    d.printAll();
                    break;
                case 6:
                    break;
            }
            System.out.println("");
        } while (choice != 6);
    }

}
