package so.doo.app.plugins;

import android.os.Environment;
import java.io.File;
import java.text.DecimalFormat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class DirSize extends CordovaPlugin {

	public long getSize(String dirPath) {

		return count(dirPath);
	}

	public String getSize(String dirPath, boolean format) {

		long size = count(dirPath);

		if(format) {
			return format(size);
		} else return ""+size;
	}

	public Long count(String dirPath) {

		File dir = getDir(dirPath);

		long size = getFolderSize(dir);
		
		return size;
	}

	public long getFolderSize(File dir) {

		long size = 0;
		for (File file : dir.listFiles()) {
		    if (file.isFile()) {
		        System.out.println(file.getName() + " " + file.length());
		        size += file.length();
		    }
		    else
		        size += getFolderSize(file);
		}

		return size;
	}

	public String format(long size) {
		if (size / (1024 * 1024)  > 0) {
			float tmpSize = (float)(size) / (float)(1024 * 1024);
			DecimalFormat df=new DecimalFormat("#.##");
			return "" + df.format(tmpSize) + "MB";
	    } else if (size / 1024 > 0) {
			return "" + (size / (1024)) + "KB";
	    } else return "" + size + "B";
	}

	public File getDir(String dirPath) {

		File sdcardDir = Environment.getExternalStorageDirectory();
		String path = sdcardDir.getPath() + "/" + dirPath;
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		return dir;
	}
}