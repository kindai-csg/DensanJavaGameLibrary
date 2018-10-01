package densan.s.game.gui;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * ゲームを表示するフレーム
 * @author S
 *
 */
public class GameFrame extends JFrame {
	
	/**
	 * シリアライズ番号
	 */
	private static final long serialVersionUID = -7693278288885735262L;
	/**
	 *  	フルスクリーンモード用のクラス
	 */
	private GraphicsDevice device;
	/**
	 * 描画されるパネル
	 */
	private GamePanel panel;

	/**
	 * 新しいゲームフレームを作る。フルスクリーンモードは終了をXボタン以外でもできるようにしておかないと消せなくなる
	 * @param width フレームの幅
	 * @param height　フレームの高さ
	 * @param title　バーに表示するタイトル
	 * @param fps - fpsの値
	 * @param isFullScreen - tureならフルスクリーンにする TODO フルスクリーンから返る処理をDEFAULTで実装
	 */
	public GameFrame(int width, int height, String title,int fps, boolean isFullScreen) {

		//	 タイトル。
		setTitle(title);

		setResizable(false);// サイズ変更禁止。
		//	 メインパネルを作ってフレームに追加。
		panel = new GamePanel(width, height,fps);
		Container contentPane = getContentPane();
		contentPane.add(panel);
		//	 フルスクリーンモードの初期化
		if (isFullScreen) {
			initFullScreen();
		}
		pack();
		//閉じた時にプログラムを終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//表示させる
		setVisible(true);
	}

	/**
	 * フルスクリーンモードの初期化
	 *
	 */
	private void initFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		device = ge.getDefaultScreenDevice();

		setUndecorated(true); // タイトルバー・ボーダー非表示

		// 必要ならマウスカーソルを消す
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
				new Point(), "");
		setCursor(cursor);

		if (!device.isFullScreenSupported()) {
			System.out.println("フルスクリーンモードはサポートされていません。");
			System.exit(0);
		}

		// フルスクリーン化！
		device.setFullScreenWindow(this);

		// ディスプレイモードの変更はフルスクリーン化後
		// 変更可能なディスプレイモードしか使えない
		// 640x480,800x600,1024x768の32bitあたりが妥当
		setDisplayMode(800, 600, 32);

	}

	/**
	 * ディスプレイモードを設定<br>
	 * フルスクリーンモードで使う
	 *
	 * @param width - 画面の幅
	 * @param height - 画面の高さ
	 * @param bitDepth - bit値
	 */
	private void setDisplayMode(int width, int height, int bitDepth) {
		if (!device.isDisplayChangeSupported()) {
			System.out.println("ディスプレイモードの変更はサポートされていません。");
			return;
		}

		DisplayMode dm = new DisplayMode(width, height, bitDepth,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		device.setDisplayMode(dm);
	}
	
	/**
	 * 現在のFPSを文字列で取得<br>
	 *  少数点2位まで　デバック用
	 * @return 文字列で表された実質FPS
	 */
	public String getFps() {
		return panel.getFps();
	}

}
