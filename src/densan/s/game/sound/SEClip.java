package densan.s.game.sound;
import java.net.URL;

import javafx.scene.media.AudioClip;



/**
 * ユーザーは使用しない
 * 効果音ファイルを表すクラス
 * @author mori
 *
 */
public class SEClip {
	// 効果音ファイルのパス
	private String fileName;
	//クリップ
	private AudioClip clip;

	/**
	 * wave用のコンテナクラスを生成
	 * @param clip　waveのクリップ
	 * @param url　waveのファイルのパス
	 */
	public SEClip(AudioClip clip, URL url) {

		this.fileName = url.getPath();

		this.clip = clip;

	}
	/**
	 * パスを返す
	 * @return　ファイルのパス
	 */
	public String getFile() {
		return fileName;
	}
	/**
	 * クリップを返す
	 * @return　クリップ
	 */
	public AudioClip getClips() {
		return clip;
	}
	/**
	 * 再生
	 */
	public void play() {
		clip.play();
	}
	/**
	 * 停止
	 *
	 */
	public void stop() {
		clip.stop();

	}
}
