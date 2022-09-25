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
import java.io.PrintStream;
import java.io.File;
import java.io.*;
import org.eclipse.openj9.criu.CRIUSupport;

public class ElapsedTime {
	public static final long SECOND = 1000000000;
	public static final long TEN_SECONDS = 10 * SECOND;
	public static final long FIVE_SECONDS = 5 * SECOND;
	
	public static void main(String args[]) throws Throwable {
		System.out.println("Spin for 10 seconds");
		
		final long start = System.nanoTime();
		int sleeps = 0;
		boolean checkpointTaken = false;
		
		while ((System.nanoTime() - start) < (TEN_SECONDS + 0.1 * SECOND)) {
			Thread.sleep(1000);
			sleeps++;
			System.out.print(".");
			if (!checkpointTaken && ((System.nanoTime() - start) > FIVE_SECONDS)) {
				//uncomment the lines below
				//checkPointJVM("checkpointData");
				//checkpointTaken = true;
			}
		}
		System.out.println();
		
		System.out.println("Total number of sleeps " + sleeps);
	}

	public static void checkPointJVM(String path) {
		if (CRIUSupport.isCRIUSupportEnabled()) {
			new CRIUSupport(Paths.get(path))
					.setLeaveRunning(false)
					.setShellJob(true)
					.setFileLocks(true)
					.setLogLevel(4)
					.setLogFile("logs")
					.checkpointJVM();
		} else {
			System.err.println("CRIU is not enabled: " + CRIUSupport.getErrorMessage());
		}

	}
}
