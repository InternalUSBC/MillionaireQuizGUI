package Controller;

import Models.*;
import View.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by izaac on 22/05/2017.
 */
public class Game implements ActionListener
{
    private QuestionDataBase CompleteQuizQuestions = new QuestionDataBase();
    private SetFinalGameQuestions QuizQuestions = new SetFinalGameQuestions();
    private ShuffleAnswers SortedAnswers = new ShuffleAnswers();
    private QuestionNumber question = new QuestionNumber();
    private LifeLines lifeLines = new LifeLines();
    private AskTheAudience askTheAudience = new AskTheAudience();
    private Username userName = new Username();
    private UsernameView usernameView;
    private String UserName;

    private String source;
    private Card card;
    private int question_Number;


    public ArrayList<String> getRoundAnswers()
    {
        return SortedAnswers.getShuffledAnswers();
    }


    public Game(Card card){
        this.card = card;
        QuizQuestions.setFinalQuestions();
        lifeLines.setLifeLines();
        userName.setUserInput(true);
        question_Number = 0;
        question.setCurrentNumber(question_Number);

        usernameView = new UsernameView(this);
        card.addCardToStack(usernameView, "username");

    }


    public void actionPerformed(ActionEvent actionEvent)
    {

            source = ((Component) actionEvent.getSource()).getName();


            if (source.equalsIgnoreCase(QuizQuestions.getQuestion(question.getQuestionNumber()).getAnswer()))
            {
                question_Number++;
                question.setCurrentNumber(question_Number);

                QuizCorrectView correctView = new QuizCorrectView(this);
                card.addCardToStack(correctView, "Correct");

                card.showCard("Correct");

            }
            else if(source.equalsIgnoreCase("submitUsernameButton"))
            {

                UserName = usernameView.getUsername().getText().toString();

                if(UserName.equalsIgnoreCase(""))
                {
                    userName.setUserInput(false);
                    usernameView = new UsernameView(this);
                    card.addCardToStack(usernameView, "userNAME");
                }
                else
                {
                    QuizQuestionView FirstQuestionView = new QuizQuestionView(this);
                    card.addCardToStack(FirstQuestionView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
                }
            }
            else if (source.equalsIgnoreCase("nextQuestionButton")) {

                QuizQuestionView NextQuestionView = new QuizQuestionView(this);
                card.addCardToStack(NextQuestionView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            } else if (source.equalsIgnoreCase("returnToMenuBTN")) {
                card.showCard("Menu");
            } else if (source.equalsIgnoreCase("exitButton"))
            {
                ExitButtonView exitButtonView = new ExitButtonView(this);
                card.addCardToStack(exitButtonView, "ExitMessage");
            }
            else if(source.equalsIgnoreCase("fiftyFiftyButton") && lifeLines.getLifeLines(0) == false) {
                lifeLines.usedLifeLine(0);
                FiftyFiftyView FiftyFiftyView = new FiftyFiftyView(this);
                card.addCardToStack(FiftyFiftyView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            }
            else if(source.equalsIgnoreCase("phoneAFriendButton") && lifeLines.getLifeLines(1) == false)
            {
                lifeLines.usedLifeLine(1);
                PhoneAFriendView phoneAFriendView = new PhoneAFriendView(this, UserName);
                card.addCardToStack(phoneAFriendView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            }
            else if(source.equalsIgnoreCase("askTheAudienceButton") && lifeLines.getLifeLines(2) == false)
            {
                lifeLines.usedLifeLine(2);
                askTheAudience.setAskTheAudience(question.getQuestionNumber());
                AskTheAudienceView askTheAudienceView = new AskTheAudienceView(this);
                card.addCardToStack(askTheAudienceView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            }
            else if(source.equalsIgnoreCase("mainMenuButton"))
            {
                HighScoreView highScoreView = new HighScoreView(this);
                card.addCardToStack(highScoreView, "highscores");
            }
            else if(source.equalsIgnoreCase("menuButton"))
            {
                card.showCard("Menu");
            }
            else
            {
                QuizIncorrectView incorrectView = new QuizIncorrectView(this);
                card.addCardToStack(incorrectView, "Incorrect");
            }
    }


}








