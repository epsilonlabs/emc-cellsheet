package org.eclipse.epsilon.labs.emc.cellsheet;

import java.time.Duration;
import java.time.Instant;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class Profiler {

	private static final Table<Object, String, Instant> STARTS = HashBasedTable.create();
	private static final Table<Object, String, Instant> FINISHES = HashBasedTable.create();
	private static final Table<Object, String, Integer> COUNTS = HashBasedTable.create();

	private static final boolean profile = Boolean.parseBoolean(System.getProperty("profile", "false"));

	public static void profileStart(Object o, String method) {
		if (profile) {
			if (COUNTS.contains(o, method)) {
				COUNTS.put(o, method, COUNTS.get(o, method) + 1);
			} else {
				COUNTS.put(o, method, 1);
			}

			STARTS.put(o, method, Instant.now());
			System.out.println(
					String.format("%s#%s start: %s", o.getClass().getSimpleName(), method, STARTS.get(o, method)));
		}
	}

	public static void profileStop(Object o, String method) {
		if (profile) {
			FINISHES.put(o, method, Instant.now());
			System.out.println(
					String.format("%s#%s finish: %s", o.getClass().getSimpleName(), method, FINISHES.get(o, method)));
			System.out.println(String.format("%s#%s duration: %s", o.getClass().getSimpleName(), method,
					Duration.between(STARTS.get(o, method), FINISHES.get(o, method))));
			System.out.println();
		}
	}

	public static void profileCaller() {
		if (profile) {
			StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
			System.out.println(caller.getClassName() + "#" + caller.getMethodName());
		}
	}

	public static void profileCounts() {
		if (profile) {
			for (Cell<Object, String, Integer> c : COUNTS.cellSet()) {
				System.out.println(String.format("%s#%s count: %s", c.getRowKey().getClass().getSimpleName(),
						c.getColumnKey(), c.getValue()));
			}
		}
	}

}
