package io.sunny.common.counter;

import org.apache.commons.lang3.StringUtils;

import io.sunny.common.utils.NodeId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalBizSequence implements CyclicSequence {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	private String name;
	private int length;
	private LocalCyclicCounter counter;

	public LocalBizSequence(String name, int length) {
		this.name = name;
		if (length > 18) {
			throw new IllegalArgumentException("length value should <= 18");
		}
		this.length = length;
		this.counter = new LocalCyclicCounter(computeMaxVal(length));
	}

	private long computeMaxVal(int length) {
		return (long) Math.pow(10D, length);
	}

	@Override
	public String next() {
		return getName() + LocalDateTime.now().format(FORMATTER) + getNodeIdPart()
				+ StringUtils.leftPad(String.valueOf(counter.increment()), length, '0');
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getLength() {
		return length;
	}

	public static String getNodeIdPart() {
		return NodeId.ID;
	}
}
