package densan.s.game.Scroll;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 表示画面の左上の座標のスクロールをする範囲を指定しその範囲でスクロールする
 * @author tachibana
 *
 */
public class Scroll {
	/**
	 * x軸オフセット double
	 */
	private double offsetX = 0;
	/**
	 * y軸オフセット double
	 */
	private double offsetY = 0;
	
	/**
	 * 表示画面の左上座標が動ける範囲の指定を保持<br>
	 * 
	 * {{(左上のx座標),(左上のy座標),(右下のx座標),(右下のy座標)}}
	 */
	private ArrayList<ArrayList<Double>> map = new ArrayList<ArrayList<Double>>();
	/**
	 * singleton
	 */
	private static Scroll instance = new Scroll();
	/**
	 * コンストラクタ
	 */
	private Scroll(){
	
	}
	
	
	/**
	 * 指定範囲内かどうかを返す
	 * @param double pointX X座標
	 * @param double pointY Y座標
	 * @return boolean
	 */
	public boolean isWithInTheScope(double pointX,double pointY){
		Iterator<ArrayList<Double>> itr = map.iterator();
		while(itr.hasNext()){
			ArrayList<Double> dlist = itr.next(); 
			double x = pointX;
			double y = pointY;
			if(dlist.get(0)<=x&&x<=dlist.get(2)&&dlist.get(1)<=y&&y<=dlist.get(3)){
				return true;
			}
		}
		//エリア外
		return false;
	}
	
	/**
	 * インスタンスへの参照
	 * @return Scroll
	 */
	public static Scroll getInstance(){
		return instance;
	}
	/**
	 * 矩形の二点の座標を指定しその矩形の範囲内でスクロールする<br>
	 * 
	 * @param x1 矩形の左上のx座標
	 * @param y1 矩形の左上のy座標
	 * @param x2 矩形の右下のx座標
	 * @param y2 区政の右下のy座標
	 */
	public void rengeSpesification(double x1,double y1,double x2,double y2){
		ArrayList<Double> position = new  ArrayList<Double>();
		position.add(x1);
		position.add(y1);
		position.add(x2);
		position.add(y2);
		map.add(position);
	}
	
/**
 * ゲッター offsetX<br>
 * 座標の指定に使う場合減算すること
 * @return double offsetX
 */
	public double getOffsetX() {
		return offsetX;
	}
/**
 * セッター
 * @param double offsetX
 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	/**
	 * オフセットに加算
	 * @param addOffsetX
	 * @param addOffsetY 
	 * @return boolean
	 */
	public boolean addOffset(double addOffsetX ,double addoffsetY ){
		
		double x = addOffsetX+offsetX;
		double y = addoffsetY+offsetY;
		if(isWithInTheScope(x,y)){
			offsetX=x;
			offsetY=y;
			return true;
		}
		return false;
	}
	
/**
 * ゲッター offsetY<br>
 * 座標の指定に使う場合減算すること
 * @return　double　offsetY
 */
	public double  getOffsetY() {
		return offsetY;
	}
/**
 * セッター　offsetY
 * 座標の指定に使う場合減算すること
 * @param double offsetY
 */
	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

}
