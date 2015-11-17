public class RegularMovie extends Movie {

    public RegularMovie(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
        if (daysRented <= 2)
            return 2;
        else
            return 2 + (daysRented - 2) * 1.5;
    }

    @Override
    int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
