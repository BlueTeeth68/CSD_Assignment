package data;

public class Vocabulary implements Comparable<Vocabulary> {

    protected String word;
    protected String meaning;

    public Vocabulary(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void showInformation() {
        System.out.printf("|%-20s|%-70s|\n", word, meaning);            // sửa chỗ này
    }

    public int compareTo(Vocabulary that) {
        return this.word.compareToIgnoreCase(that.word);
    }

    @Override
    public String toString() {
        return word;
    }

}
