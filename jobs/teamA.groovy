import util.JobFactory

myYaml = """
something: 'my datas'
size: 3
isEmpty: false
"""
datas = readYaml(myYaml)

assert datas.something == 'my datas'
assert datas.size == 3
assert datas.isEmpty == false

JobFactory factory = new JobFactory(this, "teamA", "jyepesr1", "")
factory.createTeamFolder()

project1 = factory.createMultibranchPipeline("HelloWorld", "jenkins-test", 10, 10)

project1.with {
  description("This is my test!!!")
}