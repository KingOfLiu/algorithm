package ex.jdk.debug.algorithm.ch2;

import edu.princeton.cs.algorithms.Point2D;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

public class InsertionSort {
	public static void insertionSort(Comparable[] a) {
		int length = a.length;
		for(int i = 1; i < length;i++) {
			for(int j = i; j > 0 && SortUtils.less(a[j],a[j - 1]);j--) {
				SortUtils.exch(a, j, j - 1);
			}
			System.out.println();
		}
	}
	
	static void insertionSortWhile(Comparable[] a) {
		int i = 1;
		int length = a.length;
		while(i < length) {
			int j = i;
			// 当j>0（表示数组索引不为负数），
			// 前一个元素大于后一个元素时，数组元素互换位置，否则进行下一趟循环
			while(j > 0 && SortUtils.less(a[j], a[j - 1])) {
				SortUtils.exch(a, j, j - 1);
				j--;
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
//		String[] a = {"S","O","R","T","E","X","A","M","P","L","E"};
//		//SortUtils.show(a);
//		//insertionSort(a);
//		insertionSortWhile(a);
//		SortUtils.show(a);
		
		int N = Integer.parseInt("10");
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenRadius(.005);
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(50);
            int y = StdRandom.uniform(50);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }
        double min = Double.POSITIVE_INFINITY;
        int p1 = 0, p2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dis = points[i].distanceTo(points[j]);
                if (dis < min) {
                    min = dis;
                    p1 = i;
                    p2 = j;
                }
            }
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(points[p1].x(), points[p1].y(), points[p2].x(), points[p2].y());
	}
}
