package densan.s.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * キー入力用クラス
 * @author S
 *
 */
public class KeyInput implements KeyListener {
	/**
	 * 押してるかを入れる配列
	 */
	private static long[] pressTimes = new long[Character.MAX_VALUE];
	/**
	 * Singleton用インスタンス
	 */
	private static final KeyInput keyInput = new KeyInput();

	/**
	 * Singleton用コンストラクタ
	 */
	private KeyInput() {

	}
	/**
	 * Instanceを返す<br>
	 * GUIに登録する用なのでユーザーはstaticメソッドを使用する
	 * @return　このクラス唯一のインスタンス。
	 */
	public static KeyInput getInstance() {
		return keyInput;
	}
	/**
	 * そのキーが推されているか返す
	 * @param code KeyEventクラスのキーを表す定数を指定
	 * @return　押されている状態ならtrue
	 */
	public static boolean isPressing(int code) {
		return pressTimes[code] > 0;
	}
	/**
	 * 指定されたキーのどれか一つでも推されていればtureを返す
	 * @param codes　KeyEventクラスのキーを表す定数を複数指定
	 * @return どれかが押されている状態ならtrue
	 */
	public static boolean isPressing(int... codes) {
		boolean rtn = false;
		for (int code:codes) {
			if (pressTimes[code] > 0) {
				rtn = true;
			}
		}
		return rtn;
	}
	/**
	 * 推された瞬間か返す<br>
	 * * 注意点として、1回のループで複数回呼び出した場合、極稀に呼び出す間にクリックをしてしまうことがあり、<br>
	 * 同じループ内でも別の結果を返すことがある点に注意してください。<br>
	 * @param code　KeyEventクラスのキーを表す定数を指定
	 * @return 押されてから1回目の呼び出しならture
	 */
	public static boolean isPress(int code) {
		if (pressTimes[code] == 1) {
			//一度呼ばれたら足す
			//pressTimes[code]++;
			return true;
		}
		return false;
	}
	/**
	 * 指定されたコードのどれか一つでも推された瞬間ならtrueを返す<br>
	 * 注意点として、1回のループで複数回呼び出した場合、極稀に呼び出す間にクリックをしてしまうことがあり、<br>
	 * 同じループ内でも別の結果を返すことがある点に注意してください。<br>
	 * @param codes　KeyEventクラスのキーを表す定数を複数指定
	 * @return 押されたフレームならtrue
	 */
	public static boolean isPress(int... codes) {
		boolean rtn = false;
		for (int code:codes) {
			if (pressTimes[code] == 1) {
				//一度呼ばれたら足す
				//pressTimes[code]++;
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 無し
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//押されたキーの値を足す
		pressTimes[e.getKeyCode()]++;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//離されたキーの値を0にする
		pressTimes[e.getKeyCode()] = 0;

	}
	/**
	 * 前フレームで押されていたキーの押されている時間を増やす<br>
	 * 押されていた瞬間用<br>
	 * ユーザーは使わない
	 */
	public void update() {
		for (int i=0; i < pressTimes.length; i++) {
			if (pressTimes[i] > 0) {
				pressTimes[i]++;
			}
		}
	}

}
