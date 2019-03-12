package crc;

public class CrcHelper {
    public static final int MIN_CRC12 = 12;
    public static final int MAX_CRC12 = 24;
    public static final int MIN_CRC16 = 16;
    public static final int MAX_CRC16 = 32;
    public static final int MIN_CRC16_REVERSE = 16;
    public static final int MAX_CRC16_REVERSE = 32;
    public static final int MIN_CRC32 = 32;
    public static final int MAX_CRC32 = 64;
    public static final int MIN_SDLC = 16;
    public static final int MAX_SDLC = 32;
    public static final int MIN_SDLC_REVERSE = 16;
    public static final int MAX_SDLC_REVERSE = 32;
    public static final int MIN_CRC_ITU = 16;
    public static final int MAX_CRC_ITU = 32;
    public static final int MIN_ATM_DQDB = 8;
    public static final int MAX_ATM_DQDB = 16;

    public static final int CRC12_DIVISOR_BITS_COUNT = 12;
    public static final int CRC16_DIVISOR_BITS_COUNT = 16;
    public static final int CRC16_REVERSE_DIVISOR_BITS_COUNT = 16;
    public static final int CRC32_DIVISOR_BITS_COUNT = 32;
    public static final int SDLC_DIVISOR_BITS_COUNT = 16;
    public static final int SDLC_REVERSE_DIVISOR_BITS_COUNT = 16;
    public static final int CRC_ITU_DIVISOR_BITS_COUNT = 16;
    public static final int ATM_DQDB_DIVISOR_BITS_COUNT = 8;

    public static final int[] CRC12_DIVISOR = {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1};
    public static final int[] CRC16_DIVISOR = {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0};
    public static final int[] CRC16_REVERSE_DIVISOR = {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1};
    public static final int[] CRC32_DIVISOR = {1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1,
            0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1};
    public static final int[] SDLC_DIVISOR = {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    public static final int[] SDLC_REVERSE_DIVISOR = {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0,
            0};
    public static final int[] CRC_ITU_DIVISOR = {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    public static final int[] ATM_DQDB_DIVISOR = {1, 0, 0, 0, 0, 0, 1, 1};
}
