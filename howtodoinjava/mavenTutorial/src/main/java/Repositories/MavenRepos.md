#MavenRepos

## LOCAL REPOSITORY
- resides on the dev machine. 
- when maven goals are run that require dependencies, they are downloaded and stored oon the dev's machine.
- located by default in ~/.m2/repository
- maven will look here first when attempting to resolve dependencies.

BENEFITS
- multiple projects can access same artifacts (reduced storage usage) 
- dependency is downloaded only once. (reduced network usage)

## MAVEN CENTRAL REPO
- "http://repo.maven.apache.org/maven2"
- This is the first location Maven will try if it can't resolve deps locally
- NOTE: this may be overriden in the ~/.m2/settings.xml file.

## REMOTE REPOSITORIES
- these are "other" remote repositories that work identically to the maven central repo. 
- the difference is that these are managed by 3rd parties, your organization, some kid in a basement
- these are the last line of defense for dep resolution. 

## DEFAULT LOCAL REPO
- ~/.m2/repository
- This can be changed in ~/.m2/settings.xml


    <localRepository>/new/directory</localRepository>
    
- This is also specified via EV M2_REPO
    
