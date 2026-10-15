package library;

/**
  Contract for anything a member can borrow.
 */
public interface Borrowable {

    /**
      Marks the item as borrowed by the given member.
     
      @throws ItemNotAvailableException     if the item is already on loan
      @throws MemberLimitExceededException  if the member already holds the maximum number of loans
     */
    void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException;

    /** Marks the item as returned and available again. */
    void giveBack();
}
