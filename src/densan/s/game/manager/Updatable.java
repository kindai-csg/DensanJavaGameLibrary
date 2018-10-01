package densan.s.game.manager;


import densan.s.game.drawing.Drawer;
/**
 * 更新及び描画を含むインターフェース
 * @author　S
 *
 */
public interface Updatable {
	/**
	 * 毎フレーム呼び出される操作
	 */
	public void update();
	/**
	 * 毎フレーム描画する操作
	 * @param d　描画用クラス
	 */
	public void draw(Drawer d);
}
