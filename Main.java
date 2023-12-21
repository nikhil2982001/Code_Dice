import java.util.*;
class Main{
    //total number of combination we get from diceA+ diceB
    private static void totalCombination(int A[],int B[]){
        System.out.println("There are 6 faces on eace dice");
        System.out.println();
        int total=A.length * B.length;
        System.out.println("Total combination of two dices are"+" "+total);
    }

    // Display the possible combination
    private static void displayCombination(int A[],int B[]){
        int[][] combination = new int[A.length][B.length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                combination[i][j]=A[i]+B[j];
            }
        }
        System.out.println();
        System.out.println("Displaying each combination:");
           for (int i= 0; i<A.length; i++) {
            for (int j= 0; j<B.length; j++) {
                System.out.print(combination[i][j] + "\t");
            }
              System.out.println();
        }
    }

    //Probability of all possible sums among the number of combinations
    private static void probability(int A[], int B[]){
         int total=A.length* B.length;
         Map<Integer,Integer> map= new HashMap<>();
          for (int diceA :A) {
            for (int diceB :B) {
                int sum = diceA + diceB;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        System.out.println("probability of each sum frequency");
         for (int sum : map.keySet()) {
            double probability = (double) map.get(sum) / total;
            System.out.println("P(Sum = " + sum + ") = " + probability);
        }
    }

    // to find out the correct spots on each face of dice and display it
    private static void findSpot(int A[], int B[]){
        int[] newDieA = undoomDice(A, B);
        int[] newDieB = B.clone(); // Die B doesn't need changes

        System.out.println("New Die A:");
        for (int spot : newDieA) {
            System.out.print(spot + " ");
        }
        System.out.println("\nNew Die B (remains unchanged):");
        for (int spot : newDieB) {
            System.out.print(spot + " ");
        }
    }

     //function to find out the correct spot according to maintain the old probability
         private static int[] undoomDice(int[] A, int[] B) {
        int[] newDieA = new int[A.length];

        // Count occurrences of spots in original Die A
        Map<Integer, Integer> spotCounts = new HashMap<>();
        for (int point : A) {
            spotCounts.put(point, spotCounts.getOrDefault(point, 0) + 1);
        }

        // Reassign spots in New Die A based on original probabilities
        for (int i = 0; i < A.length; i++) {
            int currentSpot = A[i];
            int count = spotCounts.get(currentSpot);

            if (count <= 4) {
                newDieA[i] = currentSpot; 
            } else {
                // Find a spot with count <= 4 and update count
                for (int j = 1; j <= 6; j++) {
                    if (!spotCounts.containsKey(j) || spotCounts.get(j) < 4) {
                        newDieA[i] = j;
                        spotCounts.put(j, spotCounts.getOrDefault(j, 0) + 1);
                        break;
                    }
                }
            }
        }

        return newDieA;
    }

    //Main program
    public static void main(String []args){
     int A[]={1,2,3,4,5,6};
     int B[]={1,2,3,4,5,6};
     totalCombination(A,B);
    displayCombination(A,B);
    probability(A,B);
    findSpot(A,B);
    }
}