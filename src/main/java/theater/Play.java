package theater;

/**
 * Represents a play with a name and type (e.g., tragedy/comedy).
 */
public class Play {
    private final String name;
    private final String type;

    /**
     * Creates a play.
     *
     * @param name the play name
     * @param type the play type ("tragedy", "comedy", etc.)
     */
    public Play(final String name, final String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Returns the play name.
     *
     * @return play name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the play type string.
     *
     * @return play type (e.g., "tragedy", "comedy")
     */
    public String getType() {
        return type;
    }
}
