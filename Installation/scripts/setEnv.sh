# JAVA_HOME may already be set
# export JAVA_HOME=

# set COURSE_HOME to directory containing labs
export COURSE_HOME=/Applications/GemFire-Admin-9.0.4c.RELEASE

# GEMFIRE may already be set
export GEMFIRE=$COURSE_HOME/pivotal-gemfire-9.0.4

# Set COURSE_RUN to directory from which labs are run
export COURSE_RUN=$COURSE_HOME/runLab

# Add GemFire bin path to execution path
export PATH=$GEMFIRE/bin:$JAVA_HOME/bin:$COURSE_RUN/management_scripts/unix:$PATH

# Set CLASSPATH mainly for client applications
export CLASSPATH=$COURSE_RUN/bookShop-1.0.jar:$GEMFIRE/lib/*

