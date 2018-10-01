package densan.s.game.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
/**
 * swing用のDrowerクラス<br>
 * javaFXに置き換える予定
 * @author yazirusi_s
 *
 */
public class SwingDrawer extends Drawer {
	/**
	 * グラフィックス用クラス
	 */
	private Graphics2D g;
	/**
	 * 最初のフォントを保存
	 */
	private Font firstFont;

	/**
	 * Swingの描画クラスからDrawerを生成
	 * @param g 描画先のGraphicsクラス
	 */
	public SwingDrawer(Graphics g) {

		//2Dに変換してから入れる
		this.g = (Graphics2D)g;
		firstFont = g.getFont();
		firstFont = new Font(Font.DIALOG, Font.PLAIN, DEFAULT_FONT_SIZE);
		setFontSize(DEFAULT_FONT_SIZE);
		setColor(DEFAULT_COLOR);

	}

	@Override
	public void setColor(Color color) {
		g.setColor(color);


	}

	@Override
	public Color getColor() {

		return g.getColor();
	}

	@Override
	public void setFontSize(int size) {
		g.setFont(new Font(firstFont.getFontName(), firstFont.getStyle(), size));

	}

	@Override
	public int getFontSize() {
		return g.getFont().getSize();
	}

	@Override
	public void drawImage(Image image, double x, double y) {
		g.drawImage(image, (int)x, (int)y, null);

	}

	@Override
	public void drawRotationImage(Image image, double x, double y, double theta) {
		DrawingTool.drawRotationImage(image, x, y, theta, g);

	}

	@Override
	public void drawRotationImage(Image image, double x, double y,
			double width, double height, double theta) {
		DrawingTool.drawRotationImage(image, x, y, (int)width, (int)height, theta, g);

	}

	@Override
	public void drawArc(double x, double y, double width, double height,
			double startAngle, double arcAngle) {
		g.drawArc((int)x, (int)y, (int)width, (int)height, (int)startAngle, (int)arcAngle);

	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2,
			double thick) {
		g.drawLine((int)x1,(int) y1,(int) x2,(int) y2);

	}

	@Override
	public void drawRect(double x, double y, double width, double height) {
		g.drawRect((int)x, (int)y, (int)width, (int)height);

	}

	@Override
	public void fillRect(double x, double y, double width, double height) {
		g.fillRect((int)x, (int)y, (int)width, (int)height);

	}

	@Override
	public void drawOval(double x, double y, double width, double height) {
		g.drawOval((int)x, (int)y,(int) width, (int)height);

	}

	@Override
	public void fillOval(double x, double y, double width, double height) {
		g.fillRect((int)x,(int) y,(int) width,(int) height);

	}

	@Override
	public void drawCircle(double x, double y, double range) {
		DrawingTool.drawCircle((int)x, (int)y, (int)(range*2), g);

	}

	@Override
	public void fillCircle(double x, double y, double range) {
		DrawingTool.fillCircle((int)x, (int)y, (int)(range*2), g);

	}

	@Override
	public void drawStringCenter(String text, double x, double y) {
		DrawingTool.drawStringCenter(text, (int)x, (int)y, g);
	}

	@Override
	public void drawStringRight(String text, double x, double y) {
		DrawingTool.drawStringRight(text, (int)x, (int)y, g);

	}

	@Override
	public void drawString(String text, double x, double y) {
		g.drawString(text, (int)x, (int)y);;

	}

	@Override
	public void setFont(Font font) {
		g.setFont(font);

	}

	@Override
	public Font getFont() {
		return g.getFont();
	}

	@Override
	public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2) {

		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,null );
	}

	@Override
	public void drawFlipImage(Image image, double x, double y,
			boolean horizontal, boolean vertical) {
		if (image == null) return;
		int h = image.getHeight(null);
		int w =  image.getWidth(null);
		if (horizontal) {
			if (vertical) {
				g.drawImage(image, (int)x +w, (int)y+ h,
						-w, -h, null);
			}else {
				g.drawImage(image, (int)x +w, (int)y,
						-w, h, null);
			}

		}else {
			if (vertical) {
				g.drawImage(image, (int)x, (int)y+ h,
						w, -h, null);
			}else {
				g.drawImage(image, (int)x, (int)y, null);
			}
		}

	}

	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		g.drawImage(img, (int)x, (int)y,width, height, null);

	}

	@Override
	public void drawScaleImage(Image image, double x, double y, double scale) {
		g.drawImage(image, (int)x, (int)y,(int)(image.getWidth(null) * scale), (int)(image.getHeight(null) * scale), null);
	}

}
