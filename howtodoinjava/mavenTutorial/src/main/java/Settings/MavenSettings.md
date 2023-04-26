#Elements Description Table

| Element Name | Description |
| --- | --- |
| localRepository | stores copied of plugins/deps locally in ~/.m2/repository |
| interactiveMode | when TRUE, maven interacts w/ user for input |
| usePluginRegistry | when TRUE, uses ~/.m2/plugin-registry.xml to manage plugin versions |
| offline | when TRUE, Maven operates in offline mode (default=FALSE) |
| pluginGroups | contains list of pluginGroup elements, each w/ a groupId. This list is searhced when a plugin is used and the groupId isn't provided on CLI NOTE: automatically includes org.apache.maven.plugins and org.codehaus.mojo |
| servers | specifies required servers (build, remote repo, etc.) and the user/pass credentials |
| mirrors | alternate locations for repositories |
| proxies | HTTP proxy info needed to connect to the web |
| profiles | allows grouping of certain configuration elements, such as repos and pluginRepositories | 
| activeProfile | specifies default profile to be active for Maven |

