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
import java.util.Properties;

public class HelloInstantOn {

	public static void main(String args[]) throws Throwable {
		System.out.println("Start");

		System.out.println("Load and initialize classes");
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			Thread.sleep(1000);
		}
		System.out.println(".");

		//uncomment the line below
		//checkPointJVM("checkpointData");

		System.out.println("Application ready!");

		if (null != System.getProperty("helloinstanton_heartbeat")) {
			while (true) {
				System.out.println("Heartbeat");
				Thread.sleep(2000);
			}
		}
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
