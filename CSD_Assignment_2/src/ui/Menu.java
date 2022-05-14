package ui;

import java.util.ArrayList;
import tool.Tool;

public class Menu {
    private ArrayList<String> optionList = new ArrayList<String>();

    public Menu() {
    }

    //Thêm vào menu một option
    public void addNewOption(String option) {
        optionList.add(option);
    }

    //in menu ra màn hình
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no option in your menu");
            return;
        }
        for (String s : optionList) {
            System.out.println(s);
        }
    }

    public int getChoice() {
        int maxOption = optionList.size();
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption;
        return Tool.getAnInteger(inputMsg, errorMsg, 1, maxOption);
    }
}
