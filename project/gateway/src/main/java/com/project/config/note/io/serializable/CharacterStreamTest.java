package com.project.config.note.io.serializable;

import java.io.*;

public class CharacterStreamTest {

	public static void main(String[] args) {

		File file1 = new File("E:\\io\\characterStreamRead.txt");
		File file2 = new File("E:\\io\\characterStreamWrite.txt");
		characterStreamClone(file1, file2);

	}

	public static void characterStreamClone(File file1, File file2) {

		FileReader fr = null;
		FileWriter fw = null;
		try {

			fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);

			fw = new FileWriter(file2);
			BufferedWriter bw = new BufferedWriter(fw);

			// ת����
//			FileOutputStream out = new FileOutputStream(file2);
//			OutputStreamWriter fo = new OutputStreamWriter(out, "UTF-8");

			while (br.ready()) {

				bw.write(br.readLine());
				bw.newLine();
				bw.flush();
				//fo.write(br.read());
				//fo.flush();

			}
			System.out.println("��ȡ���");
			if (bw != null) {
				bw.close();
			}
//			if (fo != null) {
//				fo.close();
//			}
			if (br != null) {
				br.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
