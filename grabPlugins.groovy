@Grab(group = 'org.codehaus.sonar', module = 'sonar-update-center-common', version = '1.12.1')

import org.sonar.updatecenter.common.UpdateCenterDeserializer
import org.sonar.updatecenter.common.Version

def download(address, filename) {
  if (filename == null) {
    filename = address.tokenize("/")[-1]
  }
  def file = new FileOutputStream(filename)
  def out = new BufferedOutputStream(file)
  out << new URL(address).openStream()
  out.close()
}

def cli = new CliBuilder(usage:'grabPlugins.groovy [options] [plugins]',
    header:'Options:')
cli.help('print this message')
cli.sq(args:1, argName:'sq', 'Set SonarQube version')
def options = cli.parse(args)
def sonarqubeVersion = options.sq
def plugins = options.arguments()

def tmpDir = new File("./tmp")
tmpDir.deleteDir()
tmpDir.mkdir()
def pluginsDir = new File("./plugins")
pluginsDir.deleteDir()
pluginsDir.mkdir()
download("http://update.sonarsource.org/update-center-dev.properties", "./tmp/update-center.properties")
def updateCenterDeserializer = new UpdateCenterDeserializer(UpdateCenterDeserializer.Mode.PROD, false)
uc = updateCenterDeserializer.fromSingleFile(new File("./tmp/update-center.properties"))
sqVersion = new Version(sonarqubeVersion)
uc.setInstalledSonarVersion(sqVersion)
uc.findAllCompatiblePlugins().each {
  if (plugins.contains(it.name)) {
    println "Downloading ${it.name}"
    download(it.getLastCompatible(sqVersion).downloadUrl, "./plugins/" + it.name + ".jar")
  }
}
