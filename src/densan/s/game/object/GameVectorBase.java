package densan.s.game.object;
/**
 * ゲーム用の矩形に速度を追加したクラス<br>
 * オリジナルのGameObjectクラスを作成する場合はこのクラスを継承する
 * @author S
 * @since 0.11
 */
public class GameVectorBase extends GameRectBase {
	/**
	 * X座標が動く速度
	 */
	private double vx = 0.0;
	/**
	 * y座標が動く速度
	 */
	private double vy = 0.0;
	/**
	 * 速度を0で生成
	 * @param x　X座標
	 * @param y　Y座標
	 * @param width　幅
	 * @param height　高さ
	 */
	public GameVectorBase(double x, double y, int width, int height) {
		this(x, y, width, height, 0, 0);
	}
	/**
	 * 速度を指定して生成
	 * @param x　X座標
	 * @param y　Y座標
	 * @param width　幅
	 * @param height　高さ
	 * @param vx x座標を動く速度
	 * @param vy y座標を動く速度
	 */
	public GameVectorBase(double x, double y, int width, int height, double vx, double vy) {
		super(x, y, width, height);
		this.vx = vx;
		this.vy = vy;
	}

	/**
	 * オブジェクトの速さを返す。
	 *
	 * @return　オブジェクトの速さ
	 */
	public double getSpeed() {
		return Math.sqrt(Math.pow(getVx(), 2) + Math.pow(getVy(), 2));
	}

	/**
	 * オブジェクトのX軸方向の速さを返す。
	 *
	 * @return　X軸方向の速さ
	 */
	public double getVx() {
		return vx;
	}

	/**
	 * オブジェクトのY軸方向の速さを返す。
	 *
	 * @return　Y軸方向の速さ
	 */
	public double getVy() {
		return vy;
	}

	/**
	 * オブジェクトの進んでいる角度を弧度で返す。<br>
	 * 右方向が角度0で右回りに増えます。
	 *
	 * @return　ラジアンで表されたオブジェクトの進行角度
	 */
	public double getRadian() {
		return Math.atan2(getVy(), getVx());
	}

	/**
	 * オブジェクトのベクトルをspeedとradianにセットする。
	 *
	 * @param speed　速度
	 * @param radian　ラジアンでの角度
	 */
	public void setVector(double speed, double radian) {
		setVx(speed * Math.cos(radian));
		setVy(speed * Math.sin(radian));
	}

	/**
	 * オブジェクトのベクトルにspeedとradianを足す。
	 *
	 * @param speed　足す速度
	 * @param radian　足す角度(ラジアン)
	 */
	public void addVector(double speed, double radian) {
		addVx(speed * Math.cos(radian));
		addVy(speed * Math.sin(radian));
		
	}

	/**
	 * 現在の角度のままで<br>
	 * オブジェクトの速さをspeedにセットする。
	 *
	 * @param speed　設定する速度
	 */
	public void setSpeed(double speed) {
		double radian = getRadian();
		setVx(speed * Math.cos(radian));
		setVy(speed * Math.sin(radian));
	}

	/**
	 * 現在の角度のままで<br>
	 * オブジェクトの速さにspeedを足す。
	 *
	 * @param speed　足す速度
	 */
	public void addSpeed(double speed) {
		double newSpeed = getSpeed() + speed;
		double radian = getRadian();
		setVx(newSpeed * Math.cos(radian));
		setVy(newSpeed * Math.sin(radian));
	}

	/**
	 * オブジェクトのX軸方向の速さをXspeedにセットする。
	 *
	 * @param Xspeed　設定するX軸方向の速度
	 */
	public void setVx(double Xspeed) {
		this.vx = Xspeed;
	}

	/**
	 * オブジェクトのX軸方向の速さにXspeedを足す。
	 *
	 * @param Xspeed　足すX軸方向の速さ
	 */
	public void addVx(double Xspeed) {
		setVx(getVx() + Xspeed);
	}

	/**
	 * オブジェクトのY軸方向の速さをYspeedにセットする。
	 *
	 * @param Yspeed　設定するY軸方向の速度
	 */
	public void setVy(double Yspeed) {
		this.vy = Yspeed;
	}

	/**
	 * オブジェクトのY軸方向の速さにYspeedを足す。
	 *
	 * @param Yspeed　足すY軸方向の速度
	 */
	public void addVy(double Yspeed) {
		setVy(getVy() + Yspeed);
	}

	/**
	 * 現在の速さのままで<br>
	 * オブジェクトの角度をradianにセットする。<br>
	 * 右が0度で右回り
	 *
	 * @param radian　設定するラジアンでの角度
	 */
	public void setRadian(double radian) {
		double speed = getSpeed();
		setVx(speed * Math.cos(radian));
		setVy(speed * Math.sin(radian));
	}

	/**
	 * 現在の速さのままで<br>
	 * オブジェクトの角度にradianを足す。<br>
	 * 右が0度で右回り
	 *
	 * @param radian 足すラジアンでの角度
	 */
	public void addRadian(double radian) {
		double speed = getSpeed();
		double newRadian = getRadian() + radian;
		setVx(speed * Math.cos(newRadian));
		setVy(speed * Math.sin(newRadian));
	}
	/**
	 * 現在の速度で動かす
	 */
	public void move() {
		addPos(getVx(), getVy());
	}
}
