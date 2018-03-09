package com.xzkj.xzkjproject.utils;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

/**
 * 获取设备信息
 * 
 * @author BeiLou
 * 
 */
public class DeviceInfoUtils {
	private static String phonePermission = android.Manifest.permission.READ_PHONE_STATE;
	private static Context mContext;
	public static final String DEFAULTVERSION = "2.2.0";

	public static final int PHONE_OS_ADAPTER_VERSION = 23;

	/**
	 * 设置Context
	 * 
	 * @param aContext
	 */
	public static void setContext(Context aContext) {
		mContext = aContext.getApplicationContext();
	}

	/**
	 * 获取默认代理ip
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getProxyUrl() {
		String proxyUrl = null;
		if (!isWifi()) {
			proxyUrl = android.net.Proxy.getDefaultHost();
		}
		return proxyUrl;
	}

	/**
	 * 获取设备IMSI
	 * 
	 * @param paramContext
	 * @return
	 */
	public static String getIMSI(Context paramContext) {

		if (PermissionUtil.hasPermission(paramContext, phonePermission)) {
			return ((TelephonyManager) paramContext.getSystemService("phone"))
					.getSubscriberId();
		}
		return "unknow";
	}

	/**
	 * 获取设备IMEI
	 * 
	 * @param paramContext
	 * @return
	 */
	public static String getIMEI(Context paramContext) {

		String randomId = UUID.randomUUID().toString();
		if (!TextUtils.isEmpty(randomId)) {
			randomId = exChange(randomId.substring(0, 8).toUpperCase());
		}

		if (PermissionUtil.hasPermission(paramContext, phonePermission)) {
			return ((TelephonyManager) paramContext.getSystemService("phone"))
					.getDeviceId();
		} else {
			return "999999999999999-" + randomId;
		}

	}

	public static String exChange(String str) {
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (Character.isLowerCase(c)) {
					str.replace(c, Character.toUpperCase(c));
				}
			}
		}
		return str;
	}

	/**
	 * 获取设备型号
	 * 
	 * @return
	 */
	public static String getMobileModel() {
		return Build.MODEL;
	}

	/**
	 * 获取屏幕分辨率
	 * 
	 * @return
	 */
	public static float getdensity(Context mContext1) {
		DisplayMetrics displayMetrics = mContext1.getResources()
				.getDisplayMetrics();
		return displayMetrics.density;
	}

	/**
	 * 获取设备UUID
	 * 
	 * @return
	 */
	// private String getMyUUID() {
	// final TelephonyManager tm = (TelephonyManager) mContext
	// .getSystemService(Context.TELEPHONY_SERVICE);
	// final String tmDevice, tmSerial, tmPhone, androidId;
	// tmDevice = "" + getIMEI(mContext);
	// tmSerial = "" + getIMSI(mContext);
	// androidId = ""
	// + android.provider.Settings.Secure.getString(
	// mContext.getContentResolver(),
	// android.provider.Settings.Secure.ANDROID_ID);
	// UUID deviceUuid = new UUID(androidId.hashCode(),
	// ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
	// String uniqueId = deviceUuid.toString();
	// // Log.d("debug","uuid="+uniqueId);
	// return uniqueId;
	// }

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getTertype() {
		return Build.MODEL;

	}

	/**
	 * 获取手机品牌
	 * 
	 * @return
	 */
	public static String getBrand() {
		return Build.BRAND;

	}

	/**
	 * 获取手机品牌
	 * 
	 * @return
	 */
	public static String getVersion() {
		return Build.BRAND;

	}

	/**
	 * 获取手机是否越狱
	 * 
	 * @return
	 */
	public static boolean isRoot() {
		boolean bool = false;

		try {
			if ((!new File("/system/bin/su").exists())
					&& (!new File("/system/xbin/su").exists())) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (Exception e) {

		}
		return bool;
	}

	public static String isRootNumber() {
		boolean bool = false;

		try {
			if ((!new File("/system/bin/su").exists())
					&& (!new File("/system/xbin/su").exists())) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (Exception e) {

		}

		if (bool) {
			return "1";
		} else {
			return "0";
		}

	}

	/**
	 * 获取当前手机系统版本号
	 *
	 * @return  系统版本号
	 */
	public static String getSystemVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 获取设备SDK版本号
	 * 
	 * @return
	 */
	public static int getSDKVersionNumber() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * 获取Wifi状态
	 * 
	 * @return
	 */
	public static int getWifiStatus(Context paramContext) {
		return ((WifiManager) paramContext
				.getSystemService(Service.WIFI_SERVICE)).getWifiState();
	}

	/**
	 * 判断当前网络是否是Wifi
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isWifi() {
		if (mContext == null)
			return false;
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前的安装包版本
	 * 
	 * @param context
	 * @return
	 */
	public static String getCurrentVersion(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {

		}

		return DEFAULTVERSION;
	}

	/**
	 * 获取手机服务商信息
	 * 
	 */
	public String getProvidersName(Context paramContext) {
		String ProvidersName = null;
		String IMSI = "";
		// 返回唯一的用户ID，就是这张卡的编号
		if (PermissionUtil.hasPermission(paramContext, phonePermission)) {
			IMSI = ((TelephonyManager) paramContext.getSystemService("phone"))
					.getSubscriberId();
		} else {
			IMSI = "unknow";
		}

		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
			ProvidersName = "中国移动";
		} else if (IMSI.startsWith("46001")) {
			ProvidersName = "中国联通";
		} else if (IMSI.startsWith("46003")) {
			ProvidersName = "中国电信";
		}
		return ProvidersName;
	}

	/**
	 * 检查运营商网络是否可用
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean checkNet(Context mContext) {
		ConnectivityManager manager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
		if (netWrokInfo == null || !netWrokInfo.isAvailable()) {

			// Toast.LENGTH_LONG).show();
			return false;
		} else if (netWrokInfo.getTypeName().equals("MOBILE")
				&& netWrokInfo.getExtraInfo().equals("cmwap")) {

			// Toast.LENGTH_LONG).show();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 检查是否可以下载
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isAllowDown(Context mContext) {
		boolean isAllowDown = true;
		Properties properties = new Properties();
		try {
			properties.load(mContext.openFileInput("isdownwifi.properties"));
			String lan = properties.getProperty("isdownwifi");
			if (lan.equals("true")) {
				if (DeviceInfoUtils.isWifi()) {
					isAllowDown = true;
				} else {
					isAllowDown = false;
					// Toast.makeText(mContext, "WIFI 网络不可用", 3000).show();
				}
			} else {
				isAllowDown = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return isAllowDown;
	}

	/**
	 * 获取设备ID
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getDeviceId(Context mContext) {
		Properties properties = new Properties();
		try {
			properties.load(mContext.openFileInput("settings.properties"));
			String deviceid = properties.getProperty("deviceid");
			return deviceid;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 设置设备ID
	 * 
	 * @param mContext
	 * @return
	 */
	public static void setDeviceId(Context mContext) {
		String deviceId = getIMEI(mContext);
		Calendar calendar = Calendar.getInstance();
		long unixTime = calendar.getTimeInMillis();
		deviceId += String.valueOf(unixTime);

		Properties properties = new Properties();
		properties.setProperty("deviceid", deviceId);
		try {
			properties.store(mContext.openFileOutput("settings.properties",
					Context.MODE_PRIVATE), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取通讯录中返回数据
	 * 
	 * @param context
	 * @param uri
	 * @return
	 */
	public static String[] getPhoneContacts(Context context, Uri uri) {
		String[] contact = new String[2]; // 得到ContentResolver对象
		ContentResolver cr = context.getContentResolver(); // 取得电话本中开始一项的光标
		Cursor cursor = cr.query(uri, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			// 取得联系人姓名
			int nameFieldColumnIndex = cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			if (nameFieldColumnIndex == -1) {
				cursor.close();
				return null;
			}
			contact[0] = cursor.getString(nameFieldColumnIndex); // 取得电话号码
			String ContactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			Cursor phone = cr.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
							+ ContactId, null, null);
			if (phone != null) {
				phone.moveToFirst();
				contact[1] = phone
						.getString(phone
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			}
			phone.close();
			cursor.close();
		} else {
			return null;
		}
		return contact;
	}
}
