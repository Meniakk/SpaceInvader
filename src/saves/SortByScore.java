package saves;
import java.util.Comparator;

/**
 * A class of SortByScore.
 */
public class SortByScore implements Comparator<ScoreInfo> {

    /**
     * comparing two scoreInfo-s.
     * @param a the first scoreinfo.
     * @param b the second scoreinfo.
     * @return Positive if a bigger, Negative if b bigger, else 0.
     */
    public int compare(ScoreInfo a, ScoreInfo b) {

        if (a == null && b == null) {
            return 0;
        } else if (a == null && b != null) {
            return 1;
        } else if (a != null && b == null) {
            return -1;
        } else {
            return b.getScore() - a.getScore();
        }

    }

}

