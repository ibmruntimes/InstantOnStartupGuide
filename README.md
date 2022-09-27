# InstantOnStartupGuide

Setup scripts to get started with InstantOn. This repo is used by the
[Getting Started blog post](https://blog.openj9.org/2022/09/26/getting-started-with-openj9-criu-support/).


# Quick Start

Create checkpoint image:
```
bash ./Scripts/privileged/build.sh
```

Run restore container:
```
bash ./Scripts/privileged/restoreContainer.sh
```

