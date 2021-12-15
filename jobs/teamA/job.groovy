import util.createMultibranchJob

folder('teamA') {
  description('Folder containing all jobs for teamA')
}

createMultibranchJob("HelloWorld", "teamA", "jyepesr1", "jenkins-test", null, 10, 10)