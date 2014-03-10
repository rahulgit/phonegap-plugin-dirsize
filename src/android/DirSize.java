package so.doo.app.plugins;

import java.io.File;
import java.text.DecimalFormat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Environment;
import android.util.Log;

public class DirSize extends CordovaPlugin {
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		String dir = args.getString(0);
		
		Log.e("execute", action);
		
		if (action.equals("size")) {
			long size = getSize(dir);
			callbackContext.success(""+size);
		} else if(action.equals("formatSize")) {
			String size = getSize(dir, true);
			callbackContext.success(size);
		}
		
		return true;
	}

	public long getSize(String dirPath) {
		
		long size = count(dirPath);
		Log.e("RETURN SIZE",""+size);

		return size;
	}

	// 222211322211122111
	public String getSize(String dirPath, boolean format) {

		long size = count(dirPath);
		
		Log.e("RETURN SIZE",""+size);

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