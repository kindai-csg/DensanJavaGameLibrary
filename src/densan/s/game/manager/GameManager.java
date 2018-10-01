package densan.s.game.manager;

import java.awt.Color;

import densan.s.game.drawing.Drawer;
import densan.s.game.fps.FPS;
import densan.s.game.gui.GameFrame;
/**
 * 主にユーザーが操作するクラス<br>
 * フレームの作成やゲームの管理をする
 * @author S
 *
 */
public class GameManager {
	/**
	 * Singleton用インスタンス
	 */
	private static GameManager instance;

	/**
	 * ゲームフレーム
	 */
	private GameFrame frame;
	/**
	 * 更新・描画するクラス
	 */
	private Updatable updatable;
	/**
	 * 次に設定される更新用クラス
	 */
	private Updatable nextUpdatable;
	/**
	 * 背景をクリアする色
	 */
	private Color backColor = Color.WHITE;

	/**
	 * Singleton用コンストラクタ
	 */
	private GameManager() {

	}
	/**
	 * GameManagerインスタンスを返す
	 * @return　このクラスの唯一のインスタンス
	 */
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
	/**
	 * 画面幅を指定してフレームを作る
	 * @param width　フレームの幅
	 * @param height　フレームの高さ
	 */
	public void createFrame(int width, int height) {
		createFrame(width, height, "", FPS.DEFAULT_FPS, false);
	}
	/**
	 * 画面幅とタイトルを指定してフレームを作る
	 * @param width　フレームの幅
	 * @param height　フレームの高さ
	 * @param title - バーに表示する文字
	 */
	public void createFrame(int width, int height, String title) {
		createFrame(width, height, title, FPS.DEFAULT_FPS, false);
	}
	/**
	 * 画面幅とタイトルとFPSを指定してフレームを作る
	 * @param width　フレームの幅
	 * @param height　フレームの高さ
	 * @param title - バーに表示する文字
	 * @param fps 1秒に更新する数
	 */
	public void createFrame(int width, int height, String title, int fps) {
		createFrame(width, height, title, fps, false);
	}
	/**
	 * 全ての要素を指定して新しいフレームを作る
	 * 1回の実行で2回以上よばないようにする
	 * @param width　フレームの幅
	 * @param height　フレームの高さ
	 * @param title　バーに表示する文字
	 * @param fps　1秒に更新する数
	 * @param isFullScreen　フルスクリーンにするかどうか
	 * TODO フルスクリーンモードはまだサポートできないのでプライベート化してる
	 */
	private void createFrame(int width, int height, String title, int fps, boolean isFullScreen) {
		if (frame != null) {
			System.err.println("createFrameは1度の実行で2回以上使用してはいけません。無効な操作です");
			return;
		}
		frame = new GameFrame(width, height, title, fps, isFullScreen);
	}
	/**
	 * 更新する対象をセットする<br>
	 * 更新する対象は次のupdateメソッドが呼ばれた段階で入れ替わる
	 * @param updatable　このゲーム上で更新されるクラス
	 */
	public void setUpdatable(Updatable updatable) {
		this.nextUpdatable = updatable;
	}
	/**
	 * フレームの幅を取得する<br>
	 * フレームが存在しなければ-1を返しエラーメッセージを出す
	 * @return　フレームの幅　
	 */
	public int getFrameWidth() {
		if (frame == null) {
			System.err.println("フレームが存在しません。先にcreateFrameを実行してください");
			return -1;
		}
		return frame.getWidth();
	}
	/**
	 * フレームの高さを取得する<br>
	 * フレームが存在しなければ-1を返しエラーメッセージを出す
	 * @return　フレームの高さ
	 */
	public int getFrameHeight() {
		if (frame == null) {
			System.err.println("フレームが存在しません。先にcreateFrameを実行してください");
			return -1;
		}
		return frame.getHeight();
	}
	
	/**
	 * 現在のFPSを文字列で取得<br>
	 *  少数点2位まで　デバック用
	 * @return　文字列で表された実質FPS
	 */
	public String getFps() {
		return frame.getFps();
	}
	
	/**
	 * アップデートする<br>
	 * ユーザーは使用しない
	 */
	public void update() {
		//次のupdatableが設定されていればそれにする
		if (nextUpdatable != null) {
			updatable = nextUpdatable;
			nextUpdatable = null;
		}
		if (updatable == null) return;
		updatable.update();
	}
	/**
	 * 描画する<br>
	 * 常に背景は白く塗りつぶされる
	 * ユーザーは使用しない
	 * @param d　描画用クラス
	 */
	public void draw(Drawer d) {
		if (updatable == null) return;
		updatable.draw(d);
	}
	/**
	 * 背景の色を取得する
	 * @return　- 色
	 */
	public Color getBackGroundColor() {
		return backColor;
	}
	/**
	 * 背景の色を設定する
	 * @param color - 色
	 */
	public void setBackGroundColor(Color color) {
		this.backColor = color;
	}
	

}
