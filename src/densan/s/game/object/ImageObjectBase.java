package densan.s.game.object;

import java.awt.Image;

import densan.s.game.drawing.Drawer;
import densan.s.game.image.ImageLoader;
/**
 * 画像を描画するオブジェクト<br>
 * 幅と高さは画像のものになる。<br>
 * 回転も可能になっている<br>
 * GameObjectBaseを継承している
 * @author S
 * @since 0.11
 *
 */
public abstract class ImageObjectBase extends GameObjectBase {
	/**
	 * 画像
	 */
	private Image image;
	
	/**
	 * 画像を描画するときの回転角(ラジアン<br>
	 * 右回り
	 */
	private double theta = 0.0;

	/**
	 * 画像のパスを指定してオブジェクトを生成<br>
	 * 幅と高さは画像のものになる
	 * @param x　x座標
	 * @param y　y座標
	 * @param imageName 画像のパス
	 */
	public ImageObjectBase(double x, double y, String imageName) {
		this(x, y, ImageLoader.load(imageName));
	}
	/**
	 * 画像を指定してオブジェクトを生成<br>
	 * 幅と高さは画像のものになる
	 * @param x　x座標
	 * @param y　y座標
	 * @param image 画像
	 * @since 0.6
	 */
	public ImageObjectBase(double x, double y, Image image) {
		super(x, y, image.getWidth(null), image.getHeight(null));
		this.image = image;
	}
	/**
	 * 画像描画時の回転角度を取得する(右回り)
	 * @return ラジアンで表された回転角度
	 * @since 0.10
	 */
	public double getTheta() {
		return theta;
	}
	/**
	 * 画像描画時の回転角度を設定する(右回り)
	 * @param theta　ラジアンで表された回転角度
	 * @since 0.10
	 */
	public void setTheta(double theta) {
		this.theta = theta;
	}
		
	/**
	 * このオブジェクトに設定されている画像を取得する
	 * @return　このオブジェクトで描画されている画像
	 * @since 0.11
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * このオブジェクトに画像を設定する。<br>
	 * このオブジェクトの幅と高さは設定された画像の幅と高さになる。<br>
	 * nullを指定すると幅と高さは0に設定されなにも描画されなくなる。
	 * @param image　このオブジェクトに設定する画像
	 * @since 0.11
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	

	/**
	 * オブジェクトのサイズを画像サイズから引数の拡大率で拡大した値にセットする
	 * @param scale - 拡大率
	 * @since 0.10
	 */
	public void setScale(double scale) {
		setWidth((int)(image.getWidth(null)*scale));
		setHeight((int)(image.getHeight(null)*scale));
	}

	@Override
	public void draw(Drawer d) {
		//画像を描画
		d.drawRotationImage(image, getX(), getY(),getWidth(),getHeight(), theta);
		//g.drawImage(image, (int)getX(), (int)getY(),getWidth(), getHeight(), null);
	}
	

}
