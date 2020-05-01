package com.mmkj.baselibrary.util;

/**
 * @author xuyexiang
 * @date 2018/5/18 15:20
 */

public class CampaignHelper {

	public static final String REFERRER = "referrer";

	private static final String SOURCE = "source";
	private static final String MEDIUM = "medium";
	private static final String CONTENT = "content";
	private static final String CAMPAIGN = "campaign";

	private static String referrer;

	private static String getReferrer() {
		if (referrer == null || referrer.isEmpty()) {
			referrer =PreferenceUtils.getString(REFERRER, "");
		}
		return referrer;
	}

	private static String parseReferrer(String field) {
		String[] array = getReferrer().split("&");

		for (String string : array) {
			if (string.contains(field)) {
				int index = string.indexOf("=");

				return string.substring(index + 1);
			}
		}
		return null;
	}

	public static String getSource() {
		return parseReferrer(SOURCE);
	}

	public static String getMedium() {
		return parseReferrer(MEDIUM);
	}



	public static String getContent() {
		return parseReferrer(CONTENT);
	}

	public static String getCampaign() {
		return parseReferrer(CAMPAIGN);
	}

}
