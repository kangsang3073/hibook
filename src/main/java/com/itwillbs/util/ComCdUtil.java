package com.itwillbs.util;

import java.util.ArrayList;

public class ComCdUtil {

	public static ComCdDTO getComCd(String cd) {
		ComCdDAO dao = new ComCdDAO();
		return dao.getComCd(cd);
	}
	
	public static ArrayList<ComCdDTO> getCdList(String cdGrp){
		ComCdDAO dao = new ComCdDAO();
		return dao.getComCdList(cdGrp);
	}
	
}
