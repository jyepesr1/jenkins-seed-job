import util.JobFactory

JobFactory factory = new JobFactory(this)
factory.createFolder("teamA")

factory.createMultibranchPipeline("HelloWorld", "teamA", "jyepesr1", "jenkins-test", "", 10, 10)
