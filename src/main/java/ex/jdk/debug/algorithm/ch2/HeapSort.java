package ex.jdk.debug.algorithm.ch2;

/**
 * 堆排序
 * */
public class HeapSort {
    private static int getLeft(int index){
        return (index * 2) + 1;
    }

    private static int getRight(int index){
        return index * 2;
    }

    private static void exchange(int[] data, int x, int y){
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }

    private static void print(int[] data){
        for(int d : data){
            System.out.print(d + " ");
        }
        System.out.println();
    }

    /** 堆性质维护 */
    public static void heapIfy(int[] data, int index){
        int maxHeapSize = data.length - 1;
        int curIndex = index;
        while(curIndex <= maxHeapSize){
            int left = getLeft(curIndex);
            int right = getRight(curIndex);

            int largest;
            if(left <= maxHeapSize && data[left] > data[curIndex]){
                largest = left;
            } else {
                largest = curIndex;
            }

            if(right <= maxHeapSize && data[right] > data[curIndex]){
                largest = right;
            }

            if(largest == curIndex){
                break;
            }
            exchange(data, largest, curIndex);
            curIndex = largest;
        }
    }

    public static void buildMaxHeap(int[] data){
        for(int i = data.length / 2; i > 0; i--){
            heapIfy(data, i);
        }
    }

    public static void main(String[] args){
        int[] data = {4, 5, 1, 6, 2, 7, 3};
        //int[] data = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        //int[] data = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        print(data);

//        heapIfy(data, 0);
//        print(data);
//
//        heapIfy(data, 1);
//        print(data);
//
//        heapIfy(data, 2);
//        print(data);

        buildMaxHeap(data);
        print(data);
    }
}