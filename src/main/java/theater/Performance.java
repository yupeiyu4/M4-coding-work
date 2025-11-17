package theater;

/**
 * A single performance entry for an invoice.
 */
public class Performance {
    private final String playID;
    private final int audience;

    /**
     * Creates a performance.
     *
     * @param playID   the play identifier
     * @param audience audience size
     */
    public Performance(final String playID, final int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    /**
     * Returns the play id for this performance.
     *
     * @return play id
     */
    public String getPlayID() {
        return playID;
    }

    /**
     * Returns the audience size.
     *
     * @return audience size
     */
    public int getAudience() {
        return audience;
    }
}
