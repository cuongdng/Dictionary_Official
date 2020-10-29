package main;

public class Word {
    private String word_target;
    private String word_explain;

    /**
     * Getter, Setter.
     */
    public String getWordTarget() {
        return word_target;
    }

    public void setWordTarget(String s) {
        word_target = s;
    }

    public String getWordExplain() {
        return word_explain;
    }

    public void setWordExplain(String s) {
        word_explain = s;
    }

    Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public static void main(String[] args) {
    }
}
