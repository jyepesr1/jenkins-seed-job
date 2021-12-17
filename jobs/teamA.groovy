import util.JobFactory

def datas = readYaml text: """
something: 'my datas'
size: 3
isEmpty: false
"""
assert datas.something == 'my datas'
assert datas.size == 3
assert datas.isEmpty == false

JobFactory factory = new JobFactory(this, "teamA", "jyepesr1", "")
factory.createTeamFolder()

project1 = factory.createMultibranchPipeline("HelloWorld", "jenkins-test", 10, 10)

project1.with {
  description("This is my test!!!")
}