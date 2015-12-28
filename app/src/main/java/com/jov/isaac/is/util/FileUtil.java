package com.jov.isaac.is.util;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class FileUtil {
	public static final String DIRECT_ROOT = "/isaac/";
	public static final String DIRECT_IMAGE = "/isaac/image/";

	public FileUtil() {
		makeRootFile();
	}

	public static String getLocalImageFile(String imageUrl) {
		int lastSlashIndex = imageUrl.lastIndexOf("/");
		String imageName = imageUrl.substring(lastSlashIndex + 1);
		String imageDir = Environment.getExternalStorageDirectory().getPath()
				+ DIRECT_IMAGE;
		File file = new File(imageDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String imagePath = imageDir + imageName;
		return imagePath;
	}

	public static String makeRootFile() {
		String rootDir = Environment.getExternalStorageDirectory().getPath()
				+ DIRECT_ROOT;
		File file = new File(rootDir);
		if (!file.exists()) {
			file.mkdir();
		}
		return rootDir;
	}
	public static File updateFile = null;
	public static File createFile(String name) {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File updateDir = new File(Environment.getExternalStorageDirectory()
					+ DIRECT_ROOT);
			updateFile = new File(updateDir , name + ".apk");
			if (!updateDir.exists()) {
				updateDir.mkdirs();
			}
			deleteExistsApk(updateDir);
			if (!updateFile.exists()) {
				try {
					updateFile.createNewFile();
					return updateFile;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	private static void  deleteExistsApk(File dir){
		if(dir!=null&&dir.exists()){
			File[] files = dir.listFiles();
			for(File file:files){
				file.deleteOnExit();
			}
		}
	}
}
