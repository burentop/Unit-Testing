package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;
    private User user;
    private User otherUser;

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
        user = board.createUser("anon");
        otherUser = board.createUser("dude");
    }

    @Test
    public void afterUpvoteQuestionersRepIncremented() throws Exception {
        Question question = user.askQuestion("Is this thing on?");
        int rep = user.getReputation();

        otherUser.upVote(question);

        assertEquals(rep + 5, user.getReputation());
    }


}