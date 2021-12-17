import util.JobFactory
import org.yaml.snakeyaml.Yaml

def workDir = SEED_JOB.getWorkspace()
def config = new Yaml().load(("${workDir}/jobs/config.yml" as File).text)

for (team in config.teams) {
  JobFactory factory = new JobFactory(this, team.name, team.repoOrg, team.scmCredentialsID)

  factory.createTeamFolder()

  for (project in team.projects) {
    factory.createMultibranchPipeline(project.name, project.repo, project.numJobsToKeep, project.daysToKeepJobs)
  }
}