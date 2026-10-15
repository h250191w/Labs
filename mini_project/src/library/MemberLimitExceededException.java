package library;

/** Thrown when a member tries to exceed the maximum number of simultaneous loans. */
public class MemberLimitExceededException extends Exception {
    private static final long serialVersionUID = 1L;


    public MemberLimitExceededException(Member member) {
        super(member.getName() + " (" + member.getId() + ") has reached the limit of "
                + Member.MAX_LOANS + " loans");
    }
}
