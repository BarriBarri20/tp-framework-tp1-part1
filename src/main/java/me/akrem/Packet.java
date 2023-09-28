package me.akrem;

import java.util.Arrays;

public class Packet {

    public byte[] utilData;
    public short checksum; // 2 bytes

    public Packet(CardReader data) {
        setUtilData(data);
        setChecksum();
    }

    public void setUtilData(CardReader data) {
        this.utilData = data.createData();
    }

    public void setChecksum() {
        this.checksum = Utils.createChecksum(this.utilData);
    }

    public byte[] packetSerializer() {
        byte[] data = new byte[27];
        int offset = Utils.putBytes(data, 0, this.utilData, 0, 25);
        Utils.putShort(data, offset, this.checksum);
        return data;
    }

    /**
     * @param data represents the received data
     * @return 1 if the checksum received is verified
     * 0 if not.
     */
    public byte[] packetDeserializer(byte[] data) {
        int offset = Utils.putBytes(this.utilData, 0, data, 0, 25);
        this.checksum = Utils.toShort(data, offset);
        short calculatedChecksum = Utils.createChecksum(this.utilData);
        if ((calculatedChecksum - this.checksum) == 0) {
            return this.utilData;
        }
        return new byte[1];
    }

    @Override
    public String toString() {

        Client.interpretPacket(this.packetDeserializer(this.packetSerializer()));

        return "Packet{" +
                "utilData = " + Arrays.toString(utilData) +
                ", checksum = " + checksum +
                ", checksumIsCorrect ? = " + Arrays.toString(this.packetDeserializer(this.packetSerializer())) +
                '}';

    }
}
