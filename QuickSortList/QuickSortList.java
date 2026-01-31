// This algorithm is courtesy of GeeksForGeeks
// Check both sources out if you want to learn more about QuickSort!
// Source 1 (QuickSort algorithm by itself): https://www.geeksforgeeks.org/dsa/quick-sort-algorithm/
// Source 2 (QuickSort with Linked Lists): https://www.geeksforgeeks.org/dsa/quicksort-on-singly-linked-list/

class Node
{
    int data;
    Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }

}

public class QuickSortList {
    //Helper method; gets the last node of a Linked List
    public static Node getLast(Node root) {
        while (root != null && root.next != null) {
            root = root.next;
        }
        return root;
    }

    //Initial method to call to sort the linked list
    public static Node quickSort(Node node) {
        Node first = node;
        Node last = getLast(node);
        quickSortH(first, last);
        return first;
    }

    //Actual recursive algorithm: We will partition the list into two parts based on our pivot,
    //and partition those two parts recursively until the whole array is sorted.
    public static void quickSortH(Node first, Node last) {
        //Base case: Basically if the pointer for first has gone ahead of the pointer for last
        if (first == last || first == null) return;

        //This partition method will pick a certain "pivot" point and arrange all elements less
        //than the pivot to the left of it, and all elements greateer than the pivot to the right of it.
        //The recursive algorithm then takes two new pivots for each subarray, partition again, and repeat.
        Node pivot = partition(first, last);
        quickSortH(first, pivot);
        quickSortH(pivot.next, last);

    }

    //This partition method will pick a certain "pivot" point and arrange all elements less
    //than the pivot to the left of it, and all elements greateer than the pivot to the right of it.
    public static Node partition(Node first, Node last) {
        Node j = first; //This is the iterative pointer that is travelling through the array normally.
        
        Node i = first; // When we quickly move all elements less than the pivot to the left of it,
        // order doesn't matter (the only thing that matters is that the element is less than the pivot).
        // As a result, we hold a pointer to "i" that is essentially the location of where the next "lesser"
        // element should be placed if we find one. The strategy is that for every element we find less than
        // the pivot, we will just move it to the next leftmost possible location to guarantee it will be
        // at a more left position than the pivot itself.
        
        Node pivot = first; //
        
        
        while (j != last.next) {
            if (j.data < pivot.data) {
                swap(i.next, j);
                i = i.next;
            }
            j = j.next;
        }

        // Our entire strategy involved moving all the "lesser" elements to the left of the pivot. This means that
        // once we finish the algorithm, i is located at the next element right after the last "lesser" element has
        // been found in the array. This is naturally the position that the pivot itself must be located, as that
        // guarantees that every single element to the right of the pivot can't be anything but greater than it.
        swap(i, pivot);
        
        return i; //Our recursive method relies on KNOWING the location (i.e. the pointer) to the pivot element,
        // so we can recursively apply this same partitioning algorithm to the sublist left of the pivot and sublist
        // right of the pivot. i is the node that stores the pointer to the pivot, so we will return i.
    }
    
    public static void swap(Node a, Node b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    public static void main(String[] args) {
        //The list of values we will put into our list
        int[] values = {1, 4, 5, 2, 7, 9, 20};

        //Constructing our entire list
        Node root = new Node(values[0]);
        Node temp = root;
        for (int i = 1; i < values.length; i++) {
            temp.next = new Node(values[i]);
            temp = temp.next;
        }

        //Sort our linked list
        quickSort(root);

        //Print our newly sorted linked list
        temp = root;
        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
        System.out.println();
    }
}
