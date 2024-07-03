
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import org.testng.Assert;

public class DockerUtil {

    private static  Runtime run = Runtime.getRuntime();
    private static final String dockerUpLogString ="selenium-grid-hub entered RUNNING state";
    private static final String dockerDownLogString ="Shutdown complete";
    private static  Calendar cal = Calendar.getInstance();

    public static void dockerCommandPerform(DockerCommandType dockerCommand ) throws IOException, InterruptedException {

        switch (dockerCommand) {

            case DOCKERUP : {
                run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerup.bat");
                String file = "output.txt";
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
                        if (currentLine.contains(dockerUpLogString)) {
                            System.out.println("selenium nodes ready to use");
                            flag = true;
                            break;
                        }
                        currentLine = reader.readLine();
                    }
                    reader.close();
                }
                Assert.assertTrue(flag);
                break;
            }

            case DOCKERDOWN: {
                run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerDown.bat");
                String file = "output.txt";
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
                        if (currentLine.contains(dockerDownLogString)) {
                            System.out.println("Docker containers are stopped");
                            flag = true;
                            break;
                        }
                        currentLine = reader.readLine();
                    }
                    reader.close();
                }
                Assert.assertTrue(flag);
                Thread.sleep(9000);
                Files.deleteIfExists(Paths.get("D:\\SDET\\output.txt"));
                break;

            }
            case DOCKERSCALE_CHROME: {
                run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerScaleChrome.bat");
                break;
            }
        }



       /* Thread.sleep(4000);
        run.exec("cmd /c start D:\\SDET\\src\\main\\resources\\dockerup.bat");*/
      /*  String file = "output.txt";
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

        Files.delete(Paths.get("D:\\SDET\\output.txt"));*/
    }
}


