package data;

import datastucture.BST;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tool.Tool;

public class Dictionary {

    private Scanner sc = new Scanner(System.in);

    private BST<Vocabulary> b = new BST();

    public void readFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                if (line.trim() != "") {
                    String data[] = line.split(";");
                    String word, meaning;
                    word = data[0].toString();
                    meaning = data[1].toString();
                    Vocabulary v = new Vocabulary(word, meaning);

                    //Nếu từ chưa có trong cây thì mới thêm vào
                    if (!isVocaExisted(word)) {
                        b.add(v);
                    }
                }
            }
            read.close();
        } catch (Exception e) {
            System.out.println("An error occured when read file!");
            e.printStackTrace();
        }
    }

    public void writeFile(String fileName) {

        try {
            File myFile = new File(fileName);
            myFile.createNewFile();

            FileWriter myWriter = new FileWriter(fileName);
            ArrayList<Vocabulary> arr = new ArrayList<>();
            arr = b.toArray();
            for (int i = 0; i < arr.size(); i++) {
                String data = arr.get(i).word + ";" + arr.get(i).meaning + "\n";
                myWriter.write(data);
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving file!");
            e.printStackTrace();
        }
    }

    public void simpleBalancedTree() {
        if (!b.isBalanced(b.root)) {
            b.balanceTree();
        }
        System.out.println("Tree has been balanced.");
    }

    //Hàm thêm một từ mới
    public void addANewWord() {
        String word, meaning;
        word = Tool.getString("Input new word: ", "Word cann't be empty.");

        // Check thử từ đã tồn tại hay chưa
        boolean choice = false;

        //Nếu từ đã tồn tại thì hỏi có muốn sửa hay không
        if (b.search(new Vocabulary(word, "")) != null) {
            Vocabulary tmp = (Vocabulary) b.search(new Vocabulary(word, "")).val;
            tmp.showInformation();
            System.out.println("This word has been existed. Do you want to change its meaning?");
            choice = askYN("Input your choice (Y/N):", "Choice cann't be empty.");
            if (choice) {
                meaning = Tool.getString("Input word's meaning: ", "Meaning cann't be empty.");
                Vocabulary v = new Vocabulary(word, meaning);
                b.search(new Vocabulary(word, "")).val = v;
                System.out.println("Add new word successfully.");
                System.out.println("Do you want to continue to add another word?");
                choice = askYN("Input your choice (Y/N):", "Choice cann't be empty.");
                if (choice) {
                    addANewWord();
                }
            } else {
                System.out.println("Add word fail!");
            }
        } else {

            //Nếu chưa tồn tại thì nhập nghĩa
            meaning = Tool.getString("Input word's meaning: ", "Meaning cann't be empty.");
            Vocabulary v = new Vocabulary(word, meaning);
            b.add(v);
            System.out.println("Add new word successfully.");
            System.out.println("Do you want to continue to add another word?");
            choice = askYN("Input your choice (Y/N):", "Choice cann't be empty.");
            if (choice) {
                addANewWord();
            } else {
                if (!b.isBalanced(b.root)) {
                    b.balanceTree();
                }
            }
        }
    }

    //Hàm xử lí các câu hỏi yes no
    private boolean askYN(String inforMessage, String errorMessage) {
        while (true) {
            String choice;
            choice = Tool.getString(inforMessage, errorMessage);
            if (choice.equalsIgnoreCase("y")) {
                return true;
            } else if (choice.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid value!!!");
                System.out.println("Enter y/Y if yes.");
                System.out.println("Enter n/N if no.");
            }
        }
    }

    //Check xem từ đó có tồn tại hay chưa
    public boolean isVocaExisted(String word) {
        if (b.search(new Vocabulary(word, "")) == null) {   // sửa một chút
            return false;
        }
        return true;
    }

    //Xóa một từ
    public void deleteAWord() {
        String word = Tool.getString("Input the word you want to delete: ", "Word cann't be empty!");
        Vocabulary voca = new Vocabulary(word, "");

        //Tìm thử từ đó có trong danh sách không 
        if (b.search(voca) == null) {
            //Nếu chưa có thì báo lỗi
            System.out.println("Delete fail: Vocabulary " + word + " doesn't exist.");
        } else {
            //Nếu tìm thấy thì show thông tin từ đó ra
            voca = (Vocabulary) b.search(voca).val;
            System.out.println("");
            System.out.println("This is the vocabulary you want to delete: ");
            voca.showInformation();
            System.out.println("Are you sure to delete this word?");
            boolean choice = askYN("Input your choice(Y/N): ", "Choice cann't be empty!");
            System.out.println("");
            if (choice) {
                b.deleteNode(voca);
                System.out.println("Delete voca " + voca.word + " successfully.");
                System.out.println("");

                //Hỏi có muốn xóa thêm hay không
                System.out.println("Do you want to delete another voca?");
                choice = askYN("Input your choice(Y/N): ", "Choice cann't be empty!");
                if (choice) {
                    deleteAWord();
                } else {
                    // Nếu không muốn xóa nữa thì cân bằng lại cây 
                    if (!b.isBalanced(b.root)) {
                        b.balanceTree();
                    }
                }
            } else {
                System.out.println("Delete fail.");
            }
        }
    }

    //Tìm kiếm một từ vựng
    public void search() {
        String word = Tool.getString("Input voca you want to search: ", "Voca cann't be empty!");
        Vocabulary voca = new Vocabulary(word, "");

        if (b.search(voca) == null) {
            System.out.println("Voca " + word + " doesn't exist!");
        } else {
            voca = (Vocabulary) b.search(voca).val;
            System.out.println("+--------------------+----------------------------------------------------------------------+");
            voca.showInformation();
            System.out.println("+--------------------+----------------------------------------------------------------------+");
        }
    }

    //In ra đường đi giữa 2 node
    public void printTrack() {
        String src, dest;
        src = Tool.getString("Input first word: ", "Word cann't be empty!");
        dest = Tool.getString("Input second word: ", "Word cann't be empty!");
        Vocabulary vocaSrc = new Vocabulary(src, "");
        Vocabulary vocaDest = new Vocabulary(dest, "");

        b.printTrack(vocaSrc, vocaDest);
    }

    //In ra toàn bộ cây
    public void printAll() {
        ArrayList<Vocabulary> arr = b.toArray();
        System.out.println("+--------------------+----------------------------------------------------------------------+");
        for (Vocabulary x : arr) {
            x.showInformation();
        }
        System.out.println("+--------------------+----------------------------------------------------------------------+");
    }

    public void printRoot() {
        System.out.println(b.root.val.word);
    }

}

