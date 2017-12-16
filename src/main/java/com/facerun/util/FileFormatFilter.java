package com.facerun.util;

public class FileFormatFilter {

	public static String filterFile(String path) {
		if (checkFileIsImg(path).equals("IMG")) {
			return "IMG";
		} else if (checkFileIsAudio(path).equals("AUDIO")) {
			return "AUDIO";
		} else if (checkFileIsVideo(path).equals("VIDEO")) {
			return "VIDEO";
		}
		return "FILE";
	}

	public static String checkFileIsImg(String path) {
		path = path.toLowerCase();
		if (path.endsWith("jpg") || path.endsWith("jpeg") || path.endsWith("png") || path.endsWith("gif"))
			return "IMG";
		return "";
	}

	public static String checkFileIsAudio(String path) {
		path = path.toLowerCase();
		if (path.endsWith("mp3") || path.endsWith("wma") || path.endsWith("wav") || path.endsWith("aac")
				|| path.endsWith("ogg") || path.endsWith("raw"))
			return "AUDIO";
		return "";
	}

	public static String checkFileIsVideo(String path) {
		path = path.toLowerCase();
		if (path.endsWith("mp4") || path.endsWith("avi") || path.endsWith("mov") || path.endsWith("wmv")
				|| path.endsWith("3gp") || path.endsWith("asf") || path.endsWith("flv") || path.endsWith("f4v")
				|| path.endsWith("rmvb"))
			return "VIDEO";
		return "";
	}
}
