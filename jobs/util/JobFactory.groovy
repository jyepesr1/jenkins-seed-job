package util

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Folder
import javaposse.jobdsl.dsl.jobs.MultibranchWorkflowJob

class JobFactory {
  private final DslFactory factory
  private final String teamName
  private final String repoOrg
  private final String scmCredentialsID

  JobFactory(DslFactory dslFactory, String teamName, String repoOrg, String scmCredentialsID) {
    this.factory = dslFactory
    this.teamName = teamName
    this.repoOrg = repoOrg
    this.scmCredentialsID = scmCredentialsID
  }

  Folder createTeamFolder() {
    factory.folder(this.teamName) {
      description("Folder containing all jobs for ${this.teamName}")
    }
  }

  MultibranchWorkflowJob createMultibranchPipeline(String jobName, String repoName, int numJobsToKeep, int daysToKeepJobs) {
    factory.multibranchPipelineJob("${this.teamName}/${jobName}") {
      branchSources {
        github  {
          checkoutCredentialsId(this.scmCredentialsID)
          repoOwner(this.repoOrg)
          repository(repoName)
          scanCredentialsId(this.scmCredentialsID)
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