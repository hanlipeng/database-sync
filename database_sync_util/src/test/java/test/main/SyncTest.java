package test.main;

import com.tpg.sync.sync.SyncMainMethod;

import java.io.File;
import java.net.URL;

/**
 * @author reph
 * @date 2018/3/30
 */
public class SyncTest {
    public static void main(String args []) throws Exception {
        URL config = SyncTest.class.getResource("/config.properties");
        URL sqlRule = SyncTest.class.getResource("/newsqlrule.properties");
        URL tableType = SyncTest.class.getResource("/tabletype.properties");
        URL threadConfig = SyncTest.class.getResource("/threadConfig.properties");
        SyncMainMethod syncMainMethod = new SyncMainMethod(new File(config.getFile()),
                new File(sqlRule.getFile()), new File(tableType.getFile()),
                new File(threadConfig.getFile()));
        syncMainMethod.startSync(args);
        syncMainMethod.getTableInfo();
    }
}
