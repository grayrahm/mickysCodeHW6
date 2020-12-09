import org.junit.Test;

import javax.rmi.CORBA.Tie;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.Assert.*;

public class Examples {
    // method to set up a ballot and cast votes

    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }


    ElectionData TieSetup () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei","ziggy", "husky");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("husky", "ziggy", "gompei");

        } catch (Exception e) {}

        return(ED);

    }

    ElectionData TieSetup2 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("ziggy");
            ED.addCandidate("husky");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("ziggy", "husky", "gompei");
            ED.processVote("husky", "gompei", "ziggy");

            ED.processVote("gompei","ziggy", "husky");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("husky", "ziggy", "gompei");

        } catch (Exception e) {}

        return(ED);

    }

    LinkedList<String> getTieList(){
        LinkedList<String> tieList = new LinkedList<String>();
        tieList.addFirst("gompei");
        tieList.addFirst("husky");
        tieList.addFirst("ziggy");
        return tieList;

    }

    ElectionData RunoffSetup () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("ziggy", "gompei", "ziggy");
            ED.processVote("ziggy", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }


    // now run a test on a specific election
    @Test
    public void testMostFirstWinner () {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostFirstWinnerTie () {
        assertEquals ("Runoff required", TieSetup().findWinnerMostFirstVotes());
    }

    @Test
    public void testRunoffRequired () {
        assertEquals ("Runoff required", RunoffSetup().findWinnerMostFirstVotes());
    }

    @Test
    public void testWinnerMostPoint () {
        assertEquals ("gompei", Setup1().findWinnerMostPoints());
    }

    @Test
    public void testWinnerMostPointTie () {
        assertTrue (getTieList().contains(TieSetup2().findWinnerMostPoints()));
    }

    @Test(expected=DuplicateVotesException.class)
    public void testDupExcept() throws DuplicateVotesException,UnknownCandidateException
    {
        Setup1().processVote("ziggy", "ziggy", "gompei");
    }

    @Test(expected=UnknownCandidateException.class)
    public void testTwoExceptions() throws DuplicateVotesException,UnknownCandidateException
    {
        Setup1().processVote("ziggy", "ziggy", "emerald");
    }

    @Test(expected=UnknownCandidateException.class)
    public void testUnknownExcept() throws UnknownCandidateException, DuplicateVotesException
    {
        Setup1().processVote("ziggy", "mikaela", "ziggy");
    }

    @Test(expected=CandidateExistsException.class)
    public void testExistException() throws CandidateExistsException
    {
        Setup1().addCandidate("gompei");
    }

}