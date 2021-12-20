import util.JobFactory
import org.yaml.snakeyaml.Yaml

def workDir = SEED_JOB.getWorkspace()
def config = new Yaml().load(("${workDir}/config.yml" as File).text)
def projects = [:]

for (team in config.teams) {
  JobFactory factory = new JobFactory(this, team.name, team.repoOrg, team.scmCredentialsID)
  projects[team.name] = [:]
  factory.createTeamFolder()

  for (project in team.projects) {
    projects[team.name][project.name] = factory.createMultibranchPipeline(project.name, project.repo, project.numJobsToKeep, project.daysToKeepJobs)
  }
}

projects["teamA"]["test1"].with {
  description("This test is working as expected!!!")
}