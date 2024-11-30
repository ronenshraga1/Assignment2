public class Assignment2 {

    // task 1.1
    // No input assumptions
    // Checks if the given matrix is a valid instance of the Big Trip Problem
    public static boolean isLegalInstance(boolean[][] matrix) {
        boolean result = false; // default return value
        // ---------------write your code BELOW this line only! ------------------
        if (matrix != null && matrix.length > 0) {
            result = isSquareMatrix(matrix) && isSymmetricMatrix(matrix) && isAntiReflexiveMatrix(matrix);
        }

        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 1.2
    // Assumes matrix is not null
    // Checks if the matrix is square.
    public static boolean isSquareMatrix(boolean[][] matrix) {
        boolean result = true; // default return value
        // ---------------write your code BELOW this line only! ------------------
        int arrayLength = 0;
        for(int i=0;i<matrix.length && result;i++){
                if(i==0){
                    arrayLength = matrix[0].length;
                } else if(arrayLength != matrix[i].length){
                    result = false;
                }
            }
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }
    
    // task 1.3
    // Assumes matrix is a non-null, square boolean matrix.
    // Checks if the matrix is symmetric.
    public static boolean isSymmetricMatrix(boolean[][] matrix) {
        boolean result = true; // default return value
        // ---------------write your code BELOW this line only! ------------------
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j< matrix[i].length;j++){
                if(matrix[i][j] != matrix[j][i]){
                    result = false;
                }
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 1.4
    // Assumes matrix is a non-null, square boolean matrix.
    // Checks if the matrix is anti-reflexive (no self-loops).
    public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
        boolean result = true; // default return value
        // ---------------write your code BELOW this line only! ------------------
        for(int i=0;i< matrix.length && result;i++){
            if(matrix[i][i]){
                result = false;
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 1.5
    // Assumes flights is a boolean matrix representing valid flights
    // and tour is an array of integers matching the size of flights.
    // Validates if the given tour is a correct solution for the flight's matrix.
    public static boolean isValidSolution(boolean[][] flights, int[] tour) {
        boolean result = false; // default return value
        // ---------------write your code BELOW this line only! ------------------
        result = isPermutation(tour) && areStepsLegal(flights,tour) && tour[0] == 0;
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 1.6
    // Assumes array is not null
    // Checks if the array is a permutation of numbers from 0 to array.length-1.
    public static boolean isPermutation(int[] array) {
        boolean result = true; // default return value, CHANGED IT TO TRUE.
        // ---------------write your code BELOW this line only! ------------------
        int isOne = 0;
        for(int checkedNumber =0;checkedNumber<array.length ;checkedNumber++){
            isOne = 0;
            for(int j=0;j<array.length;j++){

                if(array[j]==checkedNumber){
                    isOne++;
                }
            }
            if(isOne>1 || isOne == 0)
                result = false;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 1.7
    // Assumes flights is a valid instance matrix and tour only includes valid city indices
    // Checks if there are legal flights between consecutive cities in the tour.
    public static boolean areStepsLegal(boolean[][] flights, int[] tour) {
        boolean result = true;
        // ---------------write your code BELOW this line only! ------------------
        int firstCity=0,secondCity=0;
        for(int i=0;i<tour.length-1 && result;i++){
            firstCity=tour[i];
            secondCity=tour[i+1];
          if(!(flights[firstCity][secondCity] && flights[secondCity][firstCity]) && firstCity!=secondCity){
              result = false;
          }
        }
        if(!(flights[tour[0]][tour[tour.length-1]] && flights[tour[tour.length-1]][tour[0]]) && tour[0] != tour[tour.length-1]){
            result = false;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    ///////////////////////////////////
    /////// Part 1 ends here ////////
    ///////////////////////////////////

    // Assumes n is a non-negative integer.
    // Creates an array of integers from 0 to n-1.
    public static int[] createRange(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        return res;
    }

    // Assumes source and destination arrays are of the same length.
    // Copies the contents of source array into destination array.
    public static void copyArray(int[] source, int[] destination) {
        if (source == null || destination == null || source.length != destination.length) {
            throw new IllegalArgumentException("Source and null must be initialized arrays of the same length.");
        }
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    // Assumes n is a non-negative integer.
    // Computes the factorial of n.
    public static int factorial(int n) {
        if (n<0) {
            throw new IllegalArgumentException("n must be non-negative.");
        }
        int res = 1;
        for(; n > 1; n--) {
            res = res * n;
        }
        return res;
    }

    // task 2.1
    // Assumes n is a positive integer.
    // Generates all permutations of numbers from 0 to n-1 using the Johnson-Trotter algorithm.
    public static int[][] generatePermutations(int n) {
        int[][] result = null; // default return value
        // ---------------write your code BELOW this line only! ------------------
        int[] permutation = createRange(n);
        int[] directions = new int[n];
        for(int e: directions){
            e=-1;
        }
        result =new int[n][n];
        copyArray(permutation,result[0]);
        int i =1;
        while ((directions[i] == -1 && permutation[i]>permutation[i-1])|| (directions[i] ==1 && permutation[i]>permutation[i+1])){
        int mobileElement = findMobileIndex(permutation,directions);
        swap(permutation,directions,mobileElement);
        reverseDirections(permutation,directions,mobileElement);
        copyArray(permutation,result[i]);
        i++;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 2.2
    // Assumes permutation and directions are arrays of the same length.
    // Swaps elements in permutation and directions based on the mobile index.
    public static void swap(int[] permutation, int[] directions, int mobileIndex) {
        // ---------------write your code BELOW this line only! ------------------
           int tempDirections = directions[mobileIndex];
           int tempPermutation = permutation[mobileIndex];
           if(mobileIndex+directions[mobileIndex] < permutation.length && mobileIndex+directions[mobileIndex] >=0){
               System.out.println(mobileIndex+directions[mobileIndex]);
               permutation[mobileIndex] = permutation[mobileIndex+directions[mobileIndex]];
               directions[mobileIndex] = directions[mobileIndex+directions[mobileIndex]];
               directions[mobileIndex+tempDirections] =tempDirections;
               permutation[mobileIndex+tempDirections] = tempPermutation;

           }

        // ---------------write your code ABOVE this line only! ------------------
    }

    // task 2.3
    // Assumes permutation and directions are arrays of the same length.
    // Reverses the directions of elements greater than the given mobile element.
    public static void reverseDirections(int[] permutation, int[] directions, int mobileElement) {
        // ---------------write your code BELOW this line only! ------------------
            for(int i=0;i<permutation.length;i++){
                if(permutation[i]>mobileElement)
                    directions[i]=directions[i] * -1;
            }
        // ---------------write your code ABOVE this line only! ------------------
    }

    // task 2.4
    // Assumes permutation and directions are arrays of the same length.
    // Finds and returns the index of the largest mobile element or -1 if none exist.
    public static int findMobileIndex(int[] permutation, int[] directions) {
        int result = -1; // default return value
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }


    // task 2.5
    // Assumes flights is a valid problem instance.
    // Finds a valid solution to the Big Trip Problem using exhaustive search.
    public static int[] solveBigTripProblemExhaustive(boolean[][] flights) {
        int[] result = null; // default return value
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    ///////////////////////////////////
    /////// Part 2 ends here ////////
    ///////////////////////////////////

    // Assumes flights is a valid instance of the Big Trip Problem.
    // Solves the Big Trip Problem using reduction
    public static int[] solveBigTripProblemReduction(boolean[][] flights) {
        if (!isLegalInstance(flights)) {
            throw new IllegalArgumentException("Flights instance must be valid.");
        }
        boolean[][] encodedFlights = encoder(flights);
        int[] hamiltonianPath = HamiltonianPathSolver.solve(encodedFlights);
        return decoder(hamiltonianPath);
    }

    // task 3.1
    // Assumes flights is a valid matrix.
    // Adds a dummy city to the matrix and returns the new matrix.
    public static boolean[][] encoder(boolean[][] flights) {
        boolean[][] result = null; // default return value
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

    // task 3.2
    // Assumes hamiltonianPath is a valid Hamiltonian path or null.
    // Converts the Hamiltonian path back to the original Big Trip Problem solution.
    public static int[] decoder(int[] hamiltonianPath) {
        int[] result = null; // default return value
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return result;
    }

}
