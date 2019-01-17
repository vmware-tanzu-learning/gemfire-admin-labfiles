# Ensure student is running this using 'sudo'
if [ "$(whoami)" != "root" ]; then
echo "This script should be run as 'sudo'"
exit -1
fi

# Find the gemfire tgz file without having to know the version
TARFILE=/course_files/Binaries/pivotal-gemfire-9.7.0.tgz

# Set up the directory to install
mkdir -p /opt/pivotal
chmod 777 /opt/pivotal

# Set up a gemfire group and add student to it
groupadd gemfire
usermod -a -G gemfire student

# Disable Firewalld
systemctl disable firewalld
systemctl stop firewalld

# Unpack GemFire
tar -xzf $TARFILE -C /opt/pivotal
GEMPATH=$(find /opt/pivotal -name "pivotal-gemfire*")
chgrp -R gemfire $GEMPATH
echo "PATH=$PATH:$GEMPATH/bin" >> /home/student/.bashrc
