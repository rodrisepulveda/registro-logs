package awto.registrologs.service.util;

public class Util {

	public static String obtenerHashTagSinGato(String hashTag) {

		if (hashTag.startsWith("#")) {

			return hashTag.substring(1, hashTag.length());

		} else {

			return hashTag;

		}

	}

	public static String agregarGatoAHashtagSiNoLoTiene(String hashTag) {

		if (hashTag.startsWith("#")) {

			return hashTag;

		} else {

			return "#" + hashTag;

		}

	}

}
