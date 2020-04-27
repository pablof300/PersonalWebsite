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
Import the .pfx certificate into the vault
4) Create a DNS Zone called *pabloestrada.me*
First, point the DNS servers to this resource. Then, setup the following records
- A record: @, A, 3600, 40.122.43.44
- A record: www, A, 3600, 40.122.43.44
5) SSH into VM and install all needed requirements

```bash
    sudo apt-get update
    curl -sL https://deb.nodesource.com/setup_13.x | sudo -E bash -
    sudo apt-get install -y nodejs
    curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh | sudo bash
    sudo apt-get install gitlab-runner
    sudo nano /etc/sudoers
    // add gitlab-runner ALL=(ALL) NOPASSWD: ALL to the bottom
    sudo apt install openjdk-11-jre-headless
    sudo apt install maven
    sudo apt install docker.io
```

6) Add the runner to Gitlab by running this command

sudo gitlab-runner register --non-interactive --url "https://gitlab.com" --r "za3PWhg-dqCmusbL5fqJ" --executor "shell" --tag-list "PersianCI" --run-untagged --pre-clone-script "sudo chown -R gitlab-runner:gitlab-runner ."
