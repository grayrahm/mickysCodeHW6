public class UnknownCandidateException extends Exception {
    private String unknownCand;

    public UnknownCandidateException(String unknownCand) {
        this.unknownCand = unknownCand;
    }

    /**
     * Get the unknown candidate's name from the voter
     * @return the unknown candidate's name
     */
    public String getUnknownCand() {
        return this.unknownCand;
    }
}