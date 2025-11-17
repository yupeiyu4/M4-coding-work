package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Renders a plain-text statement for the given invoice and plays.
 */
public class StatementPrinter {

    private final Invoice invoice;
    private final Map<String, Play> plays;

    /**
     * Creates a printer bound to an invoice and its play catalogue.
     *
     * @param invoice the invoice to render
     * @param plays   mapping from play id to play
     */
    public StatementPrinter(final Invoice invoice, final Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    /**
     * Builds and returns the plain-text statement.
     *
     * @return statement text
     */
    public String statement() {
        final StringBuilder result = new StringBuilder();
        result.append("Statement for ").append(invoice.getCustomer()).append("\n");

        int totalAmount = 0;
        int volumeCredits = 0;

        for (final Performance performance : invoice.getPerformances()) {
            final int amount = getAmount(performance);
            volumeCredits += getVolumeCredits(performance);

            result.append("  ")
                    .append(getPlay(performance).getName())
                    .append(": ")
                    .append(usd(amount))
                    .append(" (")
                    .append(performance.getAudience())
                    .append(" seats)\n");

            totalAmount += amount;
        }

        result.append("Amount owed is ").append(usd(totalAmount)).append("\n");
        result.append("You earned ").append(volumeCredits).append(" credits\n");

        return result.toString();
    }

    /**
     * Looks up a play from a performance.
     *
     * @param performance the performance to use for lookup
     * @return the corresponding play
     */
    private Play getPlay(final Performance performance) {
        return plays.get(performance.getPlayID());
    }

    /**
     * Computes the amount (in cents) for a single performance.
     *
     * @param performance the performance
     * @return amount in cents
     * @throws IllegalArgumentException if the play type is unknown
     */
    private int getAmount(final Performance performance) {
        final Play play = getPlay(performance);
        final String type = play.getType();
        final int audience = performance.getAudience();

        int result;
        switch (type) {
            case "tragedy":
                result = Constants.TRAGEDY_BASE;
                if (audience > Constants.TRAGEDY_STEP_THRESHOLD) {
                    result += Constants.TRAGEDY_STEP
                            * (audience - Constants.TRAGEDY_STEP_THRESHOLD);
                }
                break;

            case "comedy":
                result = Constants.COMEDY_BASE;
                if (audience > Constants.COMEDY_STEP_THRESHOLD) {
                    result += Constants.COMEDY_STEP
                            + Constants.COMEDY_BONUS_BLOCK
                            * (audience - Constants.COMEDY_STEP_THRESHOLD);
                }
                result += Constants.COMEDY_PER_SEAT * audience;
                break;

            default:
                throw new IllegalArgumentException("unknown type: " + type);
        }
        return result;
    }

    /**
     * Computes the volume credits for a single performance.
     *
     * @param performance the performance
     * @return volume credits earned for this performance
     */
    private int getVolumeCredits(final Performance performance) {
        final Play play = getPlay(performance);
        final int audience = performance.getAudience();

        int result = Math.max(audience - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
        if ("comedy".equals(play.getType())) {
            result += audience / Constants.COMEDY_EXTRA_VOLUME_FACTOR;
        }
        return result;
    }

    /**
     * Formats a cent amount as US currency.
     *
     * @param amountInCents amount in cents
     * @return formatted currency string (e.g., "$123.45")
     */
    private String usd(final int amountInCents) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(amountInCents / (double) Constants.CENTS_PER_DOLLAR);
    }
}
