package de.greenrobot.common.hash;

import org.junit.Assert;
import org.junit.Test;

import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class CombinedChecksumTest extends AbstractChecksumTest{
    public CombinedChecksumTest() {
        super(new CombinedChecksum(new CRC32(), new Adler32()));
    }

    @Test
    public void testCombinedBasics() {
        long emptyValue = checksum.getValue();
        for (int i = 0; i < 256; i++) {
            checksum.update(i);
            long value = checksum.getValue();

            long crc32 = value & 0xffffffff;
            long adler32 = (value >>> 32) & 0xffffffff;

            Assert.assertNotEquals(crc32, adler32);
            Assert.assertNotEquals(0, adler32);
            Assert.assertNotEquals(0, crc32);
        }

        checksum.reset();
        Assert.assertEquals(emptyValue, checksum.getValue());
    }


}