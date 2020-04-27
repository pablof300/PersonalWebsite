
# Persian

Persian is a centralized API gateway for a microservice architecture of personal projects.

# Requirements
- OpenJDK 12.0.1
- Node v13.6.0
- Maven

# Deployment

### Deployment using Azure
1) Create a resource group named *Persian*
2) Create a virtual machine named *PersianVM* running *Ubuntu Server 18.04 LTS*

Make sure to configure the SSH connection credentials and the following networking rules:
- Allow Backend 8080 (this one will be configured after server creation)
- Allow Frontend 80
- Allow SSH 22 (TCP)
- Allow SSL 443
3) Create a key vault called *PersianVault*
Import the .pfx certificate into the vault Azure Virtual Machines for Deployment and Azure Resource Manager for template deployment
Go to access policies, then enable  
4) Create a DNS Zone called *pabloestrada.me*
First, point the DNS servers to this resource. Then, setup the following records
- A record: @, A, 3600, server_ip (ex: 40.122.43.44)
- A record: www, A, 3600, server_ip (ex: 40.122.43.44)
5) SSH into VM and install all needed requirements

```bash
sudo apt-get update
curl -sL https://deb.nodesource.com/setup_13.x | sudo -E bash -
sudo apt-get install -y nodejs
curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh | sudo bash
sudo apt-get install gitlab-runner
sudo nano /etc/sudoers
// add gitlab-runner ALL=(ALL) NOPASSWD: ALL to the bottom
sudo apt install docker.io
sudo apt install docker-compose
sudo docker volume create persian_volume
```
Install Java
```bash
wget https://download.java.net/java/GA/jdk12.0.2/e482c34c86bd4bf8b56c0b35558996b9/10/GPL/openjdk-12.0.2_linux-x64_bin.tar.gz
sudo mkdir /usr/java
sudo mv openjdk-12.0.2_linux-x64_bin.tar.gz /usr/java
cd /usr/java
sudo tar -xzvf openjdk-12.0.2_linux-x64_bin.tar.gz
sudo nano /etc/profile
// add to the bottom:
// JAVA_HOME=/usr/java/jdk-12.0.2
// PATH=$PATH:$HOME/bin:$JAVA_HOME/bin
// export JAVA_HOME
// export JRE_HOME 
// export PATH
sudo update-alternatives --install "/usr/bin/java" "java" "/usr/java/jdk-12.0.2/bin/java" 1
sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/java/jdk-12.0.2/bin/javac" 1
```
then Maven
```bash
sudo apt install maven
```
6) Add the runner to Gitlab by running this command

```
sudo gitlab-runner register --non-interactive --url "https://gitlab.com" --r "za3PWhg-dqCmusbL5fqJ" --executor "shell" --tag-list "PersianCI" --run-untagged --pre-clone-script "sudo chown -R gitlab-runner:gitlab-runner ."
```
7) Open PowerShell
8) Add the key vault to the Virtual Machine through:

```bash
$resourceGroup = 'Persian'
$vaultName = 'PersianVault'
$key = 'ssl'
$keyVaultName = 'PersianVault'
$vmName = 'PersianVM'


$certURL=(Get-azKeyVaultSecret -VaultName $keyVaultName -Name $key).id
$vm=Get-azVM -ResourceGroupName $resourceGroup -Name $vmName
$vaultId=(Get-azKeyVault -ResourceGroupName $resourceGroup -VaultName $keyVaultName).ResourceId
$vm = Add-azVMSecret -VM $vm -SourceVaultId $vaultId -CertificateUrl $certURL

Update-azVM -ResourceGroupName $resourceGroup -VM $vm
```
