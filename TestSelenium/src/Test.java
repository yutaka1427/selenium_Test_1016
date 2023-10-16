import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    public static void main(String[] args) {
        // ChromeDriverのパスを設定
        System.setProperty("webdriver.chrome.driver", "C:\\pleiades\\2023-06\\workspace\\TestSelenium\\chromedriver-win64\\chromedriver.exe");

        // CSVファイルのパスを指定
        String csvFilePath = "C:\\pleiades\\2023-06\\workspace\\TestSelenium\\up_csv\\urls.csv"; // CSVファイルのパス

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 各行からURLを取得
                String url = line.trim();

                // WebDriverのインスタンスを作成
                WebDriver driver = new ChromeDriver();

                try {
                    // 取得したURLにアクセス
                    driver.get(url);

                    // タイトルを取得して表示
                    WebElement titleElement = driver.findElement(By.cssSelector("h1.item__name"));
                    String pageTitle = titleElement.getText();
                    System.out.println("ページタイトル: " + pageTitle);

                    // 価格を取得して表示
                    WebElement itemValueElement = driver.findElement(By.cssSelector("span.item__value"));
                    String itemValueText = itemValueElement.getText();
                    System.out.println("価格: " + itemValueText);

                    // すべての画像を取得
                    List<WebElement> imageElements = driver.findElements(By.cssSelector("img.sp-image"));

                    // 画像をダウンロードして保存
                    for (int i = 0; i < imageElements.size(); i++) {
                        WebElement imageElement = imageElements.get(i);
                        String imageUrl = imageElement.getAttribute("src");

                        try {
                            URL imageURL = new URL(imageUrl);
        BufferedImage image = ImageIO.read(imageURL);

        // 保存先のディレクトリ
        String outputDirectory = "output";
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        // 連番をつけてファイル名を生成
        int fileNumber = i + 1;
        String baseFileName = "image";
        String fileExtension = "jpg";
        String fileName = baseFileName + "_" + fileNumber + "." + fileExtension;
        File outputFile = new File(outputDirectory, fileName);

        // 同名のファイルが存在する場合、連番を増やして避ける
        while (outputFile.exists()) {
            fileNumber++;
            fileName = baseFileName + "_" + fileNumber + "." + fileExtension;
            outputFile = new File(outputDirectory, fileName);
        }

        // 画像を保存
        ImageIO.write(image, "jpg", outputFile);
        System.out.println("画像をダウンロードして '" + fileName + "' に保存しました。");


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    // WebDriverを終了しブラウザを閉じる
                    driver.quit();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



///////////////////////ここから/////////////////////////////

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Test{
//    public static void main(String args[])
//    {
//        // ChromeDriverのパスを設定
//        System.setProperty("webdriver.chrome.driver",
//        		"C:\\pleiades\\2023-06\\workspace\\TestSelenium\\chromedriver-win64\\chromedriver.exe");
//
//        // WebDriverのインスタンスを作成
//        WebDriver driver = new ChromeDriver();
//
//        // 開きたいサイトのURLを引数に指定
//        driver.get("https://www.yahoo.co.jp/");
//    }
//
//}
///////////////////////ここまで/////////////////////////////

/*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://www.chivsp.com/");
            
            // ウェブページ上の特定の要素を取得
            WebElement element = driver.findElement(By.tagName("h1"));

            // 要素のテキストを取得して表示
            System.out.println("Webページのタイトル: " + driver.getTitle());
            System.out.println("要素のテキスト: " + element.getText());
        } finally {
            driver.quit();
        }
    }
}
*/


/*
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test
{
    public static void main(String args[])
    {
        // ChromeDriverのパスを設定
        System.setProperty("webdriver.chrome.driver", "chromeDriver/chromedriver.exe");

        // WebDriverのインスタンスを作成
        WebDriver driver = new ChromeDriver();

        // 開きたいサイトのURLを引数に指定
        driver.get("http://www.chivsp.com/");

        // お問い合わせリンクをクリック
        driver.findElement(By.xpath("//*[@id='navigation']/ul/li[5]/a")).click();

        // お問い合わせボタンをクリック
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/p[2]/a")).click();

        // 入力
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/form/div[1]/div/input")).sendKeys("会社テスト");

        // チェックボックスにチェックを入れる
        driver.findElement(By.xpath("//*[@id=\"check\"]")).click();

        // ボタンを押下する。
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
    }
}*/