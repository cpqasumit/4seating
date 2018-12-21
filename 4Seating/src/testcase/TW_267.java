package testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import common.Setup;
import common.*;

public class TW_267 extends Setup {

	public static ArrayList<String> line = new ArrayList<String>();

	@Test
	public static void CheckColor() throws InterruptedException {

		try {
			line = Common.GetURLList("TW_267", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] color = { "Mesa Charcoal", "Mesa Desert", "Mesa White", "Mesa Coal", "Mesa Parquet", "Mesa Pewter", "Mesa Terra",
				"Mesa Truffle", "Taos Kiwi", "Taos New Canary", "Taos Periwinkle", "Taos Tomato", "Taos Violet" };
		for (int i = 0; i < line.size(); i++) {
			String URL = line.get(i);
			driver.get(URL);
			Thread.sleep(2000);
			List<WebElement> Elements = driver.findElements(By.xpath("//div[@id='colorbx']/dl[1]/dd[1]/ul/li"));
			int s = Elements.size();
			String ColorValue = null;
			for (int i1 = 1; i1 <= s; i1++) {
				String Colortitle = driver
						.findElement(By.xpath("//div[@id='colorbx']/dl[1]/dd[1]/ul/li[" + i1 + "]/a/img"))
						.getAttribute("title");
				Colortitle = Colortitle.substring(0, Colortitle.length() - 3);
				for (int j = 1; j < color.length; j++) {
					if (color[j].contains(Colortitle)) {
						if (ColorValue != null) {
							ColorValue = ColorValue.concat(", " + Colortitle);
						} else {
							ColorValue = Colortitle;
						}
					}
				}
			}
			if (ColorValue != null)
			{
				log.warn("\t" + URL + "\t" + ColorValue);
			}
			else
			{
				log.info("\t" + URL + "\t" + "Data is updated as per requirement");
			}
		}
	}
}
