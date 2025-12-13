import java.util.Arrays;
//This is the same solution code displayed in the curriculum slide for Advanced Java.
//Link: https://docs.google.com/presentation/d/1m0Fd_VZfJm_4Lkp8fuQG4jUnIV6YqwfkpDx_G6GA48Q/edit?slide=id.g3ad8bd31bed_0_48#slide=id.g3ad8bd31bed_0_48
class MergeSortEasyClass {
    public static void main(String[] args) {
        int[] arr = {2, 4, 56, 36, 543, 2, 617, 654};
        System.out.println(Arrays.toString(mergeSortEasy(arr)));
    }
    
    public static int[] copyArray(int[] orig, int from, int to) {
        int[] copyArr = new int[to - from];
        int count = 0;
        for (int i = from; i < to; i++) {
            copyArr[count] = orig[i];
            count++;
        }
        return copyArr;
    }
    
    public static int[] mergeSortEasy(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        
        int[] arr1 = copyArray(arr, 0, arr.length / 2);
        int[] arr2 = copyArray(arr, arr.length / 2, arr.length);
        
        arr1 = mergeSortEasy(arr1);
        arr2 = mergeSortEasy(arr2);
        int[] combinedArr = merge(arr1, arr2);
        return combinedArr;
    }
    
    public static int[] merge(int[] arrA, int[] arrB) {
        int[] newArr = new int[arrA.length + arrB.length];
        int a = 0;
        int b = 0;
        int i = 0;
        while (a < arrA.length && b < arrB.length) {
            if (arrA[a] < arrB[b]) {
                newArr[i] = arrA[a];
                i++;    a++;
            }
            else {
                newArr[i] = arrB[b];
                i++;    b++;
            }
        }
        
        //Catch all: For everything remaining in BOTH arrays, we will just add it to the end of our new array. (Since we already added everything in one array, one of these loops is guaranteed to never start, as the ending condition is immediately met!)
        while (a < arrA.length) {
            newArr[i] = arrA[a];
            i++;    a++;
        }
        while (b < arrB.length) {
            newArr[i] = arrB[b];
            i++;    b++;
        }
        return newArr;
    }
}
