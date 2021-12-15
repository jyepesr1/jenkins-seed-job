def PATH_TO_SCRIPTS = new File(getClass().protectionDomain.codeSource.location.path).parent
 
File jobClassFile = new File("${PATH_TO_SCRIPTS}/util", "job.groovy");
Class jobClass = new GroovyClassLoader(getClass().getClassLoader()).parseClass(jobClassFile);
GroovyObject Job = (GroovyObject) jobClass.newInstance();
 
folder('teamA') {
  description('Folder containing all jobs for teamA')
}

Job.createMultibranchJob("HelloWorld", "teamA", "jyepesr1", "jenkins-test", null, 10, 10)