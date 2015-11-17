public class ChildrensMovie extends Movie {

    public ChildrensMovie(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
        if (daysRented <= 3)
            return 1.5;
        else
            return 1.5 + (daysRented - 3) * 1.5;
    }

    @Override
    int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
