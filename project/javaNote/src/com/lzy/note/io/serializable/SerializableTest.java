package com.lzy.note.io.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializableTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name = "lzy";
	transient int year = 235;
	String color = "yello";

	public static void main(String[] args) {
		// serializableTestToTxt();
		SerializableTest serializableTest = (SerializableTest) getObject();
		System.out.println(serializableTest.name + serializableTest.year + serializableTest.color);
	}

	public static void serializableTestToTxt() {

		OutputStream out;
		try {
			out = new FileOutputStream(new File("E:\\io\\io.txt"));
			ObjectOutputStream obs = null;
			try {
				obs = new ObjectOutputStream(out);

				obs.writeObject(new SerializableTest());

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
				if (obs != null) {
					try {
						obs.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Object getObject() {

		InputStream in;
		try {
			in = new FileInputStream(new File("E:\\io\\io.txt"));
			try {
				ObjectInputStream ois = new ObjectInputStream(in);
				try {
					return ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (ois != null) {
						ois.close();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
