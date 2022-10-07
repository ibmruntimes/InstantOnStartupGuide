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
package com.demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.openj9.criu.CRIUSupport;

/**
 * This class is for demonstration purposes only
 */
public final class CRIUNatives {
	static {
		System.loadLibrary("criunatives");
	}

	private String imageDir;
	private boolean leaveRunning;
	private boolean shellJob;
	private boolean extUnixSupport;
	private int logLevel;
	private String logFile;
	private boolean fileLocks;
	private String workDir;
	private boolean tcpEstablished;
	private boolean autoDedup;
	private boolean trackMemory;

	public CRIUNatives(Path imageDir) {
		setImageDir(imageDir);
	}

	public CRIUNatives setImageDir(Path imageDir) {
		this.imageDir = imageDir.toAbsolutePath().toString();
		return this;
	}

	public CRIUNatives setLeaveRunning(boolean leaveRunning) {
		this.leaveRunning = leaveRunning;
		return this;
	}

	public CRIUNatives setShellJob(boolean shellJob) {
		this.shellJob = shellJob;
		return this;
	}

	public CRIUNatives setExtUnixSupport(boolean extUnixSupport) {
		this.extUnixSupport = extUnixSupport;
		return this;
	}

	public CRIUNatives setLogLevel(int logLevel) {
		this.logLevel = logLevel;
		return this;
	}

	public CRIUNatives setLogFile(String logFile) {
		this.logFile = logFile;
		return this;
	}

	public CRIUNatives setFileLocks(boolean fileLocks) {
		this.fileLocks = fileLocks;
		return this;
	}

	public CRIUNatives setTCPEstablished(boolean tcpEstablished) {
		this.tcpEstablished = tcpEstablished;
		return this;
	}

	public CRIUNatives setAutoDedup(boolean autoDedup) {
		this.autoDedup = autoDedup;
		return this;
	}

	public CRIUNatives setTrackMemory(boolean trackMemory) {
		this.trackMemory = trackMemory;
		return this;
	}

	public void criuDump() {
		int rc = criuDumpImpl(imageDir, leaveRunning, shellJob, extUnixSupport, logLevel, logFile, fileLocks, workDir,
				tcpEstablished, autoDedup, trackMemory);
		
		if (rc < 1) {
			System.out.println("criu dump failed, rc=" + rc);
		}
	}

	private static native int criuDumpImpl(String imageDir, boolean leaveRunning, boolean shellJob,
			boolean extUnixSupport, int logLevel, String logFile, boolean fileLocks, String workDir,
			boolean tcpEstablished, boolean autoDedup, boolean trackMemory);
}
