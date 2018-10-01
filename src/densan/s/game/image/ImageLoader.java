package densan.s.game.image;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
/**
 * 画像を読み込む用のクラス
 * @author S
 *
 */
public class ImageLoader {
	/**
	 * ロードできる最大画像数
	 */
	public static final int MAX_IMAGE_NUM = 512;
	/**
	 * 読み込んだファイルの名前の配列
	 */
	private static String[] fileNmaes = new String[MAX_IMAGE_NUM];
	/**
	 * 読み込んだ画像の配列
	 */
	private static Image[] loadImages = new Image[MAX_IMAGE_NUM];
	/**
	 * いくつ読み込んだか
	 */
	private static int count = 0;

	/**
	 * 指定されたパスの画像を読み込む
	 * @param pass 画像ファイルのパス。srcフォルダからのパス
	 * @return　読み込んだ画像
	 */
	public static Image load(String pass){
		if (count == MAX_IMAGE_NUM) {
			System.err.println("画像のキャッシュが上限に達しました:"+MAX_IMAGE_NUM);
			System.err.println("画像のキャッシュをクリアします");
			clearCache();
		}
		//すでに読み込んでないか調べる
		for (int i=0; i < count; i++) {
			if (pass.equals(fileNmaes[i])) {
				//読み込んでたらその画像を返す
				return loadImages[i];
			}
		}
		try {
			//画像のURLを取得
			URL url = ImageLoader.class.getClassLoader().getResource(pass);
			//イメージアイコンとして読み込み
			ImageIcon icon = new ImageIcon(url);
			//イメージに変換
			Image img = icon.getImage();
			/*if (img.getWidth(null) == 0) {
			System.out.println("画像が読み込めません");
			return null;
		}*/
			//カウントを進めて読み込んだ画像に入れる
			fileNmaes[count] = pass;
			loadImages[count] = img;
			count++;
			return img;
		}catch (Exception e) {
			System.err.println(pass+"の読み込みでエラー");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 読み込んだ画像のキャッシュを削除する。<br>
	 * キャッシュを削除するだけなのですでにオブジェクトなどで使っている画像に影響はない
	 * @since 0.12
	 */
	public static void clearCache() {
		fileNmaes = new String[MAX_IMAGE_NUM];
		loadImages = new Image[MAX_IMAGE_NUM];
		count = 0;
	}
}
