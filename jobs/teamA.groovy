import util.JobFactory

JobFactory factory = new JobFactory(this, "teamA", "jyepesr1", "")
factory.createTeamFolder()

project1 = factory.createMultibranchPipeline("HelloWorld", "jenkins-test", 10, 10)

project1.with {
  description("This is my test!!!")
}