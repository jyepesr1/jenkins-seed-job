package util

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Folder
import javaposse.jobdsl.dsl.jobs.MultibranchWorkflowJob

class JobFactory {
  private final DslFactory factory

  JobFactory(DslFactory dslFactory) {
    this.factory = dslFactory
  }

  Folder createFolder(String teamName) {
    factory.folder(teamName) {
      description("Folder containing all jobs for ${teamName}")
    }
  }

  MultibranchWorkflowJob createMultibranchPipeline(String jobName, String teamName, String repoOrg, String repoName, String scmCredentialsID, int numJobsToKeep, int daysToKeepJobs) {
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