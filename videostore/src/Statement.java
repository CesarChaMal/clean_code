import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Statement {

    private double totalAmount;
    private int frequentRenterPoints;
    private String customerName;
    private List<Rental> rentals = new ArrayList<>();

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public String generate() {
        clearTotals();
        return header() + rentalLines() + footer();
    }

    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    private String header() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private String rentalLines() {
        return rentals.stream()
                      .map(this::processRentalLine)
                      .collect(joining());
    }

    private String processRentalLine(Rental rental) {
        double rentalAmount = rental.determineAmount();

        frequentRenterPoints += rental.determineFrequentRenterPoints();
        totalAmount += rentalAmount;

        return rentalLine(rental, rentalAmount);
    }

    private String rentalLine(Rental rental, double amount) {
        return String.format("\t%s\t%s\n", rental.getTitle(), amount);
    }

    private String footer() {
        return String.format("You owed %.1f\n"
            + "You earned %d frequent renter points\n", totalAmount, frequentRenterPoints);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

}