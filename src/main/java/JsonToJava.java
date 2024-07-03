import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JsonToJava {

    public static String prettyPrintUsingGson(String uglyJsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        JsonElement jsonElement = JsonParser.parseString(uglyJsonString);
        String prettyJsonString = gson.toJson(jsonElement);
        return prettyJsonString;
    }

    public static void main(String[] args) throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn  = null;

        List<CustomeRDetails> objList = new ArrayList<>();

        JSONArray jArray = new JSONArray();
        JSONObject jo = new JSONObject();
        Gson  gs = new Gson();

       conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","Airjordan0@");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");


        while(rs.next())
       {
          //cObj.  //rs.getString(1);
           CustomeRDetails cObj = new CustomeRDetails();
           cObj.setCourseName(rs.getString(1));
           cObj.setPurchaseDate(rs.getString(2));
           cObj.setAmount(rs.getInt(3));
           cObj.setLocation(rs.getString(4));
           objList.add(cObj);
       }

                for(CustomeRDetails c: objList)
                {


                    String jsonPart =  gs.toJson(c);
                    jArray.add(jsonPart);

                    // ObjectMapper oMap = new ObjectMapper();
                    // oMap.writerWithDefaultPrettyPrinter().writeValue(new File("D:\\SDET\\src\\main\\java\\customerInfo.json"),jo);

                }

            jo.put("data",jArray);

                String escapedSTRING = StringEscapeUtils.unescapeJson(jo.toJSONString());
                String finalString = escapedSTRING.replace("\"{","{").replace("}\"","}");

       // System.out.println(finalString);

        Files.write(Paths.get("D:\\SDET\\src\\main\\java\\customerInfoFinal.json"),prettyPrintUsingGson(finalString) .getBytes());



            /*  FileWriter fileWrite = new FileWriter("D:\\SDET\\src\\main\\java\\customerInfoFinal.json");
              fileWrite.write(prettyPrintUsingGson(jo.toJSONString()));
              fileWrite.flush();
              fileWrite.close();*/

           //System.out.println(jo.toJSONString());


        conn.close();

    }
}
