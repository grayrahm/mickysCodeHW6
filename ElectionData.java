import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

class ElectionData {
    private HashMap<String, Integer> firstChoice = new HashMap<String, Integer>();
    private HashMap<String, Integer> secondChoice = new HashMap<String, Integer>();
    private HashMap<String, Integer> thirdChoice = new HashMap<String, Integer>();
    private LinkedList<String> ballot = new LinkedList<String>();

    ElectionData() {
        this.firstChoice = new HashMap<String, Integer>();
        this.secondChoice = new HashMap<String, Integer>();
        this.thirdChoice = new HashMap<String, Integer>();
    }

    // given in starter file
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    /**
     * Process the voters' vote and put them to appropriate hash map
     * @param first the voter's first choice candidates
     * @param second the voter's second choice candidates
     * @param third the voter's third choice candidates
     * @throws UnknownCandidateException when voter votes for candidate that not in the ballot
     * @throws DuplicateVotesException when voter votes for the same candidate in two or more choices
     */
    public void processVote(String first, String second, String third)
            throws UnknownCandidateException, DuplicateVotesException {

        if (!ballot.contains(first)) {
            throw new UnknownCandidateException(first);
        }
        else if (!ballot.contains(second)) {
            throw new UnknownCandidateException(second);
        }
        else if (!ballot.contains(third)) {
            throw new UnknownCandidateException(third);
        }

        else if (first.equals(second)) {
            throw new DuplicateVotesException(second);
        }
        else if (second.equals(third)){
            throw new DuplicateVotesException(third);
        }
        else if(third.equals(first)){
            throw new DuplicateVotesException(first);
        }

        if(firstChoice.containsKey(first)){
            this.firstChoice.put(first, this.firstChoice.get(first) + 1);
        }

        if(secondChoice.containsKey(second)){
            this.secondChoice.put(first, this.secondChoice.get(first) + 1);
        }

        if(thirdChoice.containsKey(third)) {
            this.thirdChoice.put(third, this.thirdChoice.get(third) + 1);
        }

    }

    /**
     * Add the indicated candidate to the ballot
     * @param cand name of the candidate that we want to add to the ballot
     * @throws CandidateExistsException when the candidate is already in the ballot
     */
    public void addCandidate(String cand) throws CandidateExistsException{
        if(!ballot.contains(cand)) {
            this.ballot.add(cand);
            this.firstChoice.put(cand, 0);
            this.secondChoice.put(cand, 0);
            this.thirdChoice.put(cand, 0);

        }
        else throw new CandidateExistsException(cand);
    }

    /**
     * Find the candidate that has more than 50% of the first place vote
     * @return the name of the candidate that has the most vote
     */
    public String findWinnerMostFirstVotes() {
        int totalFirstVote = 0;
        for(String candidate : ballot) {
            if (!firstChoice.containsKey(candidate)) {
                totalFirstVote = totalFirstVote;
            }
            else if(firstChoice.containsKey(candidate)) {
                totalFirstVote = totalFirstVote + this.firstChoice.get(candidate);
            }
        }
        for (String candidate : ballot) {
            if (firstChoice.containsKey(candidate)) {
                if(this.firstChoice.get(candidate) > totalFirstVote/2){
                    return candidate;
                }
            }
        }
        return "Runoff required";
    }


    /**
     * Find the candidate with the most points after weighted points from each choice
     * @return the name of the candidate with the most point
     */
    public String findWinnerMostPoints(){
        int winnerPoint = 0;
        int candPoint = 0;
        String winner = "";
        for(String candidate : ballot) {
            candPoint = 0;
            if(firstChoice.containsKey(candidate)) {
                candPoint = this.firstChoice.get(candidate) *3;
            }
            if (secondChoice.containsKey(candidate)) {
                candPoint = candPoint + this.secondChoice.get(candidate) * 2;
            }
            if (thirdChoice.containsKey(candidate)) {
                candPoint = candPoint + this.thirdChoice.get(candidate);
            }
            if(winnerPoint <= candPoint) {
                winnerPoint = candPoint;
                winner = candidate;
            }
        }
        return winner;
    }
}
