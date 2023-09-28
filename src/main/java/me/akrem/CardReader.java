package me.akrem;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class CardReader {

    public long cid;
    public byte temperature; // 1 byte
    public float longitude; // 4 bytes
    public float latitude; // 4 bytes
    public long timeframe; // 8 bytes

    public void setCID() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a card id : ");
        this.cid = sc.nextLong();
    }

    public CardReader(byte temperature, float longitude, float latitude, long timeframe) {
        setCID();
        this.temperature = temperature;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeframe = timeframe;
    }


    public byte[] createData() {
        System.out.println("cid " + cid + " temperature : " + temperature + " longitude : " + longitude + " latitude : " + latitude + " time : " + timeframe);
        System.out.println(Arrays.toString(Utils.dataSerializer(cid, temperature, longitude, latitude, timeframe)));
        return Utils.dataSerializer(cid, temperature, longitude, latitude, timeframe);
    }

}

