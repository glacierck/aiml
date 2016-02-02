/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.mleiria.vo;

import java.util.List;

/**
 *
 * @author manuel
 */
public class QuestionVO {

    private String question;
    private String discipline;
    private int whatYear;
    private List<AnswerVO> answers;

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the discipline
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * @param discipline the discipline to set
     */
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    /**
     * @return the whatYear
     */
    public int getWhatYear() {
        return whatYear;
    }

    /**
     * @param whatYear the whatYear to set
     */
    public void setWhatYear(int whatYear) {
        this.whatYear = whatYear;
    }

    /**
     * @return the answers
     */
    public List<AnswerVO> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(List<AnswerVO> answers) {
        this.answers = answers;
    }
    
    
}
