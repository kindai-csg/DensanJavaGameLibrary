package densan.s.game.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * テキスト入出力用クラス
 * @author yazirusi_s
 *
 */
public class TextIO {

	/**
	 * ソース外のファイルのパスを指定して中身を文字列で返す<br>
	 * jar化しているならjarの外、Eclipseならsrcの外のファイルを読み込むときに使う<br>
	 * ファイルが存在しなかったり読み込めない場合はnullを返す
	 * @param filepath ファイルのパス
	 * @return 読み込んだ文字列
	 */
	public static String readOutside(String filepath) {
		if (!checkBeforeReadfile(new File(filepath))) {
			System.err.println("テキストファイル " +filepath+  " を読み込めませんでした");
			return null;
		}
		File file = new File(filepath);

		return readFile(file);


	}
	/**
	 * ソース内のファイルのパスを指定して中身を文字列で返す<br>
	 * jar化しているならjarの中、Eclipseならsrcの中のファイルを読み込むときに使う<br>
	 * ファイルが存在しなかったり読み込めない場合はエラーになる
	 * @param filepath ファイルのパス
	 * @return 読み込んだ文字列
	 */
	public static String readInside(String filepath) {

		String ret = "";

		InputStream is = ClassLoader.getSystemResourceAsStream(filepath);
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String line;

			int index = 0;

			//int playerNum = 0;

			while ((line = in.readLine()) != null) {
				if (index != 0) ret += "\n";
				ret += line;
				index++;
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * ファイルを読み込んで文字列で返す
	 * @param file - 読み込むファイル
	 * @return - ファイルの文字列
	 */
	private static String readFile(File file) {
		String ret = "";
		try{
			// test.txtからファイルを読み込んでテキストエリアに表示
			//String parent = System.getProperty("java.class.path");

			//InputStream is = ScoreManager.class.getClassLoader().getResourceAsStream(FILE_NAME);
			BufferedReader in = new BufferedReader( 
					new InputStreamReader(new FileInputStream(file),"UTF-8"));

			//BufferedReader in = new BufferedReader( new FileReader(filepath));
			String line;

			int index = 0;

			//int playerNum = 0;
			while ((line = in.readLine()) != null) {
				if (index != 0) ret += "\n";
				ret += line;
				index++;
			}
			in.close();
		} catch (IOException e) {   // 入出力エラーをつかまえる
			System.err.println(e);  // エラーメッセージ出力
			System.exit(1);         // 終了コード 1 で終了する
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
	/**
	 * ソース外のファイルのパスを指定して書き込む<br>
	 * jar化しているならjarの外、Eclipseならsrcの外のファイルに書き込むときに使う<br>
	 * ファイルが存在しなかったりない場合はエラー
	 * @param filepath ファイルのパス
	 * @param text 書き込む文字列
	 * @throws IOException ファイルの書き込みエラー
	 */
	public static void writeOutside(String filepath, String text) throws IOException {

		// test.txtからファイルを読み込んでテキストエリアに表示
		//String parent = System.getProperty("java.class.path");

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filepath)),"UTF-8"));
		//テキストエリアの中身を読み込む
		String[] lines  = text.split("\n",-1);

		for (int i=0; i < lines.length; i++){
			bw.write(lines[i]);
			if (i < lines.length-1) {
				bw.newLine();
			}

		}
		bw.close();

	}
	/**
	 * 指定のパスのファイルが存在するか、書き込み可能かを返す
	 * @param filepath -  ファイルパス
	 * @return 存在して書き込み可能ならtrue
	 */
	public static boolean isWritefile(String filepath){
		File file = new File(filepath);
		if (file.exists()){
			if (file.isFile() && file.canWrite()){
				return true;
			}
		}

		return false;
	}
	/**
	 * ファイルが存在するかチェックする
	 * @param file
	 * @return
	 */
	private static boolean checkBeforeReadfile(File file){
		if (file.exists()){
			if (file.isFile() && file.canRead()){
				return true;
			}
		}

		return false;
	}
}
