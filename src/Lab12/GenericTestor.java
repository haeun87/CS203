package Lab12;

import java.util.Arrays;

public class GenericTestor<T> {

    /**
     * This function returns max and min value of the given array
     * @return Pair<A,T> minMax
     */
    public static <T extends Comparable<T>> Pair<T,T> findMinAndMax(T[] arr){
        T min = arr[0];
        T max = arr[0];
        
        for(T t: arr) {
            if(max.compareTo(t) < 0) {
                max = t;
            }
            else if(min.compareTo(t) > 0) {
                min = t;
            }
        }
        return new Pair<T,T>(min, max);
    }
    
    /**
     * This function returns average and sum for the given array
     * @return Pair<A,T> avgSum or null for none enumerable types
     */
    @SuppressWarnings("unchecked")
    public static <A, T extends Number> Pair<A,T> findAvgSum(T[] arr){
        if (arr[0] instanceof Integer) {
            int sum = 0;
            for(T item :arr) {
                sum += item.intValue();
            }
            double avg = sum/arr.length;
            return (Pair<A, T>) new Pair<>(avg, sum); 
        }
        else if(arr[0] instanceof Double ) {
            double sum = 0;
            for(T item :arr) { 
                sum += item.doubleValue();
            }
            double avg = sum/arr.length;
            return (Pair<A, T>) new Pair<>(avg, sum); 
        }
        else {
            return null;
        }
    }
    
    public static void main(String[] args) {
        // Arbitrary arrays with different type
        Integer[] intArr = {3,5,2,6,111,-5,3};
        Double[] doubleArr = {3.5,5.2,2.0,69.33,1.11,-5.664,3.3242};
        
        System.out.printf("Given Array: %s, Type: %s\n",Arrays.toString(intArr), intArr.getClass() );
        Pair<Integer, Integer> minMaxInt = findMinAndMax(intArr);
        System.out.printf("Min value: %d, Max value: %d\n", minMaxInt.getVal1(),minMaxInt.getVal2() );
        
        Pair<Double, Integer> avgSumInt = findAvgSum(intArr);
        System.out.printf("Average: %f, Sum: %d\n", avgSumInt.getVal1(), avgSumInt.getVal2());  
        System.out.println();
        
        System.out.printf("Given Array: %s, Type: %s\n",Arrays.toString(doubleArr), doubleArr.getClass() );
        Pair<Double, Double> minMaxIDouble = findMinAndMax(doubleArr);
        System.out.printf("Min value: %f, Max value: %f\n", minMaxIDouble.getVal1(),minMaxIDouble.getVal2() );
        
        Pair<Double, Double> avgSumDouble = findAvgSum(doubleArr);
        System.out.printf("Average: %f, Sum: %f\n", avgSumDouble.getVal1(), avgSumDouble.getVal2());  
        System.out.println();
    }
}
