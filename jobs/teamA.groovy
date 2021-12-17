import util.JobFactory
import org.yaml.snakeyaml.Yaml


def datas = """
something: 'my datas'
size: 3
isEmpty: false
"""

def config = new Yaml().load(datas)

assert config.something == 'my datas'
assert config.size == 3
assert config.isEmpty == false

JobFactory factory = new JobFactory(this, "teamA", "jyepesr1", "")
factory.createTeamFolder()

project1 = factory.createMultibranchPipeline("HelloWorld", "jenkins-test", 10, 10)

project1.with {
  description("This is my test!!!")
}