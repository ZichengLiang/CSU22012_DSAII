import java.util.Comparator;

/**
 * Freely inspired by the implementation found in "Algorithms"
 * by  Robert Sedgewick and Kevin Wayne
 */
public class Selection {

    /***************************************************************************
     *  That's the core method. USes the implementation from
     *  Robert Sedgewick and Kevin Wayne's book
     ***************************************************************************/

    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }


    // exchange a[i] and a[j]
    private void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    // is the array a[] sorted?
    private boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array a[] sorted?
    private boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(comparator, a[i], a[i-1])) return false;
        return true;
    }



    // print array to standard output
    private void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private class BudgetComparator implements Comparator<Movie> {
        @Override
        public int compare (Movie a, Movie b) {
            if (a.getBudget() > b.getBudget()) return 1;
            else if (a.getBudget() < b.getBudget()) return -1;
            else return 0;
        }
    }

    public static void main(String[] args) {

        Movie[] movies = {
                new Movie("The Shawshank Redemption", 1994, "Frank Darabont", 25000000, 4.8),
                new Movie("The Dark Knight", 2008, "Christopher Nolan", 185000000, 4.7),
                new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 8000000, 4.6),
                new Movie("Inception", 2010, "Christopher Nolan", 160000000, 4.5),
                new Movie("The Matrix", 1999, "The Wachowskis", 63000000, 4.4),
                new Movie("Forrest Gump", 1994, "Robert Zemeckis", 55000000, 4.3),
                new Movie("The Lord of the Rings: The Return of the King", 2003, "Peter Jackson", 94000000, 4.2),
                new Movie("Spirited Away", 2001, "Hayao Miyazaki", 19000000, 4.1),
                new Movie("Saving Private Ryan", 1998, "Steven Spielberg", 70000000, 4.0),
                new Movie("The Green Mile", 1999, "Frank Darabont", 60000000, 3.9),
                new Movie("The Godfather", 1972, "Francis Ford Coppola", 6000000, 4.7),
                new Movie("The Silence of the Lambs", 1991, "Jonathan Demme", 19000000, 4.6),
                new Movie("Schindler's List", 1993, "Steven Spielberg", 22000000, 4.5),
                new Movie("12 Angry Men", 1957, "Sidney Lumet", 350000, 4.4),
                new Movie("The Lord of the Rings: The Fellowship of the Ring", 2001, "Peter Jackson", 93000000, 4.3),
                new Movie("Parasite", 2019, "Bong Joon-ho", 11400000, 4.2),
                new Movie("Interstellar", 2014, "Christopher Nolan", 165000000, 4.1),
                new Movie("Whiplash", 2014, "Damien Chazelle", 3300000, 4.0),
                new Movie("The Grand Budapest Hotel", 2014, "Wes Anderson", 25000000, 3.9),
                new Movie("The Dark Knight Rises", 2012, "Christopher Nolan", 250000000, 3.8),
                new Movie("The Godfather Part II", 1974, "Francis Ford Coppola", 13000000, 4.6),
                new Movie("The Lord of the Rings: The Two Towers", 2002, "Peter Jackson", 94000000, 4.4),
                new Movie("Seven", 1995, "David Fincher", 33000000, 4.3),
                new Movie("Life Is Beautiful", 1997, "Roberto Benigni", 20000000, 4.1)
        };
        
        Selection selectionSort = new Selection();

        selectionSort.sort(movies);
        System.out.println("Sorted by rating:"); //change the method compareTo in Movie Class to change that
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}


