package densan.s.game.manager.sample;

import java.util.Iterator;
import java.util.LinkedHashSet;

import densan.s.game.drawing.Drawer;
import densan.s.game.manager.Updatable;
import densan.s.game.object.GameObjectBase;
/**
 * AGameObjectを実装したクラスを管理するリスト<br>
 * オブジェクトのisAliveがfalseなら(つまりdie()が使用されていれば)リストから消す<br>
 * オブジェクトの更新や描画の順序は登録順
 *
 * @author S
 *
 */
public class ObjectManageList<T extends GameObjectBase> implements Updatable,Iterable<T> {

	/**
	 * オブジェクトを入れるリスト
	 */
	private LinkedHashSet<T> list = new LinkedHashSet<T>();

	/**
	 * 追加されるオブジェクトを入れるリスト
	 */
	private LinkedHashSet<T> addList = new LinkedHashSet<T>();
	/**
	 * 生成
	 */
	public ObjectManageList() {


	}
	/**
	 * オブジェクトを追加する<br>
	 * すでに追加されているオブジェクトを追加した場合は何もしない(順序も変わらない)
	 * @param object　追加するオブジェクト
	 */
	public void add(T object) {
		addList.add(object);

	}
	/**
	 * リストのオブジェクトを全て消す
	 */
	public void clear() {
		list.clear();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	/**
	 *　全て更新してから死んだか判定してリストから消す
	 */
	@Override
	public void update() {
		//オブジェクトをリストに追加
		list.addAll(addList);
		//追加した分は消す
		addList.clear();
		//更新する
		for (T ag: list) {
			ag.update();
		}

		Iterator<T> it = list.iterator();

		//死んでたら省く
		while(it.hasNext()) {
			T ag = it.next();
			if (ag.isRemove()) {
				it.remove();
			}
		}

	}

	@Override
	public void draw(Drawer d) {
		for (T ag:list) {
			ag.draw(d);
		}
	}
}
