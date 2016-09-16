package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;
    private User asker;
    private User answerer;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
        asker = board.createUser("anon");
        answerer = board.createUser("dude");
        question = asker.askQuestion("Is this thing on?");
        answer = answerer.answerQuestion(question, "It appears so.");
    }

    @Test
    public void afterUpvoteQuestionersRepIncremented() throws Exception {
        int rep = asker.getReputation();

        answerer.upVote(question);

        assertEquals(rep + 5, asker.getReputation());
    }

    @Test
    public void afterUpvoteAnswerersRepIncremented() throws Exception {
        int rep = answerer.getReputation();

        asker.upVote(answer);

        assertEquals(rep + 10, answerer.getReputation());
    }


}