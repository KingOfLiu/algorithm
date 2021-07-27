package codeforces.problemset.segment_tree;

public class SegmentTemplate {
    static class SegmentTree {
        private int[] tree;
        private int[] data;

        SegmentTree(int[] arr){
            data = new int[arr.length];
            tree = new int[arr.length * 4];
            for(int i = 0; i < arr.length; i++){
                data[i] = arr[i];
            }

            buildSegmentTree(0, 0, data.length - 1);
        }

        int getSize(){
            return data.length;
        }

        int get(int index){
            return data[index];
        }

        private int leftChild(int index){
            return 2 * index + 1;
        }

        private int rightChild(int index){
            return 2 * index + 2;
        }

        /**
         * 构建线段树
         * */
        private void buildSegmentTree(int treeIndex, int l, int r){
            if(l == r){
                tree[treeIndex] = data[l];
                return ;
            }

            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2;

            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);

            tree[treeIndex] = Math.max(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        int query(int queryLeft, int queryRight){
            return query(0, 0, data.length - 1, queryLeft, queryRight);
        }

        /**
         * 线段树的区间查询
         * */
        private int query(int treeIndex, int l, int r, int queryLeft, int queryRight){
            if(l == queryLeft && r == queryRight){
                return tree[treeIndex];
            }

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if(queryLeft > mid){
                return query(rightTreeIndex, mid + 1, r, queryLeft, queryRight);
            }

            if(queryRight <= mid){
                return query(leftTreeIndex, l, mid, queryLeft, queryRight);
            }

            int leftResult = query(leftTreeIndex, l, mid, queryLeft, mid);
            int rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryRight);

            return Math.max(leftResult, rightResult);
        }

        void update(int index, int e){
            data[index] = e;
            updateTree(0, 0, data.length - 1, index, e);
        }

        private void updateTree(int treeIndex, int l, int r, int index, int e){
            if(l == r){
                tree[treeIndex] = e;
                return ;
            }

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if(index > mid){
                updateTree(rightTreeIndex, mid + 1, r, index, e);
            } else {
                updateTree(leftTreeIndex, l, mid, index, e);
            }
            tree[treeIndex] = Math.max(tree[leftTreeIndex], tree[rightTreeIndex]);
        }
    }

    public static void main(String[] args){
        int[] arr = {34,65,8,10,21,86,79,30};
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.update(5, 100);
        int res = segmentTree.query(2, 5);
        System.out.println(res);
    }
}
