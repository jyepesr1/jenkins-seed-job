package util

import javaposse.jobdsl.dsl.Job

class Job {

  static void setConfig(Job job, String jobName, String repoOwner, String repoName, String scmCredentialsID, int numJobsToKeep, int daysToKeepJobs) {
    job.with {
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
