import parent.util.Job

folder('teamA') {
  description('Folder containing all jobs for teamA')
}

Job.createMultibranchJob("HelloWorld", "teamA", "jyepesr1", "jenkins-test", null, 10, 10)