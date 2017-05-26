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
            inPlaceMergeSort(0, num-1); //NOTE: s1에 대해서 정렬
            endTime = System.nanoTime();
            elapsedTimeOfMergeSort = endTime - startTime;
           
             
            startTime = System.nanoTime(); 
            quickSort(0, num-1);                //NOTE: s2에 대해서 정렬
            endTime = System.nanoTime();
            elapsedTimeOfQuickSort = endTime - startTime;
         
             
           
            System.out.println("mergesort count : "+elapsedTimeOfMergeSort+" quicksort count : "+elapsedTimeOfQuickSort);
        }
    }   
     
 // 공간복잡도를 줄이기위한 합병 정렬
 	public static void inPlaceMergeSort(int low, int high) {
 		count_m++;
 		int mid;// 중간값 설정
 		// 더이상 나눌필요가 없는 경우 예외처리
 		if (low < high) {
 			mid = (low + high) / 2; // 중간값 설정
 			inPlaceMergeSort(low, mid); // 좌측배열 분리
 			inPlaceMergeSort(mid + 1, high); // 우측배열 분리
 			inPlaceMerge(low, mid, high);// 나눈 배열 병합
 		}
 	}

 	public static void inPlaceMerge(int low, int mid, int high) {
 		int i, j, k;

 		i = low;// 좌측배열의 기준값이 되는 변수
 		j = mid + 1;// 우측배열의 기준값이 되는 변수
 		k = low;// 전체배열의 기준값이 되는 변수
 		// 전과 마찬가지로 각 기준이되는 포인트 값들이 전체 크기를 넘기지않을 때 까지 반복
 		while (i <= mid && j <= high) {
 			if (s1[i] < s1[j]) { // 좌측이 작은 경우
 				tempArray[k] = s1[i]; // 임시배열에 좌측값 넣기
 				i++; // 값을 사용(대입)했으므로 좌측포인트 값 증가
 			} else {// 우측이 작은 경우
 				tempArray[k] = s1[j];// 임시배열에 우측값 넣기
 				j++;// 값을 사용(대입)했으므로 우측포인트 값 증가
 			}
 			k++;// 전체배열중 기준이 되는 포인트까지 정렬을 했으므로 다음 포인트를 하기위해 증가
 		}
 		// 먼저 좌측 값이 다 끝난경우
 		if (i > mid) {
 			// 남은 값들은 임시배열에 다 넣는다.
 			for (int a = 0; a <= high - j; a++)
 				tempArray[k + a] = s1[j + a];
 		}
 		// 먼저 우측 값이 다 끝난경우
 		else {
 			// 남은 값들은 임시배열에 다 넣는다
 			for (int a = 0; a <= mid - i; a++)
 				tempArray[k + a] = s1[i + a];
 		}
 		// 해당 임시배열을 다시 원래 기본 배열에 다시 대입
 		for (int a = 0; a <= high; a++) {
 			s1[a] = tempArray[a];
 		}
 	}
    //퀵소트 
 	public static void quickSort(int low, int high) { //quicksort [Algorithm 2.6]
        count_q++;
 		//int pivotPoint = partition(int low, int high)
 		int pivotpoint; //pivotpoint 선언
 		if (high > low) {//low가 더 커지면 정렬 종료
 			pivotpoint = partition(low, high);//기본배열을 원하는 배열로 변경
 			quickSort(low, pivotpoint-1);//그때 그때의 pivotitem보다 작은 배열 정렬
 			quickSort(pivotpoint+1, high);//그때 그때의 pivotitem보다 큰 배열 정렬
 		}

    }
     
    public static int partition(int low, int high) { //partition [Algorithm 2.7]
    	int i, j, pivotpoint;
    	int pivotitem;
    	pivotitem = s2[low]; //첫번째 인덱스가 pivotitem이 된다
    	j = low; //j의 역할: for루프가 끝난 후 pivotitem이 있을 위치 기억
    	for(i = low + 1; i <= high; i++)
    		if (s2[i] < pivotitem) { 
    			j++;   //j는 배열 아이템 중 pivotitem보다 작은 것의 개수 만큼 증가함
    			int temp=s2[i]; //s2[i]와 s2[j] 위치 교환
    			s2[i]=s2[j];
    			s2[j]=temp;
   
    		}
    	pivotpoint = j; //pivotpoint 전 pivotitem보다 작았던 숫자까지 위치 이동
    	//전 pivotitem 현 pivotitem 끼리 자리 변경
    	int temp=s2[low];
		s2[low]=s2[pivotpoint];
		s2[pivotpoint]=temp;
    	//해당 포인트 리턴
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