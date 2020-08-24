package rwbykit.flowable.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 * id生成工具类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午3:10:17
 * @version 1.0
 */
public class Ids {
    
    private final static SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(0, 0);
    
    private static String randomString = "";
    
    static {
        String ip;
        long macAddress = 0L;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            macAddress = Long.parseLong(getLocalMac().replace("-", ""), 16) % 31;
        } catch (Exception e) {
            ip = "127.0.0.1";
        }
        long randomValue = ipToLong(ip);
        int randomNum = ThreadLocalRandom.current().nextInt(1000);
        randomValue = randomNum + randomValue;
        randomValue = randomValue + macAddress;
        randomValue = randomValue + ThreadLocalRandom.current().nextInt(1000);
        randomString = String.valueOf(randomValue);
    }
    
    public final static String getId() {
        Long idValue = idWorker.nextId();
        String id = Long.toString(idValue);
        StringBuffer sb = new StringBuffer();
        if (id.startsWith("-")) {
            sb.append("Z").append(randomString).append(id.substring(1));
        } else {
            sb.append("A").append(randomString).append(id);
        }
        return sb.toString();
    }
    
    public final static String getNumberId() {
        Long idValue = idWorker.nextId();
        String id = Long.toString(idValue);
        StringBuffer sb = new StringBuffer();
        if (id.startsWith("-")) {
            sb.append("9").append(randomString).append(id.substring(1));
        } else {
            sb.append("1").append(randomString).append(id);
        }
        return sb.toString();
    }
    
    /**
     * ip地址转成long型数字
     * 将IP地址转化成整数的方法如下：
     * 1、通过String的split方法按.分隔得到4个长度的数组
     * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
     * @param strIp
     * @return
     */
    private static long ipToLong(String strIp) {
        String[]ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }
 
    /**
     * 将十进制整数形式转换成127.0.0.1形式的ip地址
     * 将整数形式的IP地址转化成字符串的方法如下：
     * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
     * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
     * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。
     * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。
     * @param longIp
     * @return
     */
    protected static String longToIP(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }
    
    private static String getLocalMac() throws Exception {
        return getLocalMac(InetAddress.getLocalHost());
    }
    
    private static String getLocalMac(InetAddress ia) throws Exception {

        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString();

    }
    
    static class SnowflakeIdGenerator {
        //================================================Algorithm's Parameter=============================================
        // 系统开始时间截 (UTC 2019-01-01 00:00:00)
        private final long startTime = 1546272000000L;
        // 机器id所占的位数
        private final long workerIdBits = 5L;
        // 数据标识id所占的位数
        private final long dataCenterIdBits = 5L;
        // 支持的最大机器id(十进制)，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
        // -1L 左移 5位 (worker id 所占位数) 即 5位二进制所能获得的最大十进制数 - 31
        private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
        // 支持的最大数据标识id - 31
        private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
        // 序列在id中占的位数
        private final long sequenceBits = 12L;
        // 机器ID 左移位数 - 12 (即末 sequence 所占用的位数)
        private final long workerIdMoveBits = sequenceBits;
        // 数据标识id 左移位数 - 17(12+5)
        private final long dataCenterIdMoveBits = sequenceBits + workerIdBits;
        // 时间截向 左移位数 - 22(5+5+12)
        private final long timestampMoveBits = sequenceBits + workerIdBits + dataCenterIdBits;
        // 生成序列的掩码(12位所对应的最大整数值)，这里为4095 (0b111111111111=0xfff=4095)
        private final long sequenceMask = -1L ^ (-1L << sequenceBits);
        //=================================================Works's Parameter================================================
        /**
         * 工作机器ID(0~31)
         */
        private long workerId;
        /**
         * 数据中心ID(0~31)
         */
        private long dataCenterId;
        /**
         * 毫秒内序列(0~4095)
         */
        private long sequence = 0L;
        /**
         * 上次生成ID的时间截
         */
        private long lastTimestamp = -1L;
        //===============================================Constructors=======================================================
        /**
         * 构造函数
         *
         * @param workerId     工作ID (0~31)
         * @param dataCenterId 数据中心ID (0~31)
         */
        public SnowflakeIdGenerator(long workerId, long dataCenterId) {
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", maxWorkerId));
            }
            if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
                throw new IllegalArgumentException(String.format("DataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
            }
            this.workerId = workerId;
            this.dataCenterId = dataCenterId;
        }
        // ==================================================Methods========================================================
        // 线程安全的获得下一个 ID 的方法
        public synchronized long nextId() {
            long timestamp = currentTime();
            //如果当前时间小于上一次ID生成的时间戳: 说明系统时钟回退过 - 这个时候应当抛出异常
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(
                        String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            }
            //如果是同一时间生成的，则进行毫秒内序列
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                //毫秒内序列溢出 即 序列 > 4095
                if (sequence == 0) {
                    //阻塞到下一个毫秒,获得新的时间戳
                    timestamp = blockTillNextMillis(lastTimestamp);
                }
            }
            //时间戳改变，毫秒内序列重置
            else {
                sequence = 0L;
            }
            //上次生成ID的时间截
            lastTimestamp = timestamp;
            //移位并通过或运算拼到一起组成64位的ID
            return ((timestamp - startTime) << timestampMoveBits) //
                    | (dataCenterId << dataCenterIdMoveBits) //
                    | (workerId << workerIdMoveBits) //
                    | sequence;
        }
        // 阻塞到下一个毫秒 即 直到获得新的时间戳
        protected long blockTillNextMillis(long lastTimestamp) {
            long timestamp = currentTime();
            while (timestamp <= lastTimestamp) {
                timestamp = currentTime();
            }
            return timestamp;
        }
        // 获得以毫秒为单位的当前时间
        protected long currentTime() {
            return System.currentTimeMillis();
        }
    }

}
