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
            long timeframe = rd.nextLong();
            CardReader cardReaderInfos = new CardReader(temp[0], longitude, latitude, timeframe);

            Packet packet;
            packet = new Packet(cardReaderInfos);
            System.out.println(packet);
        }
    }
}
