import javaposse.jobdsl.dsl.jobs.MultibranchPipelineJob
import util.Job

folder('teamA') {
  description('Folder containing all jobs for teamA')
}

MultibranchPipelineJob project1 = multibranchPipelineJob("teamA/helloWorld")

Job.setConfig(project1, "HelloWorld", "jyepesr1", "jenkins-test", "", 10, 10)

