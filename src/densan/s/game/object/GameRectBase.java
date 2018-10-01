package densan.s.game.object;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
/**
 * ゲーム用の矩形クラス<br>
 * x,y座標と幅と高さを持つ
 * @author S 
 * @author tachibana
 * @since 0.11
 */
public class GameRectBase {
	/**
	 * x座標
	 */
	private double x;
	/**
	 * y座標
	 */
	private double y;
	/**
	 * 幅
	 */
	private int width;
	/**
	 * 高さ
	 */
	private int height;
	/**
	 * offsetX
	 */
	private static int offsetX = 0;
	
	private static int offsetY = 0;
	/**
	 * 座標と幅、高さを指定して生成
	 * @param x x座標
	 * @param y　y座標
	 * @param width　幅
	 * @param height　高さ
	 */
	public GameRectBase(double x, double y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * オブジェクトをRectangleとして返す。<br>
	 * 生成されるRectangleオブジェクトは独立しており、内部をいじってもこのオブジェクトに影響しない
	 * 
	 * @return　このオブジェクトと同じ値のRectangleクラス
	 */
	public Rectangle getRect() {
		return new Rectangle(getPos(), getSize());
	}

	/**
	 * オブジェクトの位置をPointで返す。<br>
	 * 生成されるPointオブジェクトは独立しており、内部をいじってもこのオブジェクトに影響しない<br>
	 * 座標はオブジェクトの左上
	 * 
	 * @return　このオブジェクトと同じ値のPointクラス
	 */
	public Point getPos() {
		return new Point((int) getX(), (int) getY());
	}

	/**
	 * オブジェクトの左上のX座標を返す。
	 * 
	 * @return　左上のX座標
	 */
	public double getX() {
		return x;
	}

	/**
	 * オブジェクトの左上のY座標を返す。
	 * 
	 * @return　左上のY座標
	 */
	public double getY() {
		return y;
	}

	/**
	 * オブジェクトの中心の位置をPointで返す。
	 * 
	 * @return　オブジェクトの中心と同じ値のPointクラス
	 */
	public Point getCenterPos() {
		return new Point((int) getCenterX(), (int) getCenterY());
	}

	/**
	 * オブジェクトの中心のX座標を返す。
	 * 
	 * @return　中心のX座標
	 */
	public double getCenterX() {
		return getX() + (getWidth() / 2);
	}

	/**
	 * オブジェクトの中心のY座標を返す。
	 * 
	 * @return　中心のY座標
	 */
	public double getCenterY() {
		return getY() + (getHeight() / 2);
	}
	/**
	 * オブジェクトの右下のX座標を返す。
	 * 
	 * @return　右下のX座標
	 */
	public double getMaxX() {
		return getX() + getWidth();
	}

	/**
	 * オブジェクトの右下のY座標を返す。
	 * 
	 * @return　右下のY座標
	 */
	public double getMaxY() {
		return getY() + getHeight();
	}
	/**
	 * オブジェクトのサイズをDimensionで返す。
	 * 
	 * @return　オブジェクトと同じ値のDimensionクラス
	 */
	public Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	/**
	 * オブジェクトの幅を返す。
	 * 
	 * @return　オブジェクトの幅
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * オブジェクトの高さを返す。
	 * 
	 * @return　オブジェクトの高さ
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * オブジェクトの情報をx,y,width,heightでセットする。
	 * 
	 * @param x　x座標
	 * @param y　y座標
	 * @param width 幅
	 * @param height　高さ
	 */
	public void setRect(double x, double y, int width, int height) {
		setPos(x, y);
		setSize(width, height);
	}

	/**
	 * オブジェクトの情報をRectangle2Dでセットする。<br>
	 * (補足)RectangleクラスはRectangle2Dクラスを継承している。
	 * 
	 * @param rect　設定する値が入れられたRectangle2Dクラス
	 */
	public void setRect(Rectangle2D rect) {
		setRect(rect.getX(), rect.getY(), (int) rect.getWidth(),
				(int) rect.getHeight());
	}

	/**
	 * オブジェクトの左上の位置を(x,y)にセットする。
	 * 
	 * @param x　座標
	 * @param y　y座標
	 */
	public void setPos(double x, double y) {
		setX(x);
		setY(y);
	}

	/**
	 * オブジェクトの左上の位置をPointにセットする。
	 * 
	 * @param point　設定する値がセットされたPointクラス
	 */
	public void setPos(Point point) {
		setPos(point.getX(), point.getY());
	}

	/**
	 * オブジェクトの左上のX座標をxにセットする。
	 * 
	 * @param x　設定するX座標
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * オブジェクトの左上のY座標をyにセットする。
	 * 
	 * @param y　設定するY座標
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * オブジェクトの中心の位置を(x,y)にセットする。
	 * 
	 * @param x　設定するX座標
	 * @param y　設定するY座標
	 */
	public void setCenterPos(double x, double y) {
		setCenterX(x);
		setCenterY(y);
	}

	/**
	 * オブジェクトの中心の位置をPointにセットする。
	 * 
	 * @param point　設定する値がセットされたPointクラス
	 */
	public void setCenterPos(Point point) {
		setCenterPos(point.x, point.y);
	}

	/**
	 * オブジェクトの中心のX座標をxにセットする。
	 * 
	 * @param x　設定するX座標
	 */
	public void setCenterX(double x) {
		setX(x - (getWidth() / 2));
	}

	/**
	 * オブジェクトの中心のY座標をyにセットする。
	 * 
	 * @param y　設定するY座標
	 */
	public void setCenterY(double y) {
		setY(y - (getHeight() / 2));
	}
	/**
	 * オブジェクトの右下の位置を(x,y)にセットする。
	 * 
	 * @param x　設定するX座標
	 * @param y　設定するY座標
	 */
	public void setMaxPos(double x, double y) {
		setMaxX(x);
		setMaxY(y);
	}
	/**
	 * オブジェクトの右下の位置をPointにセットする。
	 * 
	 * @param point　設定する値がセットされたPointクラス
	 */
	public void setMaxPos(Point point) {
		setMaxPos(point.x, point.y);
	}

	/**
	 * オブジェクトの右下のX座標をxにセットする。
	 * 
	 * @param x　設定するX座標
	 */
	public void setMaxX(double x) {
		setX(x - getWidth());
	}

	/**
	 * オブジェクトの右下のY座標をyにセットする。
	 * 
	 * @param y　設定するY座標
	 */
	public void setMaxY(double y) {
		setY(y - getHeight());
	}
	/**
	 * オブジェクトのサイズを(width,height)にセットする。
	 * 
	 * @param width　設定する幅
	 * @param height　設定する高さ
	 */
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * オブジェクトのサイズをDimension2Dでセットする。<br>
	 * (補足)DimensionクラスはDimension2Dを継承している
	 * 
	 * @param size　設定する高さが入れられたDimension2Dクラス
	 */
	public void setSize(Dimension2D size) {
		setSize((int) size.getWidth(), (int) size.getHeight());
	}
	

	/**
	 * オブジェクトの座標に(x,y)を足す。
	 * 
	 * @param addX　X座標に足す値
	 * @param addY　Y座標に足す値
	 */
	public void addPos(double addX, double addY) {
		addX(addX);
		addY(addY);
	}

	/**
	 * オブジェクトのX座標にxを足す。
	 * 
	 * @param addX　X座標に足す値
	 */
	public void addX(double addX) {
		setX(getX() + addX);
	}

	/**
	 * オブジェクトのY座標にyを足す。
	 * 
	 * @param addY Y座標に足す値
	 */
	public void addY(double addY) {
		setY(getY() + addY);
	}

	/**
	 * オブジェクトの幅をwidthにセットする。
	 * 
	 * @param width 設定する幅
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * オブジェクトの高さをheightにセットする。
	 * 
	 * @param height　設定する高さ
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	public static int getOffsetX() {
		return offsetX;
	}

	public static void setOffsetX(int offsetX) {
		GameRectBase.offsetX = offsetX;
	}

	public static int getOffsetY() {
		return offsetY;
	}

	public static void setOffsetY(int offsetY) {
		GameRectBase.offsetY = offsetY;
	}
}
