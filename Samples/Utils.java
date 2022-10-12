
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
import org.eclipse.openj9.criu.CRIUSupport;
import com.demo.CRIUNatives;

public class Utils {
	public static void checkPointJVM(String path) {
		checkPointJVM(path, null);
	}

	public static void checkPointJVM(String path, String envFile) {
		if (CRIUSupport.isCRIUSupportEnabled()) {
			CRIUSupport criuSupport = new CRIUSupport(Paths.get(path));

			criuSupport = criuSupport.setLeaveRunning(false).setShellJob(true).setFileLocks(true).setLogLevel(4)
					.setLogFile("logs");

			if (envFile != null) {
				criuSupport = criuSupport.registerRestoreEnvFile(Paths.get(envFile));
			}
			criuSupport.checkpointJVM();
		} else {
			System.err.println("CRIU is not enabled: " + CRIUSupport.getErrorMessage());
			if (Boolean.getBoolean("useCRIUNativesLib")) {
				System.err.println("Using CRIU natives libary instead");
				new CRIUNatives(Paths.get(path)).setLeaveRunning(false).setShellJob(true).setFileLocks(true)
						.setLogLevel(4).setLogFile("logs").criuDump();
			}
		}
	}
}
