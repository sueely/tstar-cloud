import com.diyiliu.common.cache.ICache;
import com.diyiliu.common.util.CommonUtil;
import com.tiza.process.protocol.gb32960.GB32960DataProcess;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: TestHandler
 * Author: DIYILIU
 * Update: 2017-10-30 11:31
 */

public class TestHandler {


    @Resource
    protected ICache cmdCacheProvider;

    @Test
    public void test() {

        String str = "110A1E0B0833010201010000000014D213FA271B3E01020B2E1032020101043F4E204E2041141627100501000000000000000006014E2AD201100CCA011E4101013F0700000000000000000008010113FA271B009C00019C00010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001002A00010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000109010100543F403F3F404040404040403F4040404040404040404040404040403F404040404040403F404040404040403F404040404040403F404040404040403F404040404040403F404040404040403F4040404040404040";
        byte[] content = CommonUtil.hexStringToBytes(str);

        GB32960DataProcess process = (GB32960DataProcess) cmdCacheProvider.get(0x02);
        process.parse(content, null);
    }

    @Test
    public void test1() {

        byte[] content = CommonUtil.hexStringToBytes("9C");
        ByteBuf buffer = Unpooled.copiedBuffer(content);
        System.out.println(buffer.readByte());
    }

    @Test
    public void test2(){
        String str = "AAAAA000";
        byte[] content = CommonUtil.hexStringToBytes(str);
        ByteBuf buffer = Unpooled.copiedBuffer(content);

        long flag = buffer.readUnsignedInt();

        String[] alarmArray = new String[]{"TempDiffAlarm", "BatteryHighTempAlarm",
                "HighPressureAlarm", "LowPressureAlarm",
                "SocLowAlarm", "BatteryUnitHighVoltageAlarm",
                "BatteryUnitLowVoltageAlarm", "SocHighAlarm",
                "SocJumpAlarm", "BatteryMismatchAlarm",
                "BatteryUnitUniformityAlarm", "InsulationAlarm",
                "DCDCTempAlarm", "BrakeAlarm",
                "DCDCStatusAlarm", "MotorCUTempAlarm",
                "HighPressureLockAlarm", "MotorTempAlarm", "BatteryOverChargeAlarm"};

        Map alarmMap = new HashMap();

        for (int i = 0; i < alarmArray.length; i++) {
            String column = alarmArray[i];
            long value =  (flag >> i) & 0x01;

            alarmMap.put(column, value);
        }

        System.out.println(alarmMap);
    }

    @Test
    public void test3(){

        System.out.println(1 << 13);
    }
}