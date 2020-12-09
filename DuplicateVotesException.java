public class DuplicateVotesException extends Exception{
    private String dupName;

    public DuplicateVotesException(String dupName) {
        this.dupName = dupName;
    }

    /**
     * Get the name of the candidate that got voted more than 2 times by a voter
     * @return the name of the candidate that got voted more than 2 times by a voter
     */
    public String getDupName() {
        return this.dupName;
    }
}