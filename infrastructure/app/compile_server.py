#!/usr/bin/env python3

import subscription, util
from config import *

import sys, os, shutil
import logging
import requests
from google.cloud import storage


def compile_db_report(submissionid, result):
    """Sends the result of the run to the database API endpoint"""
    try:
        response = requests.patch(url=api_compile_update(submissionid), data={
            'compilation_status': result})
        response.raise_for_status()
    except:
        logging.critical('Could not report to database API endpoint')
        sys.exit(1)

def compile_log_error(submissionid, reason):
    """Reports a server-side error to the database and terminates with failure"""
    logging.error(reason)
    compile_db_report(submissionid, COMPILE_ERROR)
    sys.exit(1)

def compile_worker(submissionid):
    """
    Performs a compilation job as specified in submissionid
    Message format: {submissionid}
    A single string containing the submissionid
    """

    client = storage.Client()
    bucket = client.get_bucket(GCLOUD_BUCKET_SUBMISSION)

    # Filesystem structure:
    # /box/
    #     `-- source.zip
    #     `-- src/
    #     |      `-- all contents of source.zip
    #     |      `-- <robotname>
    #     |      |      `-- RobotPlayer.java (or whatever the main class should be named)
    #     |      |      `-- Other things
    #     `-- player.zip
    rootdir   = os.path.join('/', 'box')
    sourcedir = os.path.join(rootdir, 'src')
    builddir  = os.path.join(rootdir, 'build')

    # Obtain compressed archive of the submission
    try:
        os.mkdir(sourcedir)
        with open(os.path.join(rootdir, 'source.zip'), 'wb') as file_obj:
            bucket.get_blob(os.path.join(submissionid, 'source.zip')).download_to_file(file_obj)
    except:
        compile_log_error(submissionid, 'Could not retrieve source file from bucket')

    # Decompress submission archive
    result = util.monitor_command(
        ['unzip', 'source.zip', '-d', sourcedir],
        cwd=rootdir,
        timeout=TIMEOUT_UNZIP)
    if result[0] != 0:
        compile_log_error(submissionid, 'Could not decompress source file')

    util.pull_distribution(rootdir, lambda: compile_log_error(submissionid, 'Could not pull distribution'))

    result = util.monitor_command(
        ['./gradlew', 'build', '-Psource={}'.format(sourcedir)],
        cwd=rootdir,
        timeout=TIMEOUT_COMPILE)

    if result[0] == 0:
        result = util.monitor_command(
            ['zip', '-r', 'player.zip', 'classes'],
            cwd=builddir,
            timeout=TIMEOUT_COMPILE)

        # The compilation succeeded; send the classes to the bucket for storage
        if result[0] == 0:
            try:
                with open(os.path.join(builddir, 'player.zip'), 'rb') as file_obj:
                    bucket.blob(os.path.join(submissionid, 'player.zip')).upload_from_file(file_obj)
            except:
                compile_log_error(submissionid, 'Could not send executable to bucket')
            compile_db_report(submissionid, COMPILE_SUCCESS)
        else:
            compile_log_error(submissionid, 'Could not compress compiled classes')
    else:
        compile_db_report(submissionid, COMPILE_FAILED)

    # Clean up working directory
    try:
        shutil.rmtree(sourcedir)
        shutil.rmtree(builddir)
    except:
        logging.warning('Could not clean up compilation directory')


if __name__ == '__main__':
    subscription.subscribe(GCLOUD_SUB_COMPILE_NAME, compile_worker)