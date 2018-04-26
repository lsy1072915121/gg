package com.liushiyao.gg.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.liushiyao.gg.pojo.Route;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
public class GGService {


  private final static String GPS_CONVERT_URL = "http://restapi.amap.com/v3/assistant/coordinate/convert?";
  private final static String GAODE_MAP_KEY = "bd1aba3197261ae2b263472ed47aae9b";


  public Route parse(String data) {

    Route route = new Route();
    ArrayList<double[]> list = new ArrayList<double[]>();
    String[] arrStr = data.split(";");
    String parseData = "";
    for (int i = 0; i < arrStr.length; i++) {
      String temp[] = arrStr[i].split(",");
      double[] arrDouble = new double[2];
      arrDouble[0] = angleToRad(Double.valueOf(temp[0]));
      arrDouble[1] = angleToRad(Double.valueOf(temp[1]));
      parseData = parseData + arrDouble[0] + "," + arrDouble[1] + ";";
    }
    String gaodeParseResult = parseGPSData(parseData);
    String[] arrStr2 = gaodeParseResult.split(";");
    System.out.println(gaodeParseResult.toString());
    for (int i = 0; i < arrStr2.length; i++) {
      String temp[] = arrStr2[i].split(",");
      double[] arrDouble = new double[2];
      arrDouble[0] = Double.valueOf(temp[0]);
      arrDouble[1] = Double.valueOf(temp[1]);
      list.add(arrDouble);
    }
    route.setName("轨迹");
    route.setPath(list);
    return route;
  }


  public double angleToRad(double num) {
    int angle = 0;
    int min = 0;
    double sec = 0.0;
    angle = (int) (num / 100);
    min = (int) (num % 100);
    sec = (num - (int) num) * 60;
    double temp = 0.0;
    temp = angle + min / 60.0 + sec / 3600.0;
    temp = (double) Math.round(temp * 100000) / 100000;
    return temp;
  }

  @Test
  public void Te() {

    double a = angleToRad(11312.43033);
    double b = angleToRad(2304.29057);
    System.out.println(a);
    System.out.println(b);

  }

  //http://restapi.amap.com/v3/assistant/coordinate/convert?locations=116.481499,39.990475&coordsys=gps&output=xml&key=您申请的key
  public static String parseGPSData(String data) {

    try {
      HttpClient httpClient = new DefaultHttpClient();
      HttpGet httpGet = new HttpGet(
          GPS_CONVERT_URL + "&key=" + GAODE_MAP_KEY + "&coordsys=gps" + "&locations=" + data);
      HttpResponse httpResponse = httpClient.execute(httpGet);
      if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String result = EntityUtils.toString(httpResponse.getEntity());
        JsonElement jsonElement = new JsonParser().parse(result);
        return jsonElement.getAsJsonObject().get("locations").getAsString();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;

  }

  @Test
  public void TestGPSParse() {

    String result = parseGPSData("1113.20717,23.07151;13.20717,23.07151;");
    System.out.println(result);

  }


}
