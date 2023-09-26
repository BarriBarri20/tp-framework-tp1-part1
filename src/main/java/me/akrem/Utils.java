package me.akrem;

public class Utils {

    public static final int SIZEOF_LONG = Long.SIZE / Byte.SIZE;
    public static final int SIZEOF_INT = Integer.SIZE / Byte.SIZE;

    /**
     * Write a single byte out to the specified byte array position.
     *
     * @param bytes  the byte array
     * @param offset position in the array
     * @param b      byte to write out
     * @return incremented offset
     */
    public static int putByte(byte[] bytes, int offset, byte b) {
        bytes[offset] = b;
        return offset + 1;
    }

    public static void putShort(byte[] bytes, int offset, short val) {
        bytes[offset + 1] = (byte) val;
        val >>= 8;
        bytes[offset] = (byte) val;
    }

    public static int putBytes(byte[] tgtBytes, int tgtOffset, byte[] srcBytes, int srcOffset,
                               int srcLength) {
        System.arraycopy(srcBytes, srcOffset, tgtBytes, tgtOffset, srcLength);
        return tgtOffset + srcLength;
    }

    public static int putFloat(byte[] bytes, int offset, float f) {
        return putInt(bytes, offset, Float.floatToRawIntBits(f));
    }

    public static int putInt(byte[] bytes, int offset, int val) {
        for (int i = offset + 3; i > offset; i--) {
            bytes[i] = (byte) val;
            val >>>= 8;
        }
        bytes[offset] = (byte) val;
        return offset + SIZEOF_INT;
    }

    public static int putLong(byte[] bytes, int offset, long val) {
        for (int i = offset + 7; i > offset; i--) {
            bytes[i] = (byte) val;
            val >>>= 8;
        }
        bytes[offset] = (byte) val;
        return offset + SIZEOF_LONG;
    }


    public static byte[] dataSerializer(long cid, byte temperature, float longitude, float latitude, float timeframe) {

        byte[] bytes = new byte[25];
        int offset = 0;
        offset = putLong(bytes, offset, cid);
        offset = putFloat(bytes, offset, longitude);
        offset = putFloat(bytes, offset, latitude);
        offset = putByte(bytes, offset, temperature);
        putFloat(bytes, offset, timeframe);

        return bytes;
    }

    public static short fletcher16(byte[] data) {
        int sum1 = 0x00;
        int sum2 = 0x00;
        for (byte b : data) {
            sum1 = (sum1 + b) % 128;
            sum2 = (sum2 + sum1) % 128;
        }
        return (short) ((sum2 << 8) | sum1);
    }

    public static short createChecksum(byte[] data) {
        return fletcher16(data);
    }

    // This part of code is used to deserialize the data of the packet byte[]

    /**
     * Put a float value out to the specified byte array position. Presumes float encoded as IEEE 754
     * floating-point "single format"
     *
     * @param bytes  array to convert
     * @param offset offset into array
     * @return Float made from passed byte array.
     */
    public static float toFloat(byte[] bytes, int offset) {
        return Float.intBitsToFloat(toInt(bytes, offset, SIZEOF_INT));
    }

    public static int toInt(byte[] bytes, int offset, int length) {
        int n = 0;
        for (int i = offset; i < (offset + length); i++) {
            n <<= 8;
            n ^= bytes[i] & 0xFF;
        }
        return n;
    }

    public static long toLong(byte[] bytes, int offset, int length) {
        long l = 0;
        for (int i = offset; i < offset + length; i++) {
            l <<= 8;
            l ^= bytes[i] & 0xFF;
        }
        return l;
    }

    public static short toShort(byte[] bytes, int offset) {
        short n = 0;
        n = (short) ((n ^ bytes[offset]) & 0xFF);
        n = (short) (n << 8);
        n ^= (short) (bytes[offset + 1] & 0xFF);
        return n;
    }
}
