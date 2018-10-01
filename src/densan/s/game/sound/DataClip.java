package densan.s.game.sound;
import java.net.URL;

import javax.sound.sampled.Clip;

/*
 * Created on 2005/08/15
 *
 */

/**
 * ユーザーは使用しない
 * Waveファイルを表すクラス
 * @author mori
 *
 */
public class DataClip {
	//クリップの総数
	public static final int CLIP_NUM = 3;
	// WAVEファイルのパス
	private String fileName;
	//クリップ
	private Clip[] clips;

	//再生するクリップの番地
	private int current = 0;



	//	 再生中か
	public boolean running = false;
	// 1フレーム（ゲームループ1周）で再生するバイト数を計算する
	public int sampleRate;
	/**
	 * wave用のコンテナクラスを生成
	 * @param clip　waveのクリップ
	 * @param url　waveのファイルのパス
	 */
	public DataClip(Clip[] clip, URL url) {

		this.fileName = url.getPath();

		this.clips = clip;

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
	public Clip[] getClips() {
		return clips;
	}
	public void play() {
		Clip clip = clips[current];
		//clip.flush();
    	clip.setFramePosition(0); // 再生位置を最初に戻す
    	//clip.drain();
    	//if (!clip.isRunning()) {
    		clip.start();

    	//}
    		current = (current+1)%CLIP_NUM;
	}
	/**
	 * 停止
	 *
	 */
	public void stop() {
		for (Clip c: clips) {
			c.stop();
			c.setFramePosition(0);
		}


	}
}
