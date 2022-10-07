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
#include <criu/criu.h>
#include <jni.h>
#include <fcntl.h>
#include <dlfcn.h>

/* This file is for demonstration purposes only */
JNIEXPORT jint JNICALL Java_com_demo_CRIUNatives_criuDumpImpl(JNIEnv *env, jobject unused, jstring imageDir, jboolean leaveRunning, jboolean shellJob,
		jboolean extUnixSupport, jint logLevel, jstring logFile, jboolean fileLocks, jstring workDir,
		jboolean tcpEstablished, jboolean autoDedup, jboolean trackMemory)
{
	jint rc = criu_init_opts();
	if (0 != rc) {
		goto done;
	}

	const char *imageDirChars = (*env)->GetStringUTFChars(env, imageDir, NULL);
	if (NULL == imageDirChars) {
		goto done;
	} else {
		int dirFD = open(imageDirChars, O_DIRECTORY);
		if (dirFD < 0) {
			printf("Couldn't open image directory, %s\n", imageDirChars);
			goto done;
		}
		criu_set_images_dir_fd(dirFD);
	}

	const char *workDirChars = NULL;
	if (NULL != workDir) {
		workDirChars = (*env)->GetStringUTFChars(env, workDir, NULL);
		if (NULL == workDirChars) {
			goto releaseImageDir;
		} else {
			int workDirFD = open(workDirChars, O_DIRECTORY);
			if (workDirFD < 0) {
				printf("Couldn't open working directory, %s\n", workDirChars);
				goto done;
			}
			criu_set_work_dir_fd(workDirFD);
		}
	}

	const char *logFileChars = NULL;
	if (NULL != logFile) {
		logFileChars = (*env)->GetStringUTFChars(env, logFile, NULL);
		if (NULL == logFileChars) {
			goto releaseWorkDir;
		} else {
			criu_set_log_file(logFileChars);
		}
	}

	criu_set_shell_job(JNI_FALSE != shellJob);
	if (logLevel > 0) {
		criu_set_log_level((int)logLevel);
	}
	criu_set_leave_running(JNI_FALSE != leaveRunning);
	criu_set_ext_unix_sk(JNI_FALSE != extUnixSupport);
	criu_set_file_locks(JNI_FALSE != fileLocks);
	criu_set_tcp_established(JNI_FALSE != tcpEstablished);
	criu_set_auto_dedup(JNI_FALSE != autoDedup);
	criu_set_track_mem(JNI_FALSE != trackMemory);


	rc = criu_dump();

	(*env)->ReleaseStringUTFChars(env, logFile, logFileChars);

releaseWorkDir:
	(*env)->ReleaseStringUTFChars(env, workDir, workDirChars);

releaseImageDir:
	(*env)->ReleaseStringUTFChars(env, imageDir, imageDirChars);

done:
	return rc;
}
