
public class test {
	
	int test2(){
		System.out.println("test2 함수 실행");
		return 0;
	}
	
	int test3(int i){
		int a = i;
		System.out.println("test2의 반환 값 : "+a);
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t1 = new test();
		
		t1.test3(t1.test2());
	}

}
