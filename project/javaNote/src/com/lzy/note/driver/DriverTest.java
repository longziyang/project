package com.lzy.note.driver;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.lzy.note.Student;

public class DriverTest {

	public static void main(String[] args) {

		Student student = (Student) getStudentById(Student.class, 7);
		System.out.println(student.toString());

	}

	public static Object getStudentById(Class<?> clazz, int id) {

		Connection connection = getConnection();
		try {
			Statement statemen = connection.createStatement();
			String sql = "select * from " + clazz.getSimpleName().toLowerCase() + " where id =" + id;
			ResultSet resultSet = statemen.executeQuery(sql);
			ResultSetMetaData metaDate = resultSet.getMetaData();
			int columnCount = metaDate.getColumnCount();

			Field[] fields = clazz.getDeclaredFields();
			Object object = null;
			try {
				object = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (resultSet.next()) {

				for (int i = 1; i <= columnCount; i++) {

					fields[i - 1].setAccessible(true);
					if (fields[i - 1].getName().equals(metaDate.getColumnName(i))) {

						try {
							fields[i - 1].set(object, resultSet.getObject(i));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}

			return object;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static Connection getConnection() {

		Properties properties = new Properties();
		InputStream inputStream = DriverTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");

		try {

				Class.forName(driver);
				return DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
