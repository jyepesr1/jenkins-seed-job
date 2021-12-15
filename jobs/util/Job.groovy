package util

import javaposse.jobdsl.dsl.DslFactory

class Job {

  static void createMultibranch(String jobName, String teamName, String repoOwner, String repoName, String scmCredentialsID, int numJobsToKeep, int daysToKeepJobs) {
    DslFactory.multibranchPipelineJob("${teamName}/${jobName}") {
      branchSources {
        github  {
          checkoutCredentialsId(scmCredentialsID)
          repoOwner(repoOwner)
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
