//package sold.monkeytech.com.sold_android.framework.managers;
//
//import android.os.AsyncTask;
//import android.os.Environment;
//
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Date;
//
///**
// * Created by MonkeyFather on 19/11/2017.
// */
//
//public class DownloadTaskManager extends AsyncTask<String, Void, File> {
//
//    private static final int PICTURE = 0;
//    private static final int PDF = 1;
//
//    private final int type;
//    private String fileName;
//    private final TAction<File> onDownloaded;
//
//    public DownloadTaskManager(int type, TAction<File> onDownloaded) {
//        this.type = type;
//        fileName = "" + new Date().getTime();
//        this.onDownloaded = onDownloaded;
//    }
//
//    @Override
//    protected File doInBackground(String... params) {
//        String yourFilePath = "";
//        switch (type) {
//            case PICTURE:
//                return DownloadFile(params[0], "MID/pictures/", fileName + ".jpeg");
//
//            case PDF:
//                return DownloadFile(params[0], "MID/pdfs/", fileName + "_pdf" + ".pdf");
//
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(final File file) {
//        if (file != null) {
//            onDownloaded.execute(file);
////                switch (type) {
////                    case PICTURE:
////
////                        Toast.makeText(CertificateActivity.this, "Picture downloaded: " + file.getPath(), Toast.LENGTH_SHORT).show();
////                        break;
////                    case PDF:
////                        Toast.makeText(CertificateActivity.this, "Pdf downloaded: " + file.getPath(), Toast.LENGTH_SHORT).show();
////                        break;
////                }
//        }
//    }
//
//    public File DownloadFile(String strUrl, String folderName, String fileName) {
//        try {
//            File dir = new File(Environment.getExternalStorageDirectory() + "/" + folderName);
//            if (dir.exists() == false) {
//                dir.mkdirs();
//            }
//
//            URL url = new URL(strUrl);
//            File file = new File(dir, fileName);
//
//            URLConnection ucon = url.openConnection();
//            InputStream is = ucon.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            ByteArrayBuffer baf = new ByteArrayBuffer(20000);
//            int current = 0;
//            while ((current = bis.read()) != -1) {
//                baf.append((byte) current);
//            }
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(baf.toByteArray());
//            fos.flush();
//            fos.close();
//            return file;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
