import util.JobFactory
import org.yaml.snakeyaml.Yaml

def config = new Yaml().load(("./config.yml" as File).text)

for (team in config.teams) {
  JobFactory factory = new JobFactory(this, team.name, team.repoOrg, team.scmCredentialsID)

  factory.createTeamFolder()

  for (project in team.projects) {
    factory.createMultibranchPipeline(project.name, project.repo, project.numJobsToKeep, project.daysToKeepJobs)
  }
}