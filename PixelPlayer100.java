import java.awt.*;
import java.util.Random;

public class PixelPlayer100 extends Player {
	class RetFlag{
		boolean flag;
		int retVal;
		int check4th;
	}
	RetFlag retflag = new RetFlag();
	Point[] rowAblePosition = new Point[8];  //둘 수 있는 가로 돌의 포인트
	Point[] colAblePosition = new Point[8];  //둘 수 있는 세로 돌의 포인트
	
	boolean[] rowSlider = new boolean[8]; //가로 배제 슬라이더 배제 false 허용 true   
	boolean[] colSlider = new boolean[8]; //세로 배제 슬라이더 배제 false 허용 true
	
	int[] rowWeight = new int[8]; //가로 한칸마다 가중치 
	int[] colWeight = new int[8]; //세로 한칸마다 가중치
	
	PixelPlayer100(int[][] map) {
		super(map);	
		//처음슬라이더 모두 이동가능하다고 초기화
		//각 칸마다 가중치 0으로 초기화
		for(int i=0; i<8; i++){
			colSlider[i]=true; 
			rowSlider[i]=true;
			rowWeight[i]=0;
			colWeight[i]=0;
		}
		
	}
	//3목 검사 함수 12시방향 부터 1이다.( 시계방향 )
	//TODO : ARRAY Index Out ERROR 예외처리해야함
	public RetFlag check3status(int x,int y,int mynum){
		// 내 3목인 경우 : mynum = 아군 돌
		// 적 3목을 막는 경우 : mynum = enemynum
		retflag.flag = ( map[x][y-1]==mynum && map[x][y-2]==mynum ) ? true : false;
		retflag.retVal = 1;
		retflag.check4th = map[x][y-3];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x+1][y-1]==mynum && map[x+2][y-2]==mynum  ) ? true : false;
		retflag.retVal = 2;
		retflag.check4th = map[x+3][y+3];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x+1][y]==mynum && map[x+2][y]==mynum  ) ? true : false;
		retflag.retVal = 3;
		retflag.check4th = map[x+3][y];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x+1][y+1]==mynum && map[x+2][y+2]==mynum ) ? true : false;
		retflag.retVal = 4;
		retflag.check4th = map[x+3][y+3];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x][y+1]==mynum && map[x][y+2]==mynum ) ? true : false;
		retflag.retVal = 5;
		retflag.check4th = map[x][y+3];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x-1][y+1]==mynum && map[x-2][y+2]==mynum ) ? true : false;
		retflag.retVal = 6;
		retflag.check4th = map[x-3][y+3];
		if(retflag.flag ) return retflag;
		retflag.flag= ( map[x-2][y]==mynum && map[x-1][y]==mynum ) ? true : false;
		retflag.retVal = 7;
		retflag.check4th = map[x-3][y];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x-2][y-2]==mynum && map[x-1][y-1]==mynum ) ? true : false;
		retflag.retVal = 8;
		retflag.check4th = map[x-3][y-3];
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x][y-1]==mynum && map[x][y+1]==mynum ) ? true : false;
		retflag.retVal = 9;
		retflag.check4th = (mynum == 2 ? 1 : 2);
		if(retflag.flag) return retflag;
		retflag.flag= ( map[x+1][y-1]==mynum && map[x-1][y+1]==mynum ) ? true : false;
		retflag.retVal = 10;
		retflag.check4th = (mynum == 2 ? 1 : 2);
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x+1][y]==mynum && map[x-1][y]==mynum ) ? true : false;
		retflag.retVal = 11;
		retflag.check4th = (mynum == 2 ? 1 : 2);
		if(retflag.flag) return retflag;
		retflag.flag = ( map[x-1][y-1]==mynum && map[x+1][y+1]==mynum ) ? true : false;
		retflag.retVal = 12;
		retflag.check4th = (mynum == 2 ? 1 : 2);
		if(retflag.flag) return retflag;
		//세 개가 연달아 되는 곳이 없다
		retflag.flag = false;
		retflag.retVal = 0;
		retflag.check4th = 0;
		return retflag;
	}
	
	public Point nextPosition(Point lastPosition) {  
		//x,y가 현재 슬라이더 위치이다.
		int y = (int)lastPosition.getX(); //가로 포인트
		int x = (int)lastPosition.getY(); //세로 포인트
	
		//선공후공 1이면 선공 2이면 후공
		int myNum = map[(int)currentPosition.getX()][(int)currentPosition.getY()];
		int enemyNum = myNum==1 ? 2 : 1 ; //적군 돌 번호
		Point nextPosition;

		for(int i=0; i<8; i++){
			//가로 둘 수 있는 곳 넣기	
			if( map[i][x] == 0 ){
				rowAblePosition[i] = new Point(i,x);
			}
			//세로 둘 수 있는 곳 넣기
			if( map[y][i] == 0 ){
				colAblePosition[i] = new Point(y,i);
			}
		}
		//아군 3목 만들기
		for(int i=0; i<8; i++ ){
			int Ary = (int)rowAblePosition[i].getX();
			int Arx = (int)rowAblePosition[i].getY();
			 
			int Acy = (int)colAblePosition[i].getX();
			int Acx = (int)colAblePosition[i].getY();
			
			if( check3status(Acx, Acy, myNum).flag ){
				return new Point(Acy, Acx);
			}
			if( check3status(Arx, Ary, myNum).flag ) {
				return new Point(Ary, Arx);
			}
		}
		
		//적 3목 돌로 막기
		for(int i=0; i<8; i++ ){
			int Ary = (int)rowAblePosition[i].getX();
			int Arx = (int)rowAblePosition[i].getY();
			 
			int Acy = (int)colAblePosition[i].getX();
			int Acx = (int)colAblePosition[i].getY();
			
			RetFlag test = check3status(Acx, Acy, enemyNum); 
			if( test.flag && test.check4th == myNum){
				return new Point(Acy, Acx);
			}
			test = check3status(Arx, Ary, enemyNum);
			if( test.flag && test.check4th == myNum) {
				return new Point(Ary, Arx);
			}
		}
		
		
		
		
		return nextPosition;
	}
}