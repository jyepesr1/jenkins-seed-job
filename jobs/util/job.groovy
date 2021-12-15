package util

class Job {

  static void createMultibranch(String jobName, String teamName, String repoOwner, String repoName, String scmCredentialsID, String numJobsToKeep, String daysToKeepJobs) {
    multibranchPipelineJob("${teamName}/${jobName}") {
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
