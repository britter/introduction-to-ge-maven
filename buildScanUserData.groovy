import org.apache.commons.lang3.StringUtils

// Look up the build scan API
def buildScan = session.lookup("com.gradle.maven.extension.api.scan.BuildScanApi")

// Capture if the build is from CI as a tag
def ci = System.getenv('CI')
if (ci) {
    buildScan.tag('CI')
} else {
    buildScan.tag('Local')
}

// Capture VCS branch of the build as a link
buildScan.link('Branch', "https://github.com/britter/introduction-to-ge-maven/tree/${System.getProperty('vcs.branch')}")

// Capture Git status as custom value
def status = StringUtils.chomp('git status --porcelain'.execute().text)
if (status) {
    buildScan.value('Git status', status)
}
