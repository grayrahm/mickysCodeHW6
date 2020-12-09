import java.util.Scanner;

public class VotingMachine {
    private Scanner keyboard = new Scanner(System.in);
    private ElectionData electionData;

    ElectionData ED = new ElectionData();
    public VotingMachine(ElectionData electionData){this.electionData = electionData;}

    /**
     * Take in the voters' choices. If any candidate is unknown, ask whether want to add in the ballot.
     * Catch exception whenever the voters vote for the same candidate more than two times.
     */
    public void screen() {
        ED.printBallot();
        System.out.println("Who is your first choice?");
        String firstChoiceCand = keyboard.nextLine();
        System.out.println("Who is your second choice?");
        String secondChoiceCand = keyboard.nextLine();
        System.out.println("Who is your third choice?");
        String thirdChoiceCand = keyboard.nextLine();
        System.out.println("You voted for " + firstChoiceCand + "," + " "
                + secondChoiceCand + "," + " " + thirdChoiceCand);
        try {
            ED.processVote(firstChoiceCand, secondChoiceCand, thirdChoiceCand);
        }
        catch (UnknownCandidateException e) {
            System.out.println("This " + e.getUnknownCand() + " " +
                    "candidate is not in the ballot. " +
                    "Would you like to add this candidate to the ballot? Type Y for Yes, N for No");
            String newCand = keyboard.nextLine();
            if(newCand.equals("Y") || newCand.equals("y")){
                this.addWriteIn(e.getUnknownCand());
            }
            this.screen();
        }
        catch (DuplicateVotesException e) {
            System.out.println("Duplicate vote for" + e.getDupName() + ". Please vote again!");
            this.screen();
        }
    }


    /**
     * Add the new candidate to the ballot
     * @param name name that the candidate want to add
     */
    public void addWriteIn(String name)  {
        try {ED.addCandidate(name);}
        catch (CandidateExistsException e) {
            System.out.println("This " + name + "candidate is already in the ballot");
        }
        System.out.println("Added candidate successfully");
    }
}
