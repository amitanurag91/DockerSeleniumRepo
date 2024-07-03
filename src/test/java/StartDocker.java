import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class StartDocker {


    @Test
    public void startDockerFile() throws IOException, InterruptedException {
        Runtime run = Runtime.getRuntime();
        run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerDown.bat");

      /*  Thread.sleep(4000);
        run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerup.bat");
        String file = "output.txt";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 30);
        Long stopNow = cal.getTimeInMillis();
        boolean flag = false;
        Thread.sleep(2000);
        while (System.currentTimeMillis() < stopNow) {
            if(flag)
            {
                break;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();

            while (currentLine != null && !flag) {
                if (currentLine.contains("Event bus ready")) {
                    System.out.println("selenium nodes ready to use");
                    flag = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();



        }

        Assert.assertTrue(flag);
        Thread.sleep(3000);
        run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerScaleChrome.bat");

         Files.delete(Paths.get("D:\\SDET\\output.txt"));
    }*/
    }
}
