package me.akrem;

import java.io.Serializable;

public class CardReader implements Serializable {

    public long cid;
    public byte temperature; // 1 byte
    public float longitude; // 4 bytes
    public float latitude; // 4 bytes
    public long timeframe; // 8 bytes

    public CardReader(byte temperature, float longitude, float latitude, long timeframe) {
        this.temperature = temperature;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeframe = timeframe;
    }

    public void setCID() {
        this.cid = System.currentTimeMillis();
    }

    public byte[] createData() {
        System.out.println("cid " + cid + " temperature : " + temperature + " longitude : " + longitude + " latitude : " + latitude + " time : " + timeframe);
        return Utils.dataSerializer(cid, temperature, longitude, latitude, timeframe);
    }

}

