package me.akrem;


public class Client {
    public static void interpretPacket(byte[] receivedData) {
        long cid = Utils.toLong(receivedData, 0, Utils.SIZEOF_LONG);
        byte temp = receivedData[Utils.SIZEOF_LONG + 1];
        float lo = Utils.toFloat(receivedData, 6);
        float la = Utils.toFloat(receivedData, 6 + Utils.SIZEOF_INT);
        float timeframe = Utils.toLong(receivedData, 6 + Utils.SIZEOF_INT * 2, Utils.SIZEOF_LONG);
        System.out.println("cid: " + cid + "\n");
        System.out.println("temp: " + temp + "\n");
        System.out.println("lo: " + lo + "\n");
        System.out.println("la: " + la + "\n");
        System.out.println("timeframe: " + timeframe + "\n");
    }
}
