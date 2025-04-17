import java.util.Random;

public class QuickMultiPivot {

    private static Random random;
    private static long seed;

    public QuickMultiPivot() {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    // Public sort method that shuffles and calls the recursive dual-pivot sort
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // Recursive method for dual-pivot quicksort
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        // Partition the array with two pivots
        // partition returns an int[] of the pivot indices [p1, p2]
        int[] pivots = partition(a, lo, hi);
        int p1 = pivots[0];
        int p2 = pivots[1];

        // Recursively sort each segment
        // 1) < pivot1
        sort(a, lo, p1 - 1);

        // 2) between pivot1 and pivot2
        sort(a, p1 + 1, p2 - 1);

        // 3) > pivot2
        sort(a, p2 + 1, hi);
    }

    /**
     * Perform dual-pivot partitioning on the subarray from lo to hi (inclusive).
     * Returns an array of two pivot positions: [p1, p2].
     */
    static int[] partition(Comparable[] a, int lo, int hi) {
        //two pivots- in the tight order...
        if (less(a[hi], a[lo])) {
            exch(a, lo, hi);
        }
        Comparable pivot1 = a[lo];
        Comparable pivot2 = a[hi];

        int i  = lo + 1; // current index to examine
        int lt = lo + 1; // boundary for elements < pivot1
        int gt = hi - 1; // boundary for elements > pivot2

        Comparable v = a[lo];

        // Partitioning:
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {exch(a, lt, i); ++lt; ++i;}
            else if (cmp > 0) {exch(a, gt, i); --gt;}
            else {++i;}
        }

        // pivot1 goes to index lt-1, pivot2 goes to index gt+1
        pivot1 = a[lt-1];
        pivot2 = a[gt+1];
        // place pivot1
        exch(a, lo, lt-1);
        // place pivot2
        exch(a, hi, gt+1);

        // Return the indices of the pivots
        return new int[] { lt, gt };
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n-i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    // is the array a[] sorted?
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Example: Sorting an array of Movie objects
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

        QuickMultiPivot qmp = new QuickMultiPivot();
        qmp.sort(movies);

        System.out.println("Sorted by rating (dual-pivot quicksort):");
        for (Movie m : movies) {
            System.out.println(m);
        }

        System.out.println(isSorted(movies)? "sorted" : "not sorted");
    }
}
