package densan.s.game.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

/**
 * 描画用のの抽象クラス<br>
 * SwingからjavaFXへの移行のために作った<br>
 * drowメソッドの引数から取得できるこのクラスのメソッドを使って画面に描画する
 * @author s
 *
 */
public abstract class Drawer {
	/**
	 * デフォルトのフォントサイズ
	 */
	public static final int DEFAULT_FONT_SIZE = 12;
	/**
	 * デフォルトの色
	 */
	public static final Color DEFAULT_COLOR = Color.BLACK;

	public Drawer() {
		//サイズをデフォルトにする
		//グラフィックスを先にいれないといけないからここでは出来ない
		//setFontSize(DEFAULT_FONT_SIZE);
		//setColor(DEFAULT_COLOR);
	}

	/**
	 * 描画する色を指定する
	 * @param color - 設定する色
	 */
	public abstract void setColor(Color color);
	/**
	 * 現在の色を取得する
	 * @return - 現在の色
	 */
	public abstract Color getColor();

	/**
	 * フォントサイズを設定する
	 * @param size - フォントサイズ
	 */
	public abstract void setFontSize(int size);
	/**
	 * フォントを指定する
	 * @param font - フォント
	 */
	public abstract void setFont(Font font);

	/**
	 * 現在のフォントサイズを取得する
	 * @return - フォントサイズ
	 */
	public abstract int getFontSize();
	/**
	 * 現在のフォントを取得する
	 * @return - フォント
	 */
	public abstract Font getFont();

	/**
	 * 画像を描画するメソッド
	 * @param image　描画する画像
	 * @param x 画像の左上のx座標
	 * @param y 画像の左上のy座標
	 */
	public abstract void drawImage(Image image, double x, double y);
	/**
	 * 画像を指定の幅、高さで描画

	 * @param img - 画像
	 * @param x - 左上のx座標
	 * @param y - 左上のy座標
	 * @param width - 描画する幅
	 * @param height - 描画する高さ
	 */
	public abstract void drawImage(Image img,
			int x,
			int y,
			int width,
			int height);
	/**
	 * 画面の指定した座標にソ―ス画像の指定した部分のみを描画する。<br>
	 * サイズが違う場合縮尺される
パラメータ:
@param img - 描画される指定イメージimg が null の場合には何も行わない
@param dx1 - 出力先の矩形の最初の隅の x 座標
@param dy1 - 出力先の矩形の最初の隅の y 座標
@param dx2 - 出力先の矩形の 2 番目の隅の x 座標
@param dy2 - 出力先の矩形の 2 番目の隅の y 座標
@param sx1 - ソース矩形の最初の隅の x 座標
@param sy1 - ソース矩形の最初の隅の y 座標
@param sx2 - ソース矩形の 2 番目の隅の x 座標
@param sy2 - ソース矩形の 2 番目の隅の y 座標

	 */
	public abstract void drawImage(Image img,
			int dx1,
			int dy1,
			int dx2,
			int dy2,
			int sx1,
			int sy1,
			int sx2,
			int sy2);
	/**
	 * 画像を拡大・縮小して描画します。scaleの値が1より上なら拡大、1より下なら縮小です。
	 *
	 * @param image - 画像
	 * @param x - 左上のx座標
	 * @param y - 左上のy座標
	 * @param scale - 拡大率
	 */
	public abstract void drawScaleImage(Image image, double x, double y, double scale);
	/**
	 * 画像を反転させて描画するメソッド
	 * @param image - 画像
	 * @param x - 左上のx座標
	 * @param y - 左上のy座標
	 * @param horizontal --trueなら左右反転
	 * @param vertical - trueなら上下反転
	 */
	public abstract void drawFlipImage(Image image, double x, double y, boolean horizontal
			,boolean vertical);
	/**
	 * 画像を回転させて描画するメソッド<br>
	 * 回転軸は画像の中心、回転角の指定方法はラジアン。<br>
	 * 回転は正の方向が右回り
	 * @param image　描画する画像
	 * @param x 画像の左上のx座標
	 * @param y 画像の左上のy座標
	 * @param theta 回転させる角度
	 */
	public abstract void drawRotationImage(Image image, double x, double y,double theta);
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
	 */
	public abstract void drawRotationImage(Image image, double x, double y, double width, double height,double theta);
	/**
	 * 指定された矩形をカバーする円弧または楕円弧の輪郭を描きます。<br>
	 *startAngle から始まって arcAngle の角度で展開された弧が、現在の色を使って描かれます。角度は 3 時の位置を 0 度として変換され、正の値は反時計方向、負の値は時計方向の回転を示します。
	 * @param x - 描画される弧の左上隅の x 座標
	 * @param y - 描画される弧の左上隅の y 座標
	 * @param width - 描画される孤の幅
	 * @param height - 描画される孤の高さ
	 * @param startAngle - 開始角度
	 * @param arcAngle - 開始角度に対する弧の展開角度の大きさ
	 */
	public abstract void drawArc(double x,
			double y,
			double width,
			double height,
			double startAngle,
			double arcAngle);
	/**
	 * (x1,y1)から(x2, y2)に太さthickの線を書く
	 * @param x1 始点のx座標
	 * @param y1 始点のy座標
	 * @param x2 終点のx座標
	 * @param y2 終点のy座標
	 * @param thick 線の太さ
	 */
	public abstract void drawLine(double x1, double y1, double x2, double y2, double thick);
	/**
	 * x,yに幅width、高さheightの四角形を塗りつぶしなしで書く(x,yは左上の点)
	 * @param x　左上のx座標
	 * @param y 右上のy座標
	 * @param width 幅
	 * @param height 高さ
	 */
	public abstract void drawRect(double x, double y, double width, double height);
	/**
	 * x,yに幅width、高さheightの四角形を塗りつぶし有りで書く(x,yは左上の点)
	 * @param x　左上のx座標
	 * @param y 右上のy座標
	 * @param width 幅
	 * @param height 高さ
	 */
	public abstract void fillRect(double x, double y, double width, double height);
	/**
	 * x,yに幅width、高さheightの四角形に収まる楕円を塗りつぶしなしで書く(x,yは左上の点)
	 * @param x　四角形の左上のx座標
	 * @param y 四角形の右上のy座標
	 * @param width 幅
	 * @param height 高さ
	 */
	public abstract void drawOval(double x, double y, double width, double height);
	/**
	 * x,yに幅width、高さheightの四角形に収まる楕円を塗りつぶし有りで書く(x,yは左上の点)
	 * @param x　四角形の左上のx座標
	 * @param y 四角形の右上のy座標
	 * @param width 幅
	 * @param height 高さ
	 */
	public abstract void fillOval(double x, double y, double width, double height);

	/**
	 * x, yを中心に半径sizeの円を書く
	 * @param x　円の中心のX座標
	 * @param y　円の中心のY座標
	 * @param range　- 半径
	 */
	public abstract void drawCircle(double x, double y, double range);

	/**
	 * x, yを中心に半径sizeの円を塗りつぶして書く
	 * @param x　円の中心のX座標
	 * @param y　円の中心のY座標
	 * @param range　- 半径
	 */
	public abstract void fillCircle(double x, double y, double range);

	/**
	 * 文字を指定座標の中央に描画<br>
	 * 縦方向も中心に合わさる
	 * @param text - 書きたい文字
	 * @param x - x座標
	 * @param y - y座標
	 */
	public abstract void drawStringCenter(String text,double x,double y);
	/**
	 * 文字を右づめで描画<br>
	 * yは文字の下側の位置
	 * @param text - 書きたい文字
	 * @param x - x座標
	 * @param y - y座標
	 */
	public abstract void drawStringRight(String text,double x,double y);
	/**
	 * 文字を左詰めで描画<br>
	 * yは文字の下側の位置
	 * @param text - 書きたい文字
	 * @param x - x座標
	 * @param y - y座標
	 */
	public abstract void drawString(String text,double x,double y);

}
