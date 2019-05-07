import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

class Customer {
    private String name;
    private ArrayList<Rental> rentals = new ArrayList();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        for (Rental rental: rentals) {
            double movieAmount = 0;
            //determine amounts for each line
            movieAmount = amountFor(rental);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t" + "\t" + rental.getDaysRented() + "\t" + movieAmount + "\n";
            totalAmount += movieAmount;
        }

        //add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        double movieAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                movieAmount += 2;
                if (rental.getDaysRented() > 2)
                    movieAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                movieAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                movieAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    movieAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return movieAmount;
    }

}
    