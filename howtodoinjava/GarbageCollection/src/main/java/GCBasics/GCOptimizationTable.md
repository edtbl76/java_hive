# GC Optimization Options

| Flag | Description |
| --- | --- | 
| -Xms2048m -Xmx3g | sets initial and max heap size (young space + tenured space) |
| -XX:+DisableExplicitGC | causes JVM to ignore System.gc() method invocations by an application |
| -XX:+UseGCOverheadLimit | use policy used to limit time spent in GC before OOM error is thrown |
| -XX:GCTimeLimit=95 | limits proportion of time spent in GC before OOM error is thrown. used w/ GCHeapFreeLimit |
| -XX:GCHeapFreeLimit=5 | sets min % of free space after full GC before OOM is thrown. used w/ GCTimeLimit |
| -XX:InitialHeapSize=3g | sets INITIAL heap size (young space + tenured space) |
| -XX:MaxHeapSize=3g | sets MAX heap size (young space + tenured space) |
| -XX:NewSize=128m | sets INITIAL size of young space |
| -XX:MaxNewSize=128m | sets MAX size of young space | 
| -XX:SurvivorRation=15 | sets size of single survivor space as portion of Eden Space Size | 
| -XX:PermSize=512m | sets init size of PERM space |
| -XX:MaxPermSize=512m | sets max size of PERM space |
| -Xss512k | sets size oof stack area dedicated to each thread (in bytes) |

# GC Logging Flags

| Flag | Description | 
| --- | --- | 
| -verbose:gc or -XX:+PrintGC | prints basic GC info |
| -XX:+PrintGCDetails | prints more detailed GC info |
| -XX:+PrintGCTimeStamps | print timestamps for each GC event. Seconds are sequential and begin from the JVM start time |
| -XX:+PrintGCDateStamps | print date stamps for each GC event. |
| -Xloggc: | redirects GC output to file instead of console |
| -XX:+Print\TenuringDistribution | You can print detailed info regarding young space following each collection cycle |
| -XX:+PrintTLAB | print TLAB allocation stats |
| -XX:+PrintReferenceGC | print times for reference processing (i.e. weak, soft, etc.) during stop-the-world pauses | 
| -XX:+HeapDump\OnOutOfMemoryError | creates a heap dump file during OOM conditions |