/*******************************************************************************
 * Copyright 2022, IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.PrintStream;
import java.io.File;
import java.io.*;

public class TimerEvents {
	public static final long SECOND = 1000000000;
	public static final long SECOND_IN_MILLIS = 1000;
	public static final long TEN_SECONDS = 10 * SECOND;
	public static final long FIVE_SECONDS = 5 * SECOND;

	public static void main(String args[]) throws Throwable {
		System.out.println("Start 10 event timers");

		final long start = System.nanoTime();
		boolean checkpointTaken = false;
		for (int i = 0; i < 10; i++) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					System.out.println("Event fired!");
				}
			}, 0, 10 * SECOND_IN_MILLIS);
			Thread.sleep(SECOND_IN_MILLIS);
		}
		// uncomment the line below
		//Utils.checkPointJVM("checkpointData");
	}
}
