package io.sunny.common.utils;

import io.sunny.common.counter.LocalBizSequence;

public class SequenceUtil {
	private static LocalBizSequence ordNoSeq = new LocalBizSequence("ORD", 6);
	private static LocalBizSequence jrnNoSeq = new LocalBizSequence("JRN", 6);
	private static LocalBizSequence logNoSeq = new LocalBizSequence("LOG", 6);
	private static LocalBizSequence dicSeq = new LocalBizSequence("DIC", 6);

	public static String ordNo() {
		return ordNoSeq.next();
	}

	public static String jrnNo() {
		return jrnNoSeq.next();
	}

	public static String logNo() {
		return logNoSeq.next();
	}

	public static String dictateId() {
		return dicSeq.next();
	}

}
