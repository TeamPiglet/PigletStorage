package com.example.pigletstorage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class Connection {
	public static String getNetworkClass(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);      
	    NetworkInfo info = cm.getActiveNetworkInfo();
	    if(info==null || !info.isConnected())
	        return null; //not connected
	    if(info.getType() == ConnectivityManager.TYPE_WIFI)
	        return "WIFI";
	    if(info.getType() == ConnectivityManager.TYPE_MOBILE){
	        int networkType = info.getSubtype();
	        switch (networkType) {
	            case TelephonyManager.NETWORK_TYPE_GPRS:
	            case TelephonyManager.NETWORK_TYPE_EDGE:
	            case TelephonyManager.NETWORK_TYPE_CDMA:
	            case TelephonyManager.NETWORK_TYPE_1xRTT:
	            case TelephonyManager.NETWORK_TYPE_IDEN:
	                return "2G";
	            case TelephonyManager.NETWORK_TYPE_UMTS:
	            case TelephonyManager.NETWORK_TYPE_EVDO_0:
	            case TelephonyManager.NETWORK_TYPE_EVDO_A:
	            case TelephonyManager.NETWORK_TYPE_HSDPA:
	            case TelephonyManager.NETWORK_TYPE_HSUPA:
	            case TelephonyManager.NETWORK_TYPE_HSPA:
	            case TelephonyManager.NETWORK_TYPE_EVDO_B:
	            case TelephonyManager.NETWORK_TYPE_EHRPD:
	            case TelephonyManager.NETWORK_TYPE_HSPAP:
	                return "3G";
	            case TelephonyManager.NETWORK_TYPE_LTE:
	                return "4G";
	            default:
	                return "Unknown";
	         }
	    }
	    return "Unknown";
	}
}
