public class CandidateExistsException extends Exception {
    String existedName;

    public CandidateExistsException(String name) {
        this.existedName = name;
    }

}