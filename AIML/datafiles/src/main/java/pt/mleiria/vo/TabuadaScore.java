/**
 *
 */
package pt.mleiria.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author manuel
 *
 */
public class TabuadaScore implements Comparable {

    private int user_id;
    private int scoreTime;
    private int score;
    private int totalQuestions;
    private String username;
    private Date ts;

    public Date getTs() {
        return ts;
    }
    
    public String getFormattedDate(){
        return new SimpleDateFormat("dd-MM-yyyy '' hh:mm:ss").format(ts);
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(int scoreTime) {
        this.scoreTime = scoreTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    @Override
    public String toString() {
        return "[" + user_id + ";" + score + ";" + scoreTime + ";" + totalQuestions + "]";
    }

    public int compareTo(Object o) {
        if (!(o instanceof TabuadaScore)) {
            throw new ClassCastException("Objecto do tipo TabuadaScore esperado");
        }
        int anotherScore = ((TabuadaScore) o).getScore();
        return anotherScore - score;
    }

}
