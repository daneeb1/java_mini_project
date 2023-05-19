package Paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas { // Canvas 부모 MyCanvas 자식

	// 검은색 점 안찍히게 하기 위해서 x, y 값을 -로 지정해준다.
	public int x;
	public int y;
//	public int W = 7;
//	public int H = 7;
	
	//Color(자료형) color(변수명) = Color.BLACK (상수 클래스명?)
	public Color color = Color.BLACK;

	// 페인트 메소드 재정의
	@Override
	public void paint(Graphics graphics) {
		System.out.println("paint`````");
		graphics.setColor(color);
		graphics.fillOval(x -5, y -5, 7, 7); // 70, 70 크기의 원 그리기 
	}

	// 업데이트 메소드 재정의
	@Override
	public void update(Graphics graphics) {
		System.out.println("update`````");
		paint(graphics);
	}
	
	// 필드 x, 매개변수 int x
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}

// 