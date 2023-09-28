package me.akrem;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public boolean connect(byte[] address, int port) throws IOException {

        Server server = new Server(address, port);
        server.connect();

        server.getData(); // treat the data later

        return true; // for now
    }
    public static void interpretPacket(byte[] receivedData) {
        long cid = Utils.toLong(receivedData, 0, Utils.SIZEOF_LONG);
        byte temp = receivedData[8];
        float lo = Utils.toFloat(receivedData, 8);
        float la = Utils.toFloat(receivedData, 8 + Utils.SIZEOF_INT);
        long timeframe = Utils.toLong(receivedData, 8 + Utils.SIZEOF_INT * 2 + 1, Utils.SIZEOF_LONG);
        System.out.println(lo);
    }
}
