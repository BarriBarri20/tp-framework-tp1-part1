package me.akrem;

import java.util.Arrays;

public class CardReader {

    public long cid;
    public static long nextCid = 0L;
    public byte temperature; // 1 byte
    public float longitude; // 4 bytes
    public float latitude; // 4 bytes
    public long timeframe; // 8 bytes
    public void setCID() {
        this.cid = nextCid;
        nextCid += 1;
    }

    public CardReader(byte temperature, float longitude, float latitude, long timeframe) {
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

