public class MergesortHeapNode implements Comparable {
    int key;
    int whichSubarray;
    
    public MergesortHeapNode (int key, int whichSubarray) {
        this.key = key;
        this.whichSubarray = whichSubarray;
    }
    
    
    public int getKey() {
        return key;
    }
    
    public int getWhichSubarray() {
        return whichSubarray;
    }
    
    public int compareTo (Object rhs) {
        if (this.key < ((MergesortHeapNode) rhs).key) {
            return -1;
        } else if (this.key == ((MergesortHeapNode) rhs).key) {
            return 0;
        } else {
            return 1;
        }
    }
}
