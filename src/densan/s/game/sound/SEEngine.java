package densan.s.game.sound;
import java.net.URL;

import javafx.scene.media.AudioClip;

/*
 * Created on 2005/08/15
 *
 */

/**
 * 効果音ファイルを再生するクラス<br>
 * ユーザーはSoundManagerクラスの方を使う
 * @author mori
 *
 */
public class SEEngine {
	// 登録できるWAVEファイルの最大数
	public static final int MAX_CLIPS = 256;

	// WAVEファイルデータ
	private static SEClip[] dataClips = new SEClip[MAX_CLIPS];
	// 登録されたWAVEファイル数
	private static int counter = 0;

	/**
	 * 効果音ファイルをロード
	 *
	 * @param url
	 *            効果音ファイルのURL
	 * @return 登録した番号　登録できなかった場合は−1
	 */
	public static int load(URL url){
		//すでに登録されてるか調べて登録されてるなら登録番号を返す
		for (int i=0; i < getLoadCount(); i++) {
			if (dataClips[i].getFile().equals( url.getPath())) {
				return i;
			}
		}
		if (counter == MAX_CLIPS) {
			System.err.println("エラー: これ以上登録できません");
			return -1;
		}
		


				// 空のクリップを作成
				AudioClip clip = new AudioClip(url.toExternalForm());
				// クリップを登録
				dataClips[counter] = new SEClip(clip, url);

			
		
		counter++;
		//登録した番号を返す
		return counter-1;
	}

	/**
	 * 効果音ファイルをロード
	 *
	 * @param filename
	 *            効果音ファイル名
	 *  @return 登録した番号　登録できなかった場合は−1
	 */
	public static int load(String filename){
		//URL url = WaveEngine.class.getClassLoader().getResource(filename);
		URL url = ClassLoader.getSystemResource(filename);

		return load(url);
	}

	/**
	 * 再生開始
	 *
	 * @param no
	 *            再生する効果音の番号
	 */
	public static void play(int no) {
		if (dataClips[no] == null) {
			return;
		}
		dataClips[no].play();
	}

	/**
	 * 停止
	 *
	 * @param no
	 *            停止する効果音の番号
	 */
	public static void stop(int no) {
		if (dataClips[no] == null) {
			return;
		}
		// 名前に対応するクリップを取得
		dataClips[no].stop();
	}
	/**
	 * いくつロードされたかを返す
	 * @return　ロードされたファイルの数
	 */
	public static int getLoadCount() {
		return counter;
	}


}
