package com.zlw.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ranger
 * @create 2019-05-31 15:37
 */
public class FastDFSUtils {

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    public static String uploadFile(String FDFS_CLIENT_PAHT, String FDFS_ADDRESS, MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

        String imgUrl = null;
        try {

            ClientGlobal.init(FDFS_CLIENT_PAHT);

            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);

            String fileIds[] = storageClient.upload_file(file.getBytes(), ext, null);

            imgUrl = "http://" + FDFS_ADDRESS + "/" + fileIds[0] + "/" + fileIds[1];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl.replace("/group1/M00", "");
    }

}
