package com.self.music.common.utils.time;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class UtcNtpTime {
    public static OffsetDateTime utcNowOffsetDateTime() throws IOException {
        return ntpNowInstant()
                .atOffset(ZoneOffset.UTC);
    }

    private static Instant ntpNowInstant() throws IOException {
        NTPUDPClient client = new NTPUDPClient();
        client.setDefaultTimeout(10_000);

        // can choose another reliable NTP server
        InetAddress inetAddress = InetAddress.getByName("pool.ntp.org");

        TimeInfo timeInfo = client.getTime(inetAddress);
        long returnTime = timeInfo.getReturnTime(); // timeInfo.getMessage().getTransmitTimeStamp().getTime();
        return Instant.ofEpochMilli(returnTime);
    }

    static {
        try {
            System.out.printf("""
                    ntpNowInstant(): %s,
                    utcNowOffsetDateTime(): %s
                    """,
                    ntpNowInstant(),
                    utcNowOffsetDateTime()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}