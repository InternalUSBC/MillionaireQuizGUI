package Controller;

import Models.*;
import View.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static View.gameSoundView.*;

/**
 * Created by izaac on 22/05/2017.
 */
public class Game implements ActionListener
{
    private QuizDataBase_Connection con = new QuizDataBase_Connection();
    private SetFinalGameQuestions QuizQuestions = new SetFinalGameQuestions();
    private ShuffleAnswers SortedAnswers = new ShuffleAnswers();
    private QuestionNumber question = new QuestionNumber();
    private LifeLines lifeLines = new LifeLines();
    private AskTheAudience askTheAudience = new AskTheAudience();
    private Username userName = new Username();
    private HighScores highScores = new HighScores();
    private PrizeMoney prizeMoney = new PrizeMoney();
    private gameSoundView sounds = new gameSoundView();
    private UsernameView usernameView;
    private String UserName;

    private String source;
    private Card card;
    private int question_Number;


    public ArrayList<String> getRoundAnswers()
    {
        return SortedAnswers.getShuffledAnswers();
    }

    //creates a new game each time the new game button is pressed

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


    /*this method listens for actions that are performed by the actionlisteners stored
    * inside the classes that i declare. When i receives the actionlistener it acts upon the */
    public void actionPerformed(ActionEvent actionEvent)
    {

            source = ((Component) actionEvent.getSource()).getName();

            if(question.getQuestionNumber() == 14 && source.equalsIgnoreCase(QuizQuestions.getQuestion(question.getQuestionNumber()).getAnswer()))
            {
                sounds.stop();
                sounds.playGameSound(millionDollarSound);
                ExitButtonView exitButtonView = new ExitButtonView(this, question.getQuestionNumber());
                card.addCardToStack(exitButtonView, "exitButton");
            }
            else if (source.equalsIgnoreCase(QuizQuestions.getQuestion(question.getQuestionNumber()).getAnswer()))
            {
                question_Number++;
                question.setCurrentNumber(question_Number);
                sounds.stop();
                sounds.playGameSound(correctSound);
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
                    sounds.playGameSound(questionSound);
                }
            }
            else if (source.equalsIgnoreCase("nextQuestionButton"))
            {
                sounds.playGameSound(questionSound);
                QuizQuestionView NextQuestionView = new QuizQuestionView(this);
                card.addCardToStack(NextQuestionView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            } else if (source.equalsIgnoreCase("returnToMenuBTN"))
            {
                sounds.playGameSound(exitSound);
                ExitButtonView exitButtonView = new ExitButtonView(this, question.getQuestionNumber());
                card.addCardToStack(exitButtonView, "ExitMessage");
            } else if (source.equalsIgnoreCase("exitButton"))
            {
                sounds.stop();
                sounds.playGameSound(exitSound);
                ExitButtonView exitButtonView = new ExitButtonView(this, question.getQuestionNumber());
                card.addCardToStack(exitButtonView, "ExitMessage");
            }
            else if(source.equalsIgnoreCase("fiftyFiftyButton") && lifeLines.getLifeLines(0) == false) {
                lifeLines.usedLifeLine(0);
                sounds.stop();
                sounds.playGameSound(fiftyFiftySound);
                FiftyFiftyView FiftyFiftyView = new FiftyFiftyView(this);
                card.addCardToStack(FiftyFiftyView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            }
            else if(source.equalsIgnoreCase("phoneAFriendButton") && lifeLines.getLifeLines(1) == false)
            {
                lifeLines.usedLifeLine(1);
                sounds.stop();
                sounds.playGameSound(phoneAFriendSound);
                PhoneAFriendView phoneAFriendView = new PhoneAFriendView(this, UserName);
                card.addCardToStack(phoneAFriendView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            }
            else if(source.equalsIgnoreCase("askTheAudienceButton") && lifeLines.getLifeLines(2) == false)
            {
                lifeLines.usedLifeLine(2);
                sounds.stop();
                sounds.playGameSound(askTheAudienceSound);
                askTheAudience.setAskTheAudience(question.getQuestionNumber());
                AskTheAudienceView askTheAudienceView = new AskTheAudienceView(this);
                card.addCardToStack(askTheAudienceView, QuizQuestions.getQuestion(question.getQuestionNumber()).getUUID());
            }
            else if(source.equalsIgnoreCase("mainMenuButton"))
            {
                highScores.setNewHighScores(UserName, prizeMoney.getPrizeMoney(question.getQuestionNumber()));
                con.updateHighScoreDatabase(UserName, prizeMoney.getPrizeMoney(question.getQuestionNumber()));
                HighScoreView highScoreView = new HighScoreView(this);
                card.addCardToStack(highScoreView, "highscores");
            }
            else if(source.equalsIgnoreCase("menuButton"))
            {
                sounds.stop();
                sounds.playGameSound(introSound);
                card.showCard("Menu");
            }
            else
            {
                sounds.stop();
                sounds.playGameSound(incorrectSound);
                QuizIncorrectView incorrectView = new QuizIncorrectView(this);
                card.addCardToStack(incorrectView, "Incorrect");
            }
    }


}








