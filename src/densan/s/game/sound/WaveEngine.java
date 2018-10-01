package densan.s.game.sound;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * Created on 2005/08/15
 *
 */

/**
 * Waveファイルを再生するクラス<br>
 * ユーザーはSoundManagerクラスの方を使う
 * @author mori
 *
 */
public class WaveEngine implements LineListener{
	// 登録できるWAVEファイルの最大数
	public static final int MAX_CLIPS = 256;

	// WAVEファイルデータ
	private static DataClip[] dataClips = new DataClip[MAX_CLIPS];
	// 登録されたWAVEファイル数
	private static int counter = 0;

	private static WaveEngine engine;

	static {
		engine = new WaveEngine();
	}

	/**
	 * WAVEファイルをロード
	 *
	 * @param url
	 *            WAVEファイルのURL
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
		try {
			Clip[] clips = new Clip[DataClip.CLIP_NUM];

			for (int i=0; i< DataClip.CLIP_NUM; i++) {
				// オーディオストリームを開く
				AudioInputStream stream = AudioSystem
						.getAudioInputStream(url);

				// オーディオ形式を取得
				AudioFormat format = stream.getFormat();
				// ULAW/ALAW形式の場合はPCM形式に変更
				if ((format.getEncoding() == AudioFormat.Encoding.ULAW)
						|| (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
					AudioFormat newFormat = new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							format.getSampleRate(),
							format.getSampleSizeInBits() * 2, format.getChannels(),
							format.getFrameSize() * 2, format.getFrameRate(), true);
					stream = AudioSystem.getAudioInputStream(newFormat, stream);
					format = newFormat;
				}

				// ライン情報を取得
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				// サポートされてる形式かチェック
				if (!AudioSystem.isLineSupported(info)) {
					System.out.println("エラー: " + url.getPath() + "はサポートされていない形式です");
					System.exit(0);
				}

				// 空のクリップを作成
				Clip clip = (Clip) AudioSystem.getLine(info);
				// クリップのイベントを監視
				clip.addLineListener(engine);
				// オーディオストリームをクリップとして開く
				clip.open(stream);
				// クリップを登録
				clips[i] = clip;

				// ストリームを閉じる
				stream.close();
			}
			dataClips[counter] = new DataClip(clips, url);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		counter++;
		//登録した番号を返す
		return counter-1;
	}

	/**
	 * WAVEファイルをロード
	 *
	 * @param filename
	 *            WAVEファイル名
	 *  @return 登録した番号　登録できなかった場合は−1
	 */
	public static int load(String filename){
		URL url = WaveEngine.class.getClassLoader().getResource(filename);
		return load(url);
	}

	/**
	 * 再生開始、鳴らすにはゲームループでrender()が必要
	 *
	 * @param no
	 *            再生するDataClipの番号
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
	 *            停止するDataClipの番号
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


	@Override
	public void update(LineEvent event) {
		// ストップか最後まで再生された場合
		if (event.getType() == LineEvent.Type.STOP) {
			Clip clip = (Clip) event.getSource();
			clip.stop();
			clip.setFramePosition(0); // 再生位置を最初に戻す
			//System.out.println("STOP");

		}

	}
}
