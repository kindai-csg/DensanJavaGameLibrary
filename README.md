# wiki
https://www65.atwiki.jp/kindaidensan/
# DensanJavaGameLibrary
電算研のjava製ゲームライブラリです

## 更新情報  
### 01_01b  
- offsetを使い画面をスクロールするためのクラスScrollの追加
### 01_01a	
- キーの押された瞬間を検出するKeyInput.isPressメソッドを1フレーム内で複数回呼ばれても
		　大丈夫なように変更。ただし超低確率で同じ結果を返さない(詳細はjavadocに)

### 01_00c	
- DrawerのdrawCircleメソッドのrange引数の説明が「半径」ではなく「直径」になっていたので修正

### 01_00b	
- Javadocが表示されないようになっていたので表示されるように変更

### 01_00a	
- 正式版リリース
- 正直変えすぎて変更点覚えてないのでここが開始地点だと思ってください
- 実行環境をjava8に引き上げ
- BasicPlayerを入れなくてもいいように変更
	- つまりビルドパスに追加するのが1つだけですみます
- テキストをロードするクラスtext.textIOを追加
- MP3,WaveだったのをBGM,SEに変更(メソッド名とかそのへん)



### 0_16a 
- Graphicsクラスを独自のDrowerクラスに変更
- 非推奨だったAGameObject等を削除
- その他

### 0_12a 
- CalcのgetRadianメソッドをPointでも利用できるように修正
- GameVectorBaseのaddSpeedメソッドのバグを修正
- ImageLoaderのキャッシュをクリア出来るようにし、
上限もちょっと増加、上限に達した場合自動でキャッシュをクリアするように。

### 0_11b 
- 画像の回転描画の高速化

### 0_11a 
- objectパッケージのクラスの名前を変更
- 「A〜〜〜Object」から「〜〜〜objectBase」に。
	- (一応古いバージョンも残してるけどCalcとかは新しい方にしか対応してないしほぼ非推奨説明用)
- ImageObjectBaseにimageのセッターゲッターを追加
- Calcの当たり判定が矩形同士の重なり検出になってなかったのを修正
	  	前のも別の名前で残した

### 0_10a 
- GameManageListにジェネリクスを追加してAGameObjectを継承していればなんでも行けるようにした。
- MouseInputでマウスの左右クリックの種類を取得出来るようにした。
- DrawingToolクラスを追加、画像の回転描画が可能に
- AImageObjectクラスで画像の回転をサポート

### 0_04a 
- MouseInput:マウスホイールでバグってた問題を修正
	- その代わりgetWheelRotation()で取得できる回転数の精度が落ちた

### 0_03a 
- 初期版
