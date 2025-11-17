package theater;

import java.util.List;

/**
 * Customer invoice holding all performances.
 */
public class Invoice {
    private final String customer;
    private final List<Performance> performances;

    /**
     * Creates an invoice.
     *
     * @param customer      the customer name
     * @param performances  the list of performances
     */
    public Invoice(final String customer, final List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    /**
     * Returns the customer name.
     *
     * @return customer name
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Returns the performances in this invoice.
     *
     * @return list of performances
     */
    public List<Performance> getPerformances() {
        return performances;
    }
}
