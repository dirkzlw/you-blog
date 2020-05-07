package com.zlw.test;

import com.zlw.common.CommonApplication;
import com.zlw.test.dao.IcoInfoRepository;
import com.zlw.common.po.IcoInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Dirk
 * @date 2020-05-07 14:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class FastDfsTest {

    @Autowired
    private IcoInfoRepository icoInfoRepository;

    @Test
    public void leadInIco() throws Exception {
        File dir = new File("C:\\Users\\zhouliwei\\Downloads\\5eb3aa97df4ca");
        File[] list = dir.listFiles();
        for (File file : list) {
            String fileName = file.getName();
            String icoName = fileName.split("\\.")[0];
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            ClientGlobal.init("fdfs_client.conf");

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            //file转为字节数组
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();

            System.out.println("data = " + data);
            System.out.println("icoName = " + icoName);

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);

            String fileIds[] = storageClient.upload_file(data, ext, null);

            String icoUrl = "http://123.57.210.127:80/" + fileIds[0] + "/" + fileIds[1];

            IcoInfo icoInfo = new IcoInfo(icoName, icoUrl);
            icoInfoRepository.save(icoInfo);
        }
    }

}
