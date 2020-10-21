package com.project.config.note.io.serializable;

import java.io.*;

public class ByteStreamTest {

	public static void main(String[] args) {

		File file1 = new File("E:\\io\\byteStreamRead.txt");
		File file2 = new File("E:\\io\\byteStreamWrite.txt");
		byteStreanColne(file1, file2);

	}

	public static void byteStreanColne(File file1, File file2) {

		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(file1);
			BufferedInputStream bis = new BufferedInputStream(in);

			out = new FileOutputStream(file2);
			BufferedOutputStream bos = new BufferedOutputStream(out);

			byte[] b = new byte[1];
			int len;
			while ((len = bis.read(b,0,b.length)) != -1) {

				bos.write(b);
				bos.flush();

			}
			System.out.println("��ȡ���");
			if (bos != null) {
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
