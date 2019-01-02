package saves;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class of HighScoresTable.
 */
public class HighScoresTable {

    private List<ScoreInfo> scoreInfoList;
    private int maxSize;


    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     * @param size the array size.
     */
    public HighScoresTable(int size) {
       this.scoreInfoList = new ArrayList<>();
       this.maxSize = size;

    }

    /**
     * Add a high-score.
     * @param score the score to add.
     */
    public void add(ScoreInfo score) {

        /* If table not full, add. */
        if (this.scoreInfoList.size() < this.maxSize) {
            this.scoreInfoList.add(score);
        } else {
            int newScore = score.getScore();

            /* If table is full, check if we are better */
            for (int i = 0; i < this.scoreInfoList.size(); i++) {
                if (this.scoreInfoList.get(i).getScore() < newScore) {
                    /* Adding the new score in the correct space. */
                    this.scoreInfoList.add(i, score);
                    /* Deleting the last score because table is full. */
                    this.scoreInfoList.remove(this.scoreInfoList.size() - 1);
                    break;
                }
            }
        }

        /* Sorting the list */
        Collections.sort(this.scoreInfoList, new SortByScore());
    }

    /**
     * Return table size.
     * @return table size.
     */
    public int size() {
        return this.scoreInfoList.size();
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return new ArrayList<>(this.scoreInfoList);
    }

    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     * @param score the current score.
     * @return he rank of the current score.
    */
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.scoreInfoList.size(); i++) {
            if (this.scoreInfoList.get(i) == null
                    || this.scoreInfoList.get(i).getScore() < score) {
                break;
            }
        }
        /* Returning a number greater than the size. */
        return i + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreInfoList.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     * @param filename The files's name.
     * @throws IOException In case file is bad.
     */
    public void load(File filename) throws IOException {
        /* Current table data is cleared. */
        clear();

        /* Read file, first line is name second is score. */
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            throw e;
        }


        String str;
        while ((str = br.readLine()) != null) {
            /* Casting score to int. */
            int score = Integer.parseInt(br.readLine());
            /* Adding to list. */
            this.scoreInfoList.add(new ScoreInfo(str, score));
        }

        br.close();
    }

    /**
     * Save table data to the specified file.
     * @param filename The files's name.
     * @throws IOException In case file is bad.
     */
    public void save(File filename) throws IOException {

        /* Read file, first line is name second is score. */
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            throw e;
        }

        for (ScoreInfo scoreinfo : this.scoreInfoList) {
            bw.write(scoreinfo.getName());
            bw.newLine();
            bw.write(Integer.toString(scoreinfo.getScore()));
            bw.newLine();
        }



        bw.flush();
        bw.close();
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param filename The files's name.
     * @throws IOException if file is bad.
     * @return a new table.
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {

        /* Read file, first line is name second is score. */
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            throw e;
        }

        /* Creating the new list and table. */
        HighScoresTable newHST;

        /* Counting lines in file. */
        int lineNumber = 0;
        while (br.readLine() != null) {
            lineNumber++;
        }
        newHST = new HighScoresTable(lineNumber / 2);

        /* resetting the reader. */
        br = new BufferedReader(new FileReader(filename));
        String str;
        while ((str = br.readLine()) != null) {
            /* Casting score to int. */
            int score = Integer.parseInt(br.readLine());
            /* Adding to list. */
            newHST.add(new ScoreInfo(str, score));
        }

        br.close();
        return newHST;

    }

}
