package densan.s.game.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
/**
 * 描画用のメソッドが存在するクラス<br>
 * 全てstaticメソッド
 * @author s
 * @since 0.10
 *
 */
public class DrawingTool {
	/**
	 * 画像を回転させて描画するメソッド<br>
	 * 回転軸は画像の中心、回転角の指定方法はラジアン。<br>
	 * 回転は正の方向が右回り
	 * @param image　描画する画像
	 * @param x 画像の左上のx座標
	 * @param y 画像の左上のy座標
	 * @param theta 回転させる角度
	 * @param g 描画する本体のGraohicsクラス
	 */
	public static void drawRotationImage(Image image, double x, double y,double theta, Graphics g) {
		drawRotationImage(image, x, y, image.getWidth(null), image.getHeight(null), theta, g);
	}
	/**
	 * 画像を回転させて描画するメソッド<br>
	 * 回転軸は画像の中心、回転角の指定方法はラジアン。<br>
	 * 回転は正の方向が右回り
	 * @param image　描画する画像
	 * @param x 画像の左上のx座標
	 * @param y 画像の左上のy座標
	 * @param width 描画する幅
	 * @param height　描画する高さ
	 * @param theta 回転させる角度
	 * @param g 描画する本体のGraohicsクラス
	 */
	public static void drawRotationImage(Image image, double x, double y, int width, int height,double theta, Graphics g) {
		/*BufferedImage bimg;
		try {
			bimg = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics bg = bimg.getGraphics();
			bg.drawImage(image, 0, 0,width,height, null);
			bg.dispose();
		} catch (java.lang.IllegalArgumentException e) {
			bimg = null;
		}*/
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		
		//移動
		at.translate(x, y);
		//回転
		at.rotate(theta, width / 2, height / 2);
		//拡大
		at.scale((double)width/image.getWidth(null), (double)height/image.getHeight(null));
		
		//描画
		g2.drawImage(image, at, null);
		
		//AffineTransformOp atop = new AffineTransformOp(at,
			//	AffineTransformOp.TYPE_BILINEAR);
		
		//g2.drawImage(bimg, atop, (int) x, (int) y);
		
	}


	/**
	 * x, yを中心に直径sizeの円を書く
	 * @param x　円の中心のX座標
	 * @param y　円の中心のY座標
	 * @param size　- 直径
	 * @param g guiから渡された描画用クラス
	 */
	public static void drawCircle(int x, int y, int size, Graphics g) {
		g.drawOval(x - size/2, y - size/2, size, size);
	}
	
	/**
	 * x, yを中心に直径sizeの円を塗りつぶして書く
	 * @param x　円の中心のX座標
	 * @param y　円の中心のY座標
	 * @param size　- 直径
	 * @param g guiから渡された描画用クラス
	 */
	public static void fillCircle(int x, int y, int size, Graphics g) {
		g.fillOval(x - size/2, y - size/2, size, size);
	}

	/**
	 * 文字を指定座標の中央に描画<br>
	 * 縦方向も中心に合わさる
	 * @param g - Graphicオブジェクト
	 * @param text - 書きたい文字
	 * @param x - x座標
	 * @param y - y座標
	 */
	public static void drawStringCenter(String text,int x,int y,Graphics g){
		FontMetrics fm = g.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(text, g).getBounds();
		x=x-rectText.width/2;
		y=y-rectText.height/2+fm.getMaxAscent();
		
        g.drawString(text, x, y);
	}
	/**
	 * 文字を右づめで描画
	 * @param g - Graphicオブジェクト
	 * @param text - 書きたい文字
	 * @param x - x座標
	 * @param y - y座標
	 */
	public static void drawStringRight(String text,int x,int y,Graphics g){
		FontMetrics fm = g.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(text, g).getBounds();
		x=x-rectText.width;
		
        g.drawString(text, x, y);
	}
}
