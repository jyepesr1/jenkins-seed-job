package util

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Folder
import javaposse.jobdsl.dsl.jobs.MultibranchWorkflowJob

class JobFactory {
  private final DslFactory factory
  private final String teamName
  private final String repoOrg
  private final String scmCredentialsID

  JobFactory(DslFactory dslFactory, teamName, repoOrg, scmCredentialsID) {
    this.factory = dslFactory
    this.teamName = teamName
    this.repoOrg = repoOrg
    this.scmCredentialsID = scmCredentialsID
  }

  Folder createTeamFolder() {
    factory.folder("${teamName}") {
      description("Folder containing all jobs for ${teamName}")
    }
  }

  MultibranchWorkflowJob createMultibranchPipeline(String jobName, String repoName, int numJobsToKeep, int daysToKeepJobs) {
    factory.multibranchPipelineJob("${teamName}/${jobName}") {
      branchSources {
        github  {
          checkoutCredentialsId(scmCredentialsID)
          repoOwner(repoOrg)
          repository(repoName)
          scanCredentialsId(scmCredentialsID)
          id(jobName)
        }
      }

      factory {
        workflowBranchProjectFactory {
          scriptPath('Jenkinsfile')
        }
      }

      orphanedItemStrategy {
        discardOldItems {
          numToKeep(numJobsToKeep)
          daysToKeep(daysToKeepJobs)
        }
      }
    }
  }
}