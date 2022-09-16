import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.PrintStream;
import java.io.File;
import java.io.*;
import org.eclipse.openj9.criu.CRIUSupport;

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