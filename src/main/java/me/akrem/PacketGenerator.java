package me.akrem;

import java.util.Random;

class PacketGenerator {
    public static void generatePacket(int numberOfPackets) {
        for (int i = 0; i < numberOfPackets; i++) {

            Random rd = new Random();

            byte[] temp = new byte[1];
            rd.nextBytes(temp);

            float longitude = rd.nextFloat();
            float latitude = rd.nextFloat();
            long leftLimit = 100L;
            long rightLimit = 100000L;
            long timeframe = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            CardReader cardReaderInfos = new CardReader(temp[0], longitude, latitude, timeframe);
            cardReaderInfos.setCID();
            Packet packet;
            packet = new Packet(cardReaderInfos);
            System.out.println(packet);
        }
    }
}
