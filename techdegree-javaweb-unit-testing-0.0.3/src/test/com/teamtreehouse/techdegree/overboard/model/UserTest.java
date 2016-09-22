package com.teamtreehouse.techdegree.overboard.model;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;
    private User asker;
    private User answerer;
    private Question question;
    private Answer answer;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void afterAnswerAcceptanceAnswerersRepIncremented() throws Exception {
        int rep = answerer.getReputation();

        asker.acceptAnswer(answer);

        assertEquals(rep + 15, answerer.getReputation());
    }

    @Test
    public void userCannotUpDownVoteOwnPost() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        asker.upVote(question);
        asker.downVote(question);
        answerer.upVote(answer);
        answerer.downVote(answer);
    }

    @Test
    public void onlyAskerCanAcceptAnswer() throws Exception {
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage("Only anon can accept this answer as it is their question");

        answerer.acceptAnswer(answer);
    }

    @Test
    public void downVoteQuestionDoesNotAffectRep() throws Exception {
        int rep = asker.getReputation();

        answerer.downVote(question);

        assertEquals(rep, asker.getReputation());
    }


}