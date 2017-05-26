package Algorithm_03;

import java.util.*;

public class QuickAndMergeSortMain {
     
    static int[] s1 = null; 
    static int[] s2 = null;
    static int[] tempArray = null; //additional array for inPlaceMergeSort
     
    static int num = 0;
    static int TRIALS = 10;
    static long elapsedTimeOfMergeSort = 0;
    static long elapsedTimeOfQuickSort = 1;
    static long totalElapsedTimeOfMergeSort = 0;
    static long totalElapsedTimeOfQuickSort = 0;
    static int count_m = 0;
    static int count_q = 1;
    public static void main(String[] args) {
        num = 50;

        Random r = new Random(System.currentTimeMillis());
        long startTime = 0, endTime = 0;
        while( count_m != count_q ){
            s1 = new int[num];
            s2 = new int[num];
            tempArray = new int[num];
            for (int i = 0; i < num; i++) {
                s1[i] = s2[i] = r.nextInt(100);
            }
             
            
           
 
            startTime = System.nanoTime(); 
            inPlaceMergeSort(0, num-1); //NOTE: s1�� ���ؼ� ����
            endTime = System.nanoTime();
            elapsedTimeOfMergeSort = endTime - startTime;
           
             
            startTime = System.nanoTime(); 
            quickSort(0, num-1);                //NOTE: s2�� ���ؼ� ����
            endTime = System.nanoTime();
            elapsedTimeOfQuickSort = endTime - startTime;
         
             
           
            System.out.println("mergesort count : "+elapsedTimeOfMergeSort+" quicksort count : "+elapsedTimeOfQuickSort);
        }
    }   
     
 // �������⵵�� ���̱����� �պ� ����
 	public static void inPlaceMergeSort(int low, int high) {
 		count_m++;
 		int mid;// �߰��� ����
 		// ���̻� �����ʿ䰡 ���� ��� ����ó��
 		if (low < high) {
 			mid = (low + high) / 2; // �߰��� ����
 			inPlaceMergeSort(low, mid); // �����迭 �и�
 			inPlaceMergeSort(mid + 1, high); // �����迭 �и�
 			inPlaceMerge(low, mid, high);// ���� �迭 ����
 		}
 	}

 	public static void inPlaceMerge(int low, int mid, int high) {
 		int i, j, k;

 		i = low;// �����迭�� ���ذ��� �Ǵ� ����
 		j = mid + 1;// �����迭�� ���ذ��� �Ǵ� ����
 		k = low;// ��ü�迭�� ���ذ��� �Ǵ� ����
 		// ���� ���������� �� �����̵Ǵ� ����Ʈ ������ ��ü ũ�⸦ �ѱ������� �� ���� �ݺ�
 		while (i <= mid && j <= high) {
 			if (s1[i] < s1[j]) { // ������ ���� ���
 				tempArray[k] = s1[i]; // �ӽù迭�� ������ �ֱ�
 				i++; // ���� ���(����)�����Ƿ� ��������Ʈ �� ����
 			} else {// ������ ���� ���
 				tempArray[k] = s1[j];// �ӽù迭�� ������ �ֱ�
 				j++;// ���� ���(����)�����Ƿ� ��������Ʈ �� ����
 			}
 			k++;// ��ü�迭�� ������ �Ǵ� ����Ʈ���� ������ �����Ƿ� ���� ����Ʈ�� �ϱ����� ����
 		}
 		// ���� ���� ���� �� �������
 		if (i > mid) {
 			// ���� ������ �ӽù迭�� �� �ִ´�.
 			for (int a = 0; a <= high - j; a++)
 				tempArray[k + a] = s1[j + a];
 		}
 		// ���� ���� ���� �� �������
 		else {
 			// ���� ������ �ӽù迭�� �� �ִ´�
 			for (int a = 0; a <= mid - i; a++)
 				tempArray[k + a] = s1[i + a];
 		}
 		// �ش� �ӽù迭�� �ٽ� ���� �⺻ �迭�� �ٽ� ����
 		for (int a = 0; a <= high; a++) {
 			s1[a] = tempArray[a];
 		}
 	}
    //����Ʈ 
 	public static void quickSort(int low, int high) { //quicksort [Algorithm 2.6]
        count_q++;
 		//int pivotPoint = partition(int low, int high)
 		int pivotpoint; //pivotpoint ����
 		if (high > low) {//low�� �� Ŀ���� ���� ����
 			pivotpoint = partition(low, high);//�⺻�迭�� ���ϴ� �迭�� ����
 			quickSort(low, pivotpoint-1);//�׶� �׶��� pivotitem���� ���� �迭 ����
 			quickSort(pivotpoint+1, high);//�׶� �׶��� pivotitem���� ū �迭 ����
 		}

    }
     
    public static int partition(int low, int high) { //partition [Algorithm 2.7]
    	int i, j, pivotpoint;
    	int pivotitem;
    	pivotitem = s2[low]; //ù��° �ε����� pivotitem�� �ȴ�
    	j = low; //j�� ����: for������ ���� �� pivotitem�� ���� ��ġ ���
    	for(i = low + 1; i <= high; i++)
    		if (s2[i] < pivotitem) { 
    			j++;   //j�� �迭 ������ �� pivotitem���� ���� ���� ���� ��ŭ ������
    			int temp=s2[i]; //s2[i]�� s2[j] ��ġ ��ȯ
    			s2[i]=s2[j];
    			s2[j]=temp;
   
    		}
    	pivotpoint = j; //pivotpoint �� pivotitem���� �۾Ҵ� ���ڱ��� ��ġ �̵�
    	//�� pivotitem �� pivotitem ���� �ڸ� ����
    	int temp=s2[low];
		s2[low]=s2[pivotpoint];
		s2[pivotpoint]=temp;
    	//�ش� ����Ʈ ����
    	return pivotpoint;
    }

    
     
    public static void printArrays(int[] s1, int[] s2) {
        System.out.println("Array s1:");
        for (int i = 0; i < num; i++) {
            System.out.print(s1[i] + " ");
        }
        System.out.println();
        System.out.println("Array s2:");
        for (int i = 0; i < num; i++) {
            System.out.print(s2[i] + " ");
        }
        System.out.println();
        System.out.println();
    }
}