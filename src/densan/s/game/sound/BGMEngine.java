package densan.s.game.sound;

import java.net.URL;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * BGMを再生するクラス<br>
 * ユーザーはSoundManagerクラスの方を使う
 * @author S
 *
 */
public class BGMEngine {
	/**
	 * 読み込める音楽の最大数
	 */
	public static final int MAX_FILES = 256;

	/**
	 * ファイル名の配列
	 */
	private static MediaPlayer[] players = new MediaPlayer[MAX_FILES];

	private static MediaPlayer currentPlayer;
	/**
	 * いくつ登録されたか
	 */
	private static int count = 0;

	static {
		new JFXPanel();
	}

	/**
	 * ファイルのロード
	 * @param fileName　文字列で表されたファイルのパス
	 * @return　登録番号
	 */
	public static int load(String fileName) {
		URL url = ClassLoader.getSystemResource(fileName);
		//URL url = MP3Engine.class.getClassLoader().getResource(fileName);

		return load(url);
	}
	/**
	 * ファイルのロード
	 * @param url　urlクラスで表されたファイルのパス
	 * @return 登録番号
	 */
	public static int load(URL url) {
		//すでに登録されてるか調べて登録されてるなら登録番号を返す

		for (int i=0; i < count; i++) {
			//System.out.println("Med:" + files[i].getMedia().getSource());
			if (players[i].getMedia().getSource().equals( url.toExternalForm())) {
				return i;
			}

		}
		
		if (count == MAX_FILES) {
			System.out.println("これ以上登録できません");

			return -1;
		}
		//メディアプレーヤーでBGMファイルを読み込み
		MediaPlayer m =  new MediaPlayer(new Media(url.toExternalForm()));
		//配列に登録
		players[count] = m;
		
		count++;
		return count-1;
	}
	/**
	 * 再生を止める
	 */
	public static void stop() {
		//現在再生中であれば
		if (currentPlayer != null) {
			//現在再生中のBGMを停止
			currentPlayer.stop();
		}

	}
	/**
	 * BGMの再生
	 * ループはしない
	 * @param no 登録番号
	 */
	public static void play(int no) {
		//現在再生しているBGMを停止
		stop();
		//登録番号にBGMが存在しなければ何もしない
		if (players[no] == null) {
			return;
		} 
		//プレイヤーを指定のに設定
		currentPlayer = players[no];
		//1回だけ再生するように設定。
		currentPlayer.setCycleCount(1);
		//再生
		currentPlayer.play();
		//System.out.println(players[no].getMedia().);



	}
	/**
	 * BGMをループさせて再生
	 * @param no 再生する登録番号
	 */
	public static void loopPlay(int no) {
		//現在再生しているBGMを停止
		stop();
		//登録番号にBGMが存在しなければ何もしない
		if (players[no] == null) {
			return;
		} 
		//プレイヤーを指定のに設定
		currentPlayer = players[no];
		//無限にループするように設定
		currentPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//再生
		currentPlayer.play();
		//System.out.println(players[no].getMedia().);

	}




}
