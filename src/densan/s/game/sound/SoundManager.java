package densan.s.game.sound;

/**
 * 
 * 効果音やBGMを扱うクラス
 * 
 * @author S
 *
 */
public class SoundManager {
	/**
	 * 全ての音楽を止める
	 */
	public static void stopAllSound() {
		stopAllSE();
		stopBGM();
	}
	/**
	 * 効果音ファイルを読み込んで登録番号を返す
	 * @param fileName　文字列で表されたファイルのパス
	 * @return 登録番号。読み込めなかった場合は-1
	 */
	public static int loadSE(String fileName) {

			return SEEngine.load(fileName);
		
	}
	/**
	 * 効果音を再生する
	 * 前に再生されていたものもそのまま流れる
	 * @param number - 再生する登録番号
	 */
	public static void playSE(int number) {
		SEEngine.play(number);
	}
	/**
	 * 
	 * 指定された登録番号の効果音の再生を止める
	 * @param number - 止める登録番号
	 */
	public static void stopSE(int number) {
		SEEngine.stop(number);
	}
	/**
	 * 全ての効果音の再生を止める
	 */
	public static void stopAllSE() {
		for (int i=0; i < SEEngine.getLoadCount(); i++) {
			SEEngine.stop(i);
		}
	}
	/**
	 * BGMをロードする
	 * 現在、実行可能jarにした場合にBGMでのwavの再生がうまくいっていないので注意
	 * @param fileName　文字列で表されたファイルのパス
	 * @return 登録番号
	 */
	public static int loadBGM(String fileName) {
		return BGMEngine.load(fileName);
	}
	/**
	 * BGMを再生する
	 * @param number 再生する登録番号
	 */
	public static void playBGM(int number) {
		BGMEngine.play(number);
	}
	/**
	 * BGMをループさせながら再生する
	 * @param number　再生する登録番号
	 */
	public static void playLoopBGM(int number) {
		BGMEngine.loopPlay(number);
	}
	
	/**
	 * BGMの再生を止める
	 */
	public static void stopBGM() {
		BGMEngine.stop();
	}
	
}
