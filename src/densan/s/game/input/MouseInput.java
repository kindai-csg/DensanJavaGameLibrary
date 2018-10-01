package densan.s.game.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;

/**
 * マウス入力用クラス
 * @author S
 *
 */
public class MouseInput implements MouseInputListener, MouseWheelListener{
	/**
	 * マウスの座標
	 */
	private static int mouseX = -1, mouseY = -1;
	/**
	 * クリックされた座標
	 */
	private static int clickX = -1, clickY = -1;
	/**
	 * 離された座標
	 */
	private static int releaseX = -1, releaseY = -1;
	/**
	 * マウスがフレーム内にあるかどうか
	 */
	private static boolean isInFrame = false;

	/**
	 * マウスが左クリックされているかどうか
	 */
	private static boolean isLeftClicking = false;
	/**
	 * マウスが左クリックされているかどうか
	 */
	private static boolean isRightClicking = false;
	
	/**
	 * ホイールの回転速度
	 */
	private static double wheelRotation = 0.0;

	/**
	 * Singleton用インスタンス
	 */
	private static final MouseInput instance = new MouseInput();
	/**
	 * Instanceを返す<br>
	 * GUIに登録する用なのでユーザーはstaticメソッドを使う
	 * @return　このクラス唯一のインスタンス
	 */
	public static MouseInput getInstance() {
		return instance;
	}
	/**
	 * マウスのX座標を返す<br>
	 * マウスがフレーム外なら最後の座標
	 * @return　現在のマウスのX座標
	 */
	public static int getMouseX() {
		return mouseX;
	}
	/**
	 * マウスのY座標を返す<br>
	 * マウスがフレーム外なら最後の座標
	 * @return　現在のマウスのY座標
	 */
	public static int getMouseY() {
		return mouseY;
	}
	/**
	 * マウスの座標をPointクラスで返す<br>
	 * マウスがフレーム外なら最後にフレーム内居た時の座標
	 * @return 現在のマウスの座標
	 */
	public static Point getMousePoint() {
		return new Point(getMouseX(), getMouseY());
	}
	/**
	 * 最後にクリックされたX座標を返す<br>
	 * まだClickされていなければ-1
	 * @return　最後にクリックされたX座標
	 */
	public static int getClickX() {
		return clickX;
	}
	/**
	 * 最後にクリックされたY座標を返す<br>
	 * まだClickされていなければ-1
	 * @return　最後にクリックされたY座標
	 */
	public static int getClickY() {
		return clickY;
	}
	/**
	 * 最後にクリックされた座標をPointクラスで返す<br>
	 * まだClickされていなければ-1
	 * @return　最後にクリックされた座標
	 */
	public static Point getClickPoint() {
		return new Point(getClickX(), getClickY());
	}
	/**
	 * 最後にクリックが解除されたX座標を返す<br>
	 * まだ解除されていなければ-1
	 * @return　最後にクリックが離されたX座標
	 */
	public static int getReleaseX() {
		return releaseX;
	}
	/**
	 * 最後にクリックが解除されたY座標を返す<br>
	 * まだ解除されていなければ-1
	 * @return　最後にクリックが離されたY座標
	 */
	public static int getReleaseY() {
		return releaseY;
	}
	/**
	 * 最後にクリックが解除された座標をPointクラスで返す<br>
	 * まだ解除されていなければ-1
	 * @return　最後にクリックが離された座標
	 */
	public static Point getReleasePoint() {
		return new Point(getReleaseX(), getReleaseY());
	}
	/**
	 * マウスがフレーム内にあるかを返す
	 * @return フレーム内ならtrue
	 */
	public static boolean isInFrame() {
		return isInFrame;
	}
	/**
	 * マウスがクリックされているかを返す<br>
	 * 左右どちらかでもクリックされていればtrueを返す
	 * @return　くりっくされているならtrue
	 */
	public static boolean isClicking() {
		return isLeftClicking || isRightClicking;
	}
	/**
	 * マウスが左クリックされているかを返す<br>
	 * 
	 * @return　くりっくされているならtrue
	 * @since 0.10
	 */
	public static boolean isLeftClicking() {
		return isLeftClicking;
	}
	/**
	 * マウスが右クリックされているかを返す
	 * @return　くりっくされているならtrue
	 * @since 0.10
	 */
	public static boolean isRightClicking() {
		return isRightClicking;
	}
	/**
	 * ホイールの回転数を返す　<br>
	 * 起動してからの合計回転数
	 * @return　ホイールの軌道からの合計回転数
	 */
	public static double getWheelRotation() {
		return wheelRotation;
	}



	//イベント処理
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("CLICK:" + e.getX() + "," + e.getY());
		

	}
	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("PRESS:" + e.getX() + "," + e.getY());
		if (e.getButton() == MouseEvent.BUTTON1) {
			isLeftClicking = true;
		}else if (e.getButton() == MouseEvent.BUTTON3) {
			isRightClicking = true;
		}
		clickX = e.getX();
		clickY = e.getY();

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("RELEASE:" + e.getX() + "," + e.getY());
		if (e.getButton() == MouseEvent.BUTTON1) {
			isLeftClicking = false;
		}else if (e.getButton() == MouseEvent.BUTTON3) {
			isRightClicking = false;
		}
		releaseX = e.getX();
		releaseY = e.getY();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("ENTER:" + e.getX() + "," + e.getY());
		isInFrame = true;

	}
	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("EXIT:" + e.getX() + "," + e.getY());
		isInFrame = false;

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		//System.out.println("D:" + e.getX() + "," + e.getY());
		//マウスの座標を更新
		mouseX = e.getX();
		mouseY = e.getY();

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("M:" + e.getX() + "," + e.getY());
		//マウスの座標を更新
		mouseX = e.getX();
		mouseY = e.getY();

	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		//ホイールに値を足す
		wheelRotation += e.getWheelRotation();
	}



}
