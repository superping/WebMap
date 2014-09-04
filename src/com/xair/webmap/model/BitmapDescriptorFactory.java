package com.xair.webmap.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;

public class BitmapDescriptorFactory {
	// Creates a bitmap descriptor that refers to the default marker image.

	static Context mContext;

	public static void init(Context context) {
		mContext = context;
	}

	public static BitmapDescriptor defaultMarker() {
		return null;
	}

	// //Creates a bitmap descriptor that refers to a colorization of the
	// default marker image.
	// static BitmapDescriptor defaultMarker(float hue){}
	//
	// //Creates a BitmapDescriptor using the name of an image in the assets
	// directory.
	// static BitmapDescriptor fromAsset(String assetName)

	static int bitmapIndex = 0;;

	// Creates a bitmap descriptor from a given image.
	public static BitmapDescriptor fromBitmap(Bitmap image) {
		BitmapDescriptor bd = new BitmapDescriptor(BitmapToBase64(image),
				image.getHeight(), image.getWidth());

		return bd;
	}

	// Creates a BitmapDescriptor using the name of an image file located in the
	// internal storage.
	public static BitmapDescriptor fromFile(String fileName) {
		if (mContext != null) {
			Bitmap bitmap = BitmapFactory.decodeFile(fileName);
			BitmapDescriptor bd = new BitmapDescriptor(BitmapToBase64(bitmap),
					bitmap.getHeight(), bitmap.getWidth());
			return bd;
		}
		return null;
	}

	// Creates a bitmap descriptor from an absolute file path.
	public static BitmapDescriptor fromPath(String absolutePath) {
		if (mContext != null) {
			Bitmap bitmap = BitmapFactory.decodeFile(absolutePath);
			BitmapDescriptor bd = new BitmapDescriptor(BitmapToBase64(bitmap),
					bitmap.getHeight(), bitmap.getWidth());
			return bd;
		}
		return null;
	}

	// Creates a BitmapDescriptor using the resource id of an image.
	public static BitmapDescriptor fromResource(int resourceId) {
		if (mContext != null) {
			Bitmap bitmap = BitmapFactory.decodeResource(
					mContext.getResources(), resourceId);
			BitmapDescriptor bd = new BitmapDescriptor(BitmapToBase64(bitmap),
					bitmap.getHeight(), bitmap.getWidth());
			return bd;
		}
		return null;
	}

	static String BitmapToBase64(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(CompressFormat.PNG, 100, baos);
		String result = Base64.encodeToString(baos.toByteArray(),
				Base64.DEFAULT);

		result = result.trim();
		result = result.replace("\n", "");
		result = result.replace("\r", "");
		return result;
	}

	public class LocalFileContentProvider extends ContentProvider {

		// private static final String URI_PREFIX = "content://com.xair.webmap";
		// /** 基本数据库存放路径 **/
		// public static final String BASE_JS_PATH =
		// "/data/data/com.xair.webmap/files/";

		public ParcelFileDescriptor openFile(Uri uri, String mode) {

			Log.d("LocalFileContentProvider", "fetching: " + uri);

			String path = getContext().getFilesDir().getAbsolutePath() + "/"
					+ uri.getPath();
			File file = new File(path);
			ParcelFileDescriptor parcel = null;
			try {
				parcel = ParcelFileDescriptor.open(file,
						ParcelFileDescriptor.MODE_READ_ONLY);

			} catch (FileNotFoundException e) {
				Log.e("LocalFileContentProvider", "uri " + uri.toString(), e);
			}
			return parcel;
		}

		@Override
		public boolean onCreate() {
			return false;
		}

		@Override
		public int delete(Uri uri, String s, String[] as) {
			throw new UnsupportedOperationException(
					"Not supported by this provider");
		}

		@Override
		public String getType(Uri uri) {
			throw new UnsupportedOperationException(
					"Not supported by this provider");
		}

		@Override
		public Uri insert(Uri uri, ContentValues contentvalues) {
			throw new UnsupportedOperationException(
					"Not supported by this provider");
		}

		@Override
		public Cursor query(Uri uri, String[] as, String s, String[] as1,
				String s1) {
			throw new UnsupportedOperationException(
					"Not supported by this provider");
		}

		@Override
		public int update(Uri uri, ContentValues contentvalues, String s,
				String[] as) {
			throw new UnsupportedOperationException(
					"Not supported by this provider");
		}

	}
}
