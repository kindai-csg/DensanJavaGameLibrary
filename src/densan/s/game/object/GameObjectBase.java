package densan.s.game.object;

import densan.s.game.drawing.Drawer;
/**
 * 画面上に表示されるオブジェクトの抽象クラス<br>
 * オブジェクトを消すかどうかのフラグと、draw,updateメソッドを実装している<br>
 * オブジェクトを消すかのフラグは、それをtrueにしただけでは消去されない<br>
 * 実装の際にその機能を自分で追加する必要がある<br>
 * これらの機能がいらない場合はGameVectorBaseを継承してつかうことを推奨する
 * @author S
 * @since 0.11
 *
 */
public abstract class GameObjectBase extends GameVectorBase {

	/**
	 *  オブジェクトが生きてるかどうか。
	 */
	private boolean isRemove = false;

	/**
	 * コンストラクタ<br>
	 *  使わなかったり後で設定する場合は適当な値で大丈夫
	 * @param x 左上のx座標
	 * @param y 左上のy座標
	 * @param width 幅
	 * @param height 高さ
	 */
	public GameObjectBase(double x, double y, int width, int height) {
		super(x, y, width, height);
	}


	/**
	 * オブジェクトが削除要求されていたらtrueを返す。
	 *
	 * @return　オブジェクトが削除要請されていたらtrue
	 */
	public boolean isRemove() {
		return isRemove;
	}
	/**
	 * オブジェクトが消える<br>
	 * aliveフラグをfalseにする
	 */
	public void remove() {
		isRemove = true;
	}
	/**
	 * オブジェクトの更新
	 */
	public abstract void update();
	/**
	 * オブジェクトの描画
	 * @param d 描画用クラス
	 */
	public abstract void draw(Drawer d);


}
