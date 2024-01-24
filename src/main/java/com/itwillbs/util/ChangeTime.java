package com.itwillbs.util;

import java.sql.Timestamp;

// 글목록,글내용,댓글등 작성된 글이 등록된 시간을 계산해서 몇초전 몇분전 몇시간전 등 출력하는 함수사용.
public class ChangeTime {
		
	private static class CH_MAXIMUM {
		
		public static final int SEC = 60; // 초
		public static final int MIN = 60; // 분
		public static final int HOUR = 24; // 시
		public static final int DAY = 30;  // 일
		public static final int MONTH = 12; //월
		
	}
		
		public static String calculateTime(Timestamp date) {
			
			long curTime = System.currentTimeMillis(); // 현재시간 = 시스템시간
			long regTime = date.getTime(); // curTime에서 시간을 가져오는 것.
			long diffTime = (curTime - regTime) / 1000;
			
			String msg = null;
			
			if (diffTime < CH_MAXIMUM.SEC) {
				// sec
				msg = diffTime + "초 전";
				
			} else if ((diffTime /= CH_MAXIMUM.SEC) < CH_MAXIMUM.MIN) {
				// min
				msg = diffTime + "분 전";
				
			} else if ((diffTime /= CH_MAXIMUM.MIN) < CH_MAXIMUM.HOUR) {
				// hour
				msg = (diffTime) + "시간 전";
				
			} else if ((diffTime /= CH_MAXIMUM.HOUR) < CH_MAXIMUM.DAY) {
				// day
				msg = (diffTime) + "일 전";
				
			} else if ((diffTime /= CH_MAXIMUM.DAY) < CH_MAXIMUM.MONTH) {
				// day
				msg = (diffTime) + "달 전";
				
			} else {
				msg = (diffTime) + "년 전";
			}
			return msg;
		}
			
}
		
		
		
		
		
		
		

