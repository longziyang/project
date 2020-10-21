package note.arithmetic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapTest {

	public static void main(String[] args) {

		String[] str = { "sgsfd", "adc", "dfa", "dfsdgdf", "oj", "b", "bfaskj", };

		Map<String, Integer> map = new HashMap<>();
		int len = str[0].length();
		map.put(str[0], len);

		for (int i = 1; i < str.length; i++) {

			if (str[i].length() <= len) {

				map.clear();
				len = str[i].length();
				map.put(str[i], len);

			}else if(str[i].length() == len) {
				
				map.put(str[i], str[i].length());
			}

		}

		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		System.out.println(map.get("b"));
	}

}
