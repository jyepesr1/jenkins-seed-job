import util.Job

folder('teamA') {
  description('Folder containing all jobs for teamA')
}

Job.createMultibranch("HelloWorld", "teamA", "jyepesr1", "jenkins-test", "", 10, 10)