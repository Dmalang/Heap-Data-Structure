public class MalangHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    // Constructor to initialize the heap
    public MalangHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = new int[maxSize + 1];
        heap[0] = Integer.MIN_VALUE; // Sentinel value for easier calculations
    }

    // Returns the index of the parent node
    private int parent(int pos) {
        return pos / 2;
    }

    // Returns the index of the left child
    private int leftChild(int pos) {
        return 2 * pos;
    }

    // Returns the index of the right child
    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    // Checks if a node is a leaf
    private boolean isLeaf(int pos) {
        return pos > size / 2 && pos <= size;
    }

    // Swaps two elements in the heap
    private void swap(int fpos, int spos) {
        int temp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = temp;
    }

    // Heapify the node at the given position
    private void heapify(int pos) {
        if (!isLeaf(pos)) {
            if (heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]) {
                if (heap[leftChild(pos)] < heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    heapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    heapify(rightChild(pos));
                }
            }
        }
    }

    // Insert an element into the heap
    public void insert(int element) {
        if (size >= maxSize) {
            System.out.println("Heap is full!");
            return;
        }
        heap[++size] = element;
        int current = size;

        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Removes and returns the minimum element (root)
    public int removeMin() {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return Integer.MIN_VALUE;
        }
        int popped = heap[1];
        heap[1] = heap[size--];
        heapify(1);
        return popped;
    }

    // Prints the heap
    public void printHeap() {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print("Parent: " + heap[i] +
                    " Left: " + heap[2 * i] +
                    " Right: " + heap[2 * i + 1] + "\n");
        }
    }

    // Main method to test the heap
    public static void main(String[] args) {
        MalangHeap heap = new MalangHeap(10);
        heap.insert(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(2);

        heap.printHeap();
        System.out.println("Removed Min: " + heap.removeMin());
        heap.printHeap();
    }
}
