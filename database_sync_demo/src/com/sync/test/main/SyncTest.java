package com.sync.test.main;

import com.tpg.sync.sync.SyncMainMethod;

import java.io.File;

/**
 * @author reph
 * @date 2018/3/30
 */
public class SyncTest {
    public static void main(String args []) {
        SyncMainMethod.startSync(args,new File("dbsyncconfig.properties"),new File("newsqlrule.properties"),new File("tabletype.properties"),new File("threadConfig.properties"));
    }
}
