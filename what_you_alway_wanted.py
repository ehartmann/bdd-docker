import argparse
import subprocess

def main():
    parser = argparse.ArgumentParser();
    parser.add_argument("sqversion", help="SonarQube version", type=str);
    parser.add_argument("plugins", help="Comma separated list of plugins to install",
                        type=str);
    args = parser.parse_args();

    # launch process
    process = subprocess.Popen('groovy grabPlugins.groovy -sq ' + args.sqversion + ' ' + args.plugins.replace(",", " "), shell=True, stdout=subprocess.PIPE);
    process.wait();
    process = subprocess.Popen('fig build && fig up', shell=True, stdout=subprocess.PIPE);
    process.wait();

if __name__ == "__main__":
    main();
