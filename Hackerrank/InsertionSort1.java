// Hackerrank Algorithm - Insertion Sort 1 Solution
// https://www.hackerrank.com/challenges/insertionsort1/problem

import java.util.*;

class Result {

    public static void insertionSort1(int n, List<Integer> arr) {
        // Write your code here

        int temp;

        for(int i=n-1; i>0; i--){

            temp = arr.get(i);

            if(arr.get(i)<arr.get(i-1)){
                arr.set(i, arr.get(i-1));

                for(int j=0; j<n; j++){
                    System.out.print(arr.get(j) + " ");
                }
                System.out.println("");

                arr.set(i-1, temp);
            }
        }

        for(int j=0; j<arr.size(); j++){
            System.out.print(arr.get(j) + " ");
        }

    }

}
