package densan.s.game.calc;
import java.awt.geom.Point2D;

import densan.s.game.object.GameRectBase;


/**
 * ゲームで使う便利な計算が出来るクラス<br>
 * Mathクラスと同じような感じで使う
 *
 *@author S
 *
 */
public class Calc {

	/**
	 * オブジェクトとオブジェクトの衝突検出。<br>
	 * 矩形同士で判定を行う
	 * @param obj1　当たり判定を行うオブジェクト1
	 * @param obj2　当たり判定を行うオブジェクト2
	 * @return 矩形同士が衝突していればtrue
	 */
	public static boolean collisionDetection(GameRectBase obj1, GameRectBase obj2) {
		/*// オブジェクト１の矩形を求める。
		Rectangle rectObject1 = obj1.getRect();
		// オブジェクト２の矩形を求める。
		Rectangle rectObject2 = obj2.getRect();

		// 矩形同士が重なっているか調べる。
		// 重なっていたら衝突している(trueを返す)。
		return rectObject1.intersects(rectObject2);
		*/
		//高速化
		return 
			      Math.abs(obj1.getCenterX() - obj2.getCenterX()) 
			      < obj1.getWidth()/2 + obj1.getWidth()/2 //横の判定
			      &&
			      Math.abs(obj1.getCenterY() - obj2.getCenterY()) 
			      < obj1.getHeight()/2 + obj2.getHeight()/2; //縦の判定
			   
	
	}
	/**
	 * オブジェクトとオブジェクトの衝突検出。<br>
	 * 各オブジェクトの幅と高さの内小さい方を直径とした円同士で判定を行う
	 * @param obj1　当たり判定を行うオブジェクト1
	 * @param obj2　当たり判定を行うオブジェクト2
	 * @return 円同士が衝突していればtrue
	 * @since 0.11
	 */
	public static boolean collisionCircleDetection(GameRectBase obj1, GameRectBase obj2) {

		// 矩形同士が重なっているか調べる。
		// 重なっていたら衝突している(trueを返す)。
		//return rectObject1.intersects(rectObject2);
		//円でアタリ判定する
		double dis = getDistance(obj1, obj2);
		double size = (Math.min(obj1.getWidth(), obj1.getHeight()) + Math.min(obj2.getWidth(), obj2.getHeight()))/2;
		return dis < size;
	
	}
	/*
	 * オブジェクトとオブジェクトの衝突検出。<br>
	 *楕円と矩形で判定を行う
	 * @param player 楕円のオブジェクト
	 * @param obj 矩形のオブジェクト
	 * @return
	 *
	public static boolean playerCollisionDetection(AGameRect player,
			AGameRect obj) {
		// プレーヤーの矩形を求める。
		Rectangle rectObject1 = player.getRect();
		// オブジェクトの円を求める。
		int n = obj.getWidth() <= obj.getHeight() ? obj.getWidth() : obj
				.getHeight();
		Ellipse2D.Float rectObject2 = new Ellipse2D.Float(
				(int) obj.getCenterX() - (n / 2), (int) obj.getCenterY()
						- (n / 2), n, n);

		// 矩形同士が重なっているか調べる。
		// 重なっていたら衝突している(trueを返す)。
		return rectObject2.intersects(rectObject1);
	}*/

	/**
	 * ２つのオブジェクトの距離を返す。
	 *
	 * @param obj1　オブジェクト1
	 * @param obj2　オブジェクト2
	 * @return　2点の距離
	 */
	public static double getDistance(GameRectBase obj1, GameRectBase obj2) {
		
		return getDistance(obj1.getCenterPos(), obj2.getCenterPos());
	}

	/**
	 * オブジェクトと点の距離を返す。
	 *
	 * @param obj オブジェクト
	 * @param pos ポイント
	 * @return　2点の距離
	 */
	public static double getDistance(GameRectBase obj, Point2D pos) {
		
		return getDistance(obj.getCenterPos(), pos);
	}
	/**
	 * ２つの点の距離を返す。
	 *
	 * @param pos1　ポイント1
	 * @param pos2　ポイント2
	 * @return　2点の距離
	 */
	public static double getDistance(Point2D pos1, Point2D pos2) {
		// 三平方の定理。
		double Xdiff = Math.abs(pos1.getX() - pos2.getX());
		double Ydiff = Math.abs(pos1.getY() - pos2.getY());
		return Math.sqrt(Math.pow(Xdiff, 2) + Math.pow(Ydiff, 2));
	}

	/**
	 * AからBへの角度を弧度で返す。
	 *
	 * @param from　元のオブジェクト
	 * @param to　対象のオブジェクト
	 * @return　ラジアンで表した角度
	 */
	public static double getRadian(GameRectBase from, GameRectBase to) {
		return getRadian(from.getCenterPos(), to.getCenterPos());
	}
	/**
	 * AからBへの角度を弧度で返す。
	 *
	 * @param from　元のオブジェクト
	 * @param to　対象のオブジェクト
	 * @return　ラジアンで表した角度
	 * @since 0.12
	 */
	public static double getRadian(GameRectBase from, Point2D to) {
		return getRadian(from.getCenterPos(), to);
	}
	/**
	 * AからBへの角度を弧度で返す。
	 *
	 * @param from　元の座標
	 * @param to　対象の座標
	 * @return　ラジアンで表した角度
	 * @since 0.12
	 */
	public static double getRadian(Point2D from, Point2D to) {
		double radian = Math.atan2(to.getY() - from.getY(),
				to.getX() - from.getX());
		return radian;
	}
	

	/**
	 * 2つの角度の差の実質値を返す<br>
	 * 詳しく言うと、最短で到達する方向に移動した場合の移動距離<br>
	 * 例：(30 , 330) → 60
	 * @param r1 1つめの角度
	 * @param r2　2つめの角度
	 * @return 角度の実質的な差をラジアンで表した値
	 */
	public static double radianDiff(double r1, double r2) {
		double ret = Math.abs(r1 -r2);
		ret = ret % (Math.PI*2);
		ret = Math.min(ret, Math.PI*2 - ret);
		return ret;
	}
	

}