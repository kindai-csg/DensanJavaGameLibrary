package densan.s.game.fps;
import java.text.DecimalFormat;

/**
 * FPSを計算して維持するためのクラス
 * @author S
 *
 */
public class FPS {
	/**
	 * デフォルトのFPS(60)
	 * 
	 */
	public static final int DEFAULT_FPS = 60;
	
	/** 期待するFPS（1秒間に描画するフレーム数）
	 * 
	 */
	private int fps;

	/**
	 *  1フレームで使える持ち時間
	 */
	private long PERIOD; // 単位: ns

	/**
	 *  FPSを計算する間隔（1s = 10^9ns）
	 */
	private static long MAX_STATS_INTERVAL = 1000000000L; // 単位: ns

	

	/**
	 *  フレーム数
	 */
	private long frameCount = 0;
	/**
	 *  実際のFPS
	 */
	private double actualFPS = 0.0;
	/**
	 * 数値を文字に整形するクラス
	 */
	private DecimalFormat df = new DecimalFormat("00.00");
	/**
	 *  FPS計算用
	 */
	private long calcInterval = 0L; // in ns
	/**
	 *  FPS計算用
	 */
	private long prevCalcTime;
	/**
	 *  FPS計算用
	 */
	private long beforeTime, afterTime, timeDiff, sleepTime;
	/**
	 *  FPS計算用
	 */
	private long overSleepTime = 0L;
	/**
	 *  FPS計算用
	 */
	private int noDelays = 0;

	/**
	 * デフォルトのFPSで計算するクラス
	 */
	public FPS() {
		beforeTime = System.nanoTime();
		prevCalcTime = beforeTime;
	}
	/**
	 * FPSを指定して作成
	 * @param fps 指定するFPS
	 */
	public FPS(int fps) {
		this.fps = fps;
		PERIOD = (long) (1.0 / fps * 1000000000); // 単位: ns
		beforeTime = System.nanoTime();
		prevCalcTime = beforeTime;
	}
	/**
	 *  FPSの処理をする<br>
	 *  毎フレーム呼び出す
	 */
	public void runFPS() {
		afterTime = System.nanoTime();
		timeDiff = afterTime - beforeTime;
		// 前回のフレームの休止時間誤差も引いておく
		sleepTime = (PERIOD - timeDiff) - overSleepTime;

		if (sleepTime > 0) {
			//	 休止時間がとれる場合
			try {
				Thread.sleep(sleepTime / 1000000L); // nano->ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// sleep()の誤差
			overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
		} else {
			// 状態更新・レンダリングで時間を使い切ってしまい
			// 休止時間がとれない場合
			overSleepTime = 0L;
			// 休止なしが16回以上続いたら
			if (++noDelays >= 16) {
				Thread.yield(); // 他のスレッドを強制実行
				noDelays = 0;
			}
		}

		beforeTime = System.nanoTime();

		// FPSを計算
		calcFPS();
	}

	/**
	 *  FPSを返す。<br>
	 *  少数点2位まで　デバック用
	 * @return　文字列で表されたFPS
	 */
	public String getFPS() {
		return df.format(actualFPS);
	}
	/**
	 * 設定されたFPSを返す<br>
	 * できるだけこの値にするようにする
	 * @return 設定されたFPS
	 */
	public int getSettingFPS() {
		return fps;
	}

	/**
	 *  FPSの計算。
	 */
	private void calcFPS() {
		frameCount++;
		calcInterval += PERIOD;

		// 1秒おきにFPSを再計算する
		if (calcInterval >= MAX_STATS_INTERVAL) {
			long timeNow = System.nanoTime();
			// 実際の経過時間を測定
			long realElapsedTime = timeNow - prevCalcTime; // 単位: ns

			// FPSを計算
			// realElapsedTimeの単位はnsなのでsに変換する
			actualFPS = ((double) frameCount / realElapsedTime) * 1000000000L;

			frameCount = 0L;
			calcInterval = 0L;
			prevCalcTime = timeNow;
		}
	}
}
