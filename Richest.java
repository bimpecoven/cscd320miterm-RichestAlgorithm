/* Blake Impecoven - May, 2016 - CSCD320 - Programming Assignment 1
 * 
**/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintStream;

public class Richest {

    public static void main(String[]args) throws FileNotFoundException {
        Min_Heap minHeap = new Min_Heap(10000);//new heap with enough space for our maximum amount of data
        Scanner inputReader = null;

        try {
            File inputFile = new File(args[0]);
            try {
                inputReader = new Scanner(inputFile);
            }//end try
            catch(FileNotFoundException e) {
                throw new FileNotFoundException("Input File not found!");
            }//end catch
        }//end try
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds...\nExiting program.");
        }//end catch

        fillHeap(minHeap, inputReader);
        displayResults(minHeap);
    }//end main

    public static void fillHeap(Min_Heap minHeap, final Scanner inputReader) {
        if(inputReader == null) {
            throw new RuntimeException("File Reader is NULL.");
        }//end if

        int newValue;

        if(minHeap.size == 0) {
            newValue = inputReader.nextInt();
            minHeap.insert(newValue);
        }//end if

        while(inputReader.hasNext()) {
            newValue = inputReader.nextInt();

            if(minHeap.size < minHeap.maximum) {
                minHeap.insert(newValue);
            }//end if
            /* We are checking against the first element in this heap because we know
             * that it is going to be the smallest number in the heap, this will help
             * keep the heap smaller
            **/
            else if(newValue > minHeap.heap[1] && minHeap.size >= minHeap.maximum) {
                minHeap.delete(1);
                minHeap.insert(newValue);
            }//end elseif
        }//end while
    }//end fillHeap

    public static void displayResults(Min_Heap minHeap) {
        int count = minHeap.size;
        PrintStream fileWriter = null;
    
        minHeap.createMaxHeap();
        minHeap.heapSort();
        try {
            fileWriter = new PrintStream("richest.output");
        }//end try
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }//end catch
        while(count >= 1) {
            fileWriter.println(minHeap.heap[count]);
            count--;
        }//end while
        fileWriter.flush();
        fileWriter.close();
    }//end displayResults
   
}//end class