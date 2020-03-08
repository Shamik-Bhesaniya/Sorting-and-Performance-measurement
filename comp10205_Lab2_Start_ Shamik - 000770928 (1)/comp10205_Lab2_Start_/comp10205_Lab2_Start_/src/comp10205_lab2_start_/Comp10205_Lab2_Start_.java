/* Comp10205 - Sorting Assignment
 
 Some of the sort code from courseWare textbook - Copyright, All rights reserved.

 Additional code added by C. Mark Yendt in May 2014 August 2019

 ADD Your Authorship and answers to Questions here :
 I, Shamik Bhesaniya, 000770928 certify that this material is my own work and no other
 person's work is used without due acknowledgement.

ANSWERS

01)
a-Quicksort
b-Selection sort
c-Insertion sort
d-Merge sort
e-Bubble sort

02)
b,e,c,a,d 
order of these normally changes , but not rapidly. because the generated array is random

03)
d,a,c,b,e

04)
a-Quicksort-----------------------O(n^2)
b-Selection sort------------------O(n^2)
c-Insertion sort------------------O(n^2)
d-Merge sort----------------------O(nlogn)
e-Bubble sort---------------------O(n^2)

05)
merge sort has the best performance for basic step.(0.0 ns)
yes. 
for less number of elements it lags to other sorting algorithms.
But for the large number of elements, it passes the other sorting algorithms by having best performance in basic step.
 */
package comp10205_lab2_start_;
import java.util.Arrays;
/**
 *
 * @author mark.yendt
 */
public class Comp10205_Lab2_Start_ {

    static int sortdCompares = 0; // Left in for comparison purposes only ()for merge sort there should be a global variable
    static int sortaCompares = 0;  // Left in for comparison purposes only
    static final boolean SHOW_SOLUTION = true;

    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param The array containing the two elements.
     * @param a The subscript of the first element.
     * @param b The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;

        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * The quick sort______________ sort - manages first call
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static int aSort(int array[]) {

        int y = doASort(array, 0, array.length - 1);//catch the # of comparisons 
        
        return y;
    }

    /**
     * The doASort method uses the __quick sort__________________ algorithm to sort
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end The ending subscript of the list to sort
     */
    private static int doASort(int array[], int start, int end) {
        int pivotPoint;
        sortaCompares++;//counting number of comparisons for asort
        if (start < end) {
            // Get the pivot point.
            pivotPoint = part(array, start, end);
            // Note - only one +/=
            // Sort the first sub list.
            doASort(array, start, pivotPoint - 1);

            // Sort the second sub list.
            doASort(array, pivotPoint + 1, end);
        }
        return sortaCompares;

    }

    /**
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static int part(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript
        //int sortaCompares=0;
        // see http://www.cs.cmu.edu/~fp/courses/15122-s11/lectures/08-qsort.pdf
        // for discussion of middle point 
        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;

        // Swap the middle element with the first element.
        // This moves the pivot value to the start of 
        // the list.
        swap(array, start, mid);

        // Save the pivot value for comparisons.
        pivotValue = array[start];

        // For now, the end of the left sub list is
        // the first element.
        endOfLeftList = start;

        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        for (int scan = start + 1; scan <= end; scan++) {

            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }

        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);

        // Return the subscript of the pivot value.
        return endOfLeftList;

        //return sortaCompares;
    }

    /**
     * An implementation of the _selection sort_____________________ Sort Algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static int bSort(int[] array) {
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan
        int totalsortbCompares = 0;//counter to calculate number of comparisons 
        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            // Assume the first element in the scannable area
            // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];
           int localsortbCompares=0;//local counter to calculate inner loop comparisons 
            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area. 
            for (index = startScan + 1; index < array.length; index++) {
                    localsortbCompares++;
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }
            totalsortbCompares=totalsortbCompares+localsortbCompares;

            // Swap the element with the smallest value 
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
        
        return totalsortbCompares;
    }

    /**
     * An implementation of the _______insertion_______________ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static int cSort(int[] array) {
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array
        int totalsortcCompares = 0;// variable to calculate the number of comparisons (outer loop)
        // The outer loop steps the index variable through 
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0  by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
             int localsortcCompares=0 ;//taking new local variable
           //varaible to count number of comparisons in insertion sort
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];

            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;
         
            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                localsortcCompares++;//incremeting
            if(array[scan - 1]>unsortedValue){
                  
            }
            totalsortcCompares=totalsortcCompares+localsortcCompares;
                array[scan] = array[scan - 1];
                scan--;
              
            }
            

            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
        
        return totalsortcCompares;
    }

    /**
     * completes a ___merge________ sort on an array
     *
     * @param array the unsorted elements - will be sorted on completion
     * @return
     */
    public static int dSort(int array[]) {
        int length = array.length;

        int x = doDSort(array, 0, length - 1);
      
        return x;
    }

    /**
     * private recursive method that splits array until we start at array sizes
     * of 1
     *
     * @param array starting array
     * @param lowerIndex lower bound of array to sort
     * @param higherIndex upper bound of array to sort
     * @return 
     */
    public static int doDSort(int[] array, int lowerIndex, int higherIndex) {
       int y=0;//initialize y to hold counter variable from part1
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            
            doDSort(array, lowerIndex, middle);
            // Below step sorts the right side of the array
           
            doDSort(array, middle + 1, higherIndex);
            // Now put both parts together
             y = part1(array, lowerIndex, middle, higherIndex);//catch the number of comparisons 
             
        }
        return y;
        
       
    }

    /**
     * Puts two smaller sorted arrays into one sorted array
     *
     * @param array provided in two sorted parts (1,4,9,2,3,11)
     * @param lowerIndex the location of the first index
     * @param middle - the middle element
     * @param higherIndex - the upper bound of the sorted elements
     * @return 
     */
    public static int part1(int[] array, int lowerIndex, int middle, int higherIndex) {

        int[] mArray = new int[higherIndex - lowerIndex + 1];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            mArray[i - lowerIndex] = array[i];
        }
        // Part A - from lowerIndex to middle
        // Part B - from middle + 1 to higherIndex
        int partAIndex = lowerIndex;
        int partBIndex = middle + 1;
        int m = lowerIndex;
        while (partAIndex <= middle && partBIndex <= higherIndex) {
            // Place items back into Array in order
            // Select the lowestest of the two elements
            if (mArray[partAIndex - lowerIndex] <= mArray[partBIndex - lowerIndex]) {
                array[m] = mArray[partAIndex - lowerIndex];
                partAIndex++;
            } else {
                array[m] = mArray[partBIndex - lowerIndex];
                partBIndex++;
            }
            m++;
        }
        // Copy the remainder of PartA array (if required)
        while (partAIndex <= middle) {
            array[m] = mArray[partAIndex - lowerIndex];
            m++;
            partAIndex++;
        }
        sortdCompares+=m;//calculating number of comparisons
        return sortdCompares;
    }

    /**
     * Sorting using the ____Bubble_____ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static int eSort(int[] array) {
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare
        int totalsorteCompares = 0;
        // The outer loop positions lastPos at the last element
        // to compare during each pass through the array. Initially
        // lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
         
            // The inner loop steps through the array, comparing
            // each element with its neighbor. All of the elements
            // from index 0 thrugh lastPos are involved in the
            // comparison. If two elements are out of order, they
            // are swapped.
          
            for (index = 0; index <= lastPos - 1; index++) {
                  totalsorteCompares++;
                // Compare an element with its neighbor.

                if (array[index] > array[index + 1]) {
                    // Swap the two elements.

                    swap(array, index, index + 1);
                }
            
            }
        }
        
        return totalsorteCompares;
    }

    /**
     * Print an array to the Console
     *
     * @param A array to be printed
     */
    public static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%5d ", A[i]);
        }
        System.out.println();
    }

     public static void repetitive(int size){
         int[] B;
        int[] A = new int[size];

        // Create random array with elements in the range of 0 to SIZE - 1;
         for (int i = 0; i < size; i++) {
           A[i] = (int) (Math.random() * size);
        }

        System.out.printf("\nComparison for array size of %d\n\n", size);
        
        // calling to all sorting methods with time.
        B = Arrays.copyOf(A, A.length);
        long startTime1 = System.nanoTime();//start time for asort
        int s1 = aSort(B);// calling asort
        long elapsedTime1 = System.nanoTime() - startTime1;//total time spent for asort

        long startTime2 = System.nanoTime();//start time for bsort
        int s2 = bSort(B);// calling bsort
        long elapsedTime2 = System.nanoTime() - startTime2;//total time spent for bsort

        long startTime3 = System.nanoTime();//start time for csort
        int s3 = cSort(A);//calling csort
        long elapsedTime3 = System.nanoTime() - startTime3;//total time spent for csort

        long startTime4 = System.nanoTime();//start time for dsort
        int s4 = dSort(B);//calling dsort
        long elapsedTime4 = System.nanoTime() - startTime4;//total time spent for dsort

        long startTime5 = System.nanoTime();//start time for esort
        int s5 = eSort(B);//calling esort
        long elapsedTime5 = System.nanoTime() - startTime5;//total time spent for esort

        System.out.printf("Number of compares for aSort     = %10d\n", s1);
        System.out.printf("Time required for aSort          = %10d ns\n", elapsedTime1);
        System.out.printf("Basic Step = %6.1f ns\n", (double) elapsedTime1 / s1);

        System.out.printf("Number of compares for bSort     = %10d\n", s2);
        System.out.printf("Time required for bSort          = %10d ns\n", elapsedTime2);
        System.out.printf("Basic Step = %6.1f ns\n", (double) elapsedTime2 / s2);

        System.out.printf("Number of compares for cSort     = %10d\n", s3);
        System.out.printf("Time required for cSort          = %10d ns\n", elapsedTime3);
        System.out.printf("Basic Step = %6.1f ns\n", (double) elapsedTime3 / s3);

        System.out.printf("Number of compares for dSort     = %10d\n", s4);
        System.out.printf("Time required for dSort          = %10d ns\n", elapsedTime4);
        System.out.printf("Basic Step = %6.1f ns\n", (double) elapsedTime4 / s4);

        System.out.printf("Number of compares for eSort     = %10d\n", s5);
        System.out.printf("Time required for eSort          = %10d ns\n", elapsedTime5);
        System.out.printf("Basic Step = %6.1f ns\n", (double) elapsedTime5 / s5);
     }  
   
   
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        int size;//size for the array 
        for(int j=1;j<4;j++){ // for loop that run for 3 cases 
        switch(j){
            case 1:{size=25;
            repetitive(size);// call repetitive method
            break;
            }
            case 2:{size=250;
            repetitive(size);
            break;
            }
            default:size=25000;
            repetitive(size);
            break;
        }
           
        }
    
       
    }
    
}
