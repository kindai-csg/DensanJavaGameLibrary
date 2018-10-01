package densan.s.game.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import densan.s.game.drawing.Drawer;
import densan.s.game.drawing.SwingDrawer;
import densan.s.game.fps.FPS;
import densan.s.game.input.KeyInput;
import densan.s.game.input.MouseInput;
import densan.s.game.manager.GameManager;
/**
 * 
 * ゲームの描画をするパネル
 * @author S
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	
	/**
	 * シリアライズ番号
	 */
	private static final long serialVersionUID = -7731885916536633695L;
	/**
	 * 	スレッド。
	 */
	private Thread thread = null;
	/**
	 * 	スレッドのフラグ。
	 */
	boolean running = true;
	/**
	 * FPS
	 */
	private FPS fps;
	/**
	 *  	ダブルバッファリング（db）用のGraphic
	 */
	private Graphics dbg;
	/**
	 * ドロワーのアダプター
	 */
	private Drawer drawer;
	/**
	 * ダブルバッファリング用のImage
	 */
	private Image dbImage = null;
	/**
	 * ゲームの進行管理クラス
	 */
	private GameManager manager;
	
	/**
	 * コンストラクタ
	 * @param width パネルの幅
	 * @param height パネルの高さ
	 * @param fps FPSの値
	 */
	public GamePanel(int width, int height, int fps) {
		
		//		 パネルのサイズを指定。
		setPreferredSize(new Dimension(width, height));
		setSize(width, height);
		//setVisible(true);
		setFocusable(true);
		//managerを呼び出す
		manager = GameManager.getInstance();

		
		//FPSの設定
		this.fps = new FPS(fps);
		
		//	リスナーの登録。
		addKeyListener(KeyInput.getInstance());
		addMouseListener(MouseInput.getInstance());
		addMouseMotionListener(MouseInput.getInstance());
		addMouseWheelListener(MouseInput.getInstance());
		
		//ゲームループの起動。
		if (thread == null || !running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * 	ゲームの本描画
	 */
	private void gameRender() {//OK
		//	 初回の呼び出し時にダブルバッファリング用オブジェクトを作成。
		if (dbImage == null) {
			requestFocusInWindow();
			//	 バッファイメージ。
			dbImage = createImage(getWidth(), getHeight());
			if (dbImage == null) {
				return;
			} else {
				//	 バッファイメージの描画オブジェクト。
				dbg = dbImage.getGraphics();
				drawer = new SwingDrawer(dbg);
			}
		}

		//バッファをクリアする。
		
		dbg.setColor(manager.getBackGroundColor());
		dbg.fillRect(0, 0, getWidth(), getHeight());
		

		manager.draw(drawer);


	}
	/**
	 * 描画されたimageを画面に表示
	 */
	private void paintScreen() {
		try {
			Graphics g = getGraphics(); // グラフィックオブジェクトを取得
			if ((g != null) && (dbImage != null)) {
				g.drawImage(dbImage, 0, 0, null); // バッファイメージを画面に描画
			}
			Toolkit.getDefaultToolkit().sync();
			if (g != null) {
				g.dispose(); // グラフィックオブジェクトを破棄
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 実行するスレッド<br>
	 * 1ループ＝1フレーム
	 */
	@Override
	public void run() {


		requestFocusInWindow();
		while(running){
			
			//	更新。
			manager.update();
			//	バッファへの描画。
			gameRender();
			//キーの更新処理
			KeyInput.getInstance().update();
			//画面への描画
			paintScreen();

			//FPS。
			fps.runFPS();
		}
	}
	/**
	 * 現在のFPSを文字列で取得<br>
	 *  少数点2位まで　デバック用
	 * @return　文字列で表された実質FPS
	 */
	public String getFps() {
		return fps.getFPS();
	}

}
