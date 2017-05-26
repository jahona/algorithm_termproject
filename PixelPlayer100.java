import java.awt.*;
import java.util.Random;

public class PixelPlayer100 extends Player {
	class RetFlag{
		boolean flag;
		int retVal;
		int check4th;
	}
	RetFlag retflag = new RetFlag();
	Point[] rowAblePosition = new Point[8];  //�� �� �ִ� ���� ���� ����Ʈ
	Point[] colAblePosition = new Point[8];  //�� �� �ִ� ���� ���� ����Ʈ
	
	boolean[] rowSlider = new boolean[8]; //���� ���� �����̴� ���� false ��� true   
	boolean[] colSlider = new boolean[8]; //���� ���� �����̴� ���� false ��� true
	
	int[] rowWeight = new int[8]; //���� ��ĭ���� ����ġ 
	int[] colWeight = new int[8]; //���� ��ĭ���� ����ġ
	
	PixelPlayer100(int[][] map) {
		super(map);	
		//ó�������̴� ��� �̵������ϴٰ� �ʱ�ȭ
		//�� ĭ���� ����ġ 0���� �ʱ�ȭ
		for(int i=0; i<8; i++){
			colSlider[i]=true; 
			rowSlider[i]=true;
			rowWeight[i]=0;
			colWeight[i]=0;
		}
		
	}
	//3�� �˻� �Լ� 12�ù��� ���� 1�̴�.( �ð���� )
	//TODO : ARRAY Index Out ERROR ����ó���ؾ���
	public RetFlag check3status(int x,int y,int mynum){
		// �� 3���� ��� : mynum = �Ʊ� ��
		// �� 3���� ���� ��� : mynum = enemynum
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
		//�� ���� ���޾� �Ǵ� ���� ����
		retflag.flag = false;
		retflag.retVal = 0;
		retflag.check4th = 0;
		return retflag;
	}
	
	public Point nextPosition(Point lastPosition) {  
		//x,y�� ���� �����̴� ��ġ�̴�.
		int y = (int)lastPosition.getX(); //���� ����Ʈ
		int x = (int)lastPosition.getY(); //���� ����Ʈ
	
		//�����İ� 1�̸� ���� 2�̸� �İ�
		int myNum = map[(int)currentPosition.getX()][(int)currentPosition.getY()];
		int enemyNum = myNum==1 ? 2 : 1 ; //���� �� ��ȣ
		Point nextPosition;

		for(int i=0; i<8; i++){
			//���� �� �� �ִ� �� �ֱ�	
			if( map[i][x] == 0 ){
				rowAblePosition[i] = new Point(i,x);
			}
			//���� �� �� �ִ� �� �ֱ�
			if( map[y][i] == 0 ){
				colAblePosition[i] = new Point(y,i);
			}
		}
		//�Ʊ� 3�� �����
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
		
		//�� 3�� ���� ����
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