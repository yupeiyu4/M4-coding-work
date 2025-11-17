package theater;

/**
 * Constants for pricing and volume credits.
 */
public final class Constants {

    // General
    /** Cents per US dollar. */
    public static final int CENTS_PER_DOLLAR = 100;

    // Volume credits
    /** Audience threshold that earns base credits. */
    public static final int BASE_VOLUME_CREDIT_THRESHOLD = 30;
    /** Extra credit factor for comedy (every 5 attendees). */
    public static final int COMEDY_EXTRA_VOLUME_FACTOR = 5;

    // Tragedy pricing
    /** Base price for tragedy, in cents. */
    public static final int TRAGEDY_BASE = 40000;
    /** Audience threshold where tragedy surcharge starts. */
    public static final int TRAGEDY_STEP_THRESHOLD = 30;
    /** Tragedy surcharge per attendee over the threshold, in cents. */
    public static final int TRAGEDY_STEP = 1000;

    // Comedy pricing
    /** Base price for comedy, in cents. */
    public static final int COMEDY_BASE = 30000;
    /** Audience threshold where comedy surcharge starts. */
    public static final int COMEDY_STEP_THRESHOLD = 20;
    /** Fixed comedy surcharge when audience exceeds threshold, in cents. */
    public static final int COMEDY_STEP = 10000;
    /** Per-seat comedy charge, in cents. */
    public static final int COMEDY_PER_SEAT = 300;
    /** The extra-block constant used in comedy surcharge (was literal 500). */
    public static final int COMEDY_BONUS_BLOCK = 500;

    private Constants() {
        // utility class; do not instantiate
    }
}
