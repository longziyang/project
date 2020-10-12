package com.lzy.note.pack6.ioc;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class MyMainClass {

	public static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
	private static File[] files;
	private static String pack;

	public static void start(Class<?> clazz) {

		try {
			inversionOfControl(clazz.newInstance());
			dependencyInjection();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private MyMainClass(Object object) {

	}

	// ioc鍔熻兘
	public static <T> void inversionOfControl(Object object) {

		try {

			// 鑾峰彇褰撳墠椤圭洰涓嬬殑main鍑芥暟鐨勫寘鍚�
			pack = object.getClass().getPackage().getName().replace(".", "\\");

			// 鑾峰緱鐨勪负椤圭洰鍚嶇殑璺緞 鎵�浠ヨ鍔犱笂src鏂囦欢鍚� 鍚﹀垯鎵句笉鍒拌繖涓枃浠�
			// pack=test\pack6
			String path = new String("\\src\\" + pack);

			// 鑾峰彇椤圭洰鐨勭粷瀵硅矾寰� 杩欎釜璺緞鏄」鐩殑瀹屾暣璺緞
			String filePath = new File("").getCanonicalPath() + path;
			File file = new File(filePath);
			files = file.listFiles();

			for (File file2 : files) {
				if (file2.isDirectory()) {

					File[] file3 = file2.listFiles();
					for (File file4 : file3) {

						String classPath = file4.getAbsolutePath();
						String className = classPath.substring(classPath.indexOf(pack)).replace("\\", ".");

						try {

							if (className.contains("ioc")) {

								continue;
							}
							@SuppressWarnings("unchecked")
							Class<T> ss = (Class<T>) Class.forName(className.replace(".java", ""));
							Object obj = ss.newInstance();
							Annotation[] Annotation = obj.getClass().getAnnotations();
							for (Annotation annotation2 : Annotation) {

								if (annotation2.annotationType() == Controller.class) {

									String value = ss.getAnnotation(Controller.class).value();
									if (value == null) {

										String referenceName = initialsGetSmaller(file4.getName()).replace(".java", "");
										map.put(referenceName, obj);
									} else {

										map.put(value, obj);
									}
								} else if (annotation2.annotationType() == Service.class) {

									String value = ss.getAnnotation(Service.class).value();
									if (value == null) {

										String referenceName = initialsGetSmaller(file4.getName()).replace(".java", "");
										map.put(referenceName, obj);
									} else {

										map.put(value, obj);
									}
								} else if (annotation2.annotationType() == Dao.class) {

									String value = ss.getAnnotation(Dao.class).value();
									if (value == null) {

										String referenceName = initialsGetSmaller(file4.getName()).replace(".java", "");
										map.put(referenceName, obj);
									} else {

										map.put(value, obj);
									}
								} else if (annotation2.annotationType() == Component.class) {

									String value = ss.getAnnotation(Component.class).value();
									if (value == null) {

										String referenceName = initialsGetSmaller(file4.getName()).replace(".java", "");
										map.put(referenceName, obj);
									} else {

										map.put(value, obj);
									}
								}

							}

						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> void dependencyInjection() {

		for (File file2 : files) {

			if (file2.isDirectory()) {

				File[] file3 = file2.listFiles();
				for (File file4 : file3) {

					String classPath = file4.getAbsolutePath();
					String className = classPath.substring(classPath.indexOf(pack)).replace("\\", ".");

					if (className.contains("ioc")) {

						continue;
					}
					try {
						@SuppressWarnings("unchecked")
						Class<T> ss = (Class<T>) Class.forName(className.replace(".java", ""));
						Field[] fields = ss.getDeclaredFields();
						for (Field field : fields) {

							if (field.isAnnotationPresent(MyAutowired.class)) {

								String className1 = field.getType().getName();
								String fieldName=className1.substring(className1.lastIndexOf(".")+1);
								System.out.println(fieldName);
							}
						}

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

	public static String initialsGetSmaller(String letter) {

		String s1 = letter.substring(0, 1);
		String s = letter.substring(1);
		int ascll = (int) s1.toCharArray()[0];

		if (ascll < 91 && ascll > 64) {
			ascll += 32;

			char s2 = (char) ascll;
			return s2 + s;
		}

		return letter;
	}

}
